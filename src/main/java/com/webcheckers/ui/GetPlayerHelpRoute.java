/*
 * GET "/playerHelp" route handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.*;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;


import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class GetPlayerHelpRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( GetPlayerHelpRoute.class.getName() );
    private TemplateEngine templateEngine;
    private final GlobalInformation gi;


    /**
     * Default constructor for the GetPlayerHelpRoute route handler
     */
    public GetPlayerHelpRoute( GlobalInformation gi, TemplateEngine templateEngine ) {
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );
        Objects.requireNonNull( templateEngine, "template engine cannot be null" );
        this.gi = gi;
        this.templateEngine = templateEngine;
    }


    /**
     * Helper method that generates move strings
     *
     * @param validMoves the valid moves
     * @return move strings
     */
    private ArrayList< String > getMoveStrings( ArrayList< Move > validMoves ) {
        ArrayList< String > moveStrings = new ArrayList<>();
        for( Move move : validMoves ) {
            String moveString = "Move (";
            moveString += move.getStart().getRow() + ", " + move.getStart().getCell() + ") to (";
            moveString += move.getEnd().getRow() + ", " + move.getEnd().getCell() + ")";
            moveStrings.add( moveString );
        }
        return moveStrings;
    }


    /**
     * Handler method for GET "/playerHelp"
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendering by the template engine
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetPlayerHelpRoute is invoked." );

        PlayerLobby playerLobby = gi.getPlayerLobby();
        GameLobby gameLobby = gi.getGameLobby();
        Session httpSession = request.session();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_NAME_KEY );
        if( currentPlayerName == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }
        Player currentPlayer = playerLobby.getPlayer( currentPlayerName );
        if( currentPlayer == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }
        Game game = gameLobby.findGame( currentPlayer );
        if( game == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        final String PLAYER_ONE_BOARD_IMAGE_SRC = "http://localhost:4567/img/player1-board.jpg";
        final String PLAYER_TWO_BOARD_IMAGE_SRC = "http://localhost:4567/img/player2-board.jpg";

        Map< String, Object > vm = new HashMap<>();
        MoveVerifier moveVerifier = game.getMoveVerifier();
        ArrayList< Move > validMoves;
        if( currentPlayer.equals( game.getPlayerOne() ) ) {
            vm.put( RoutesAndKeys.IMAGE_SRC_KEY, PLAYER_ONE_BOARD_IMAGE_SRC );
            validMoves = moveVerifier.getValidMoves( game, Piece.Color.RED );
        }
        else {
            vm.put( RoutesAndKeys.IMAGE_SRC_KEY, PLAYER_TWO_BOARD_IMAGE_SRC );
            validMoves = moveVerifier.getValidMoves( game, Piece.Color.WHITE );
        }

        HashSet< Move > uniqueMoveSet = new HashSet<>( validMoves );
        ArrayList< Move > uniqueMoves = new ArrayList<>( uniqueMoveSet );
        ArrayList< String > validMoveStrings = getMoveStrings( uniqueMoves );
        vm.put( RoutesAndKeys.VALID_MOVE_STRINGS_KEY, validMoveStrings );

        // render
        return templateEngine.render( new ModelAndView( vm, "playerHelp.ftl" ) );
    }

}
