/*
 * GET "/game" Route Handler
 *
 * @author Karthik Iyer
 * @author Eugene Chang
 * @author Emily Wesson
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetGameRoute implements Route {

    public enum ViewMode { PLAY, SPECTATOR, REPLAY }

    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;

    /**
     * Constructor for the GetGameRoute route handler
     * @param templateEngine  the HTML template rendering engine
     */
    public GetGameRoute( final TemplateEngine templateEngine, final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "GI must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
        LOG.config( "GetGameRoute is initialized." );
    }


    /**
     * Render the signed-in WebCheckers Home page.
     * @param request  the HTTP request
     * @param response  the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetGameRoute is invoked." );

        // construct the view model
        Map< String, Object > vm = new HashMap<>();
        vm.put("title", "Welcome!");

        final Session httpSession = request.session();
        final PlayerLobby playerLobby = gi.getPlayerLobby();
        HashMap< String, Player > players = playerLobby.getPlayers();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER );
        Player currentPlayer = players.get( currentPlayerName );

        // check if you are the first player
        Boolean isFirstPlayer = false;
        String opponentName = ""; // initialize the opponent name with an empty string
        for( String playerName : players.keySet() ) { // iterate through all the players
            if( playerName.equals( currentPlayerName ) ) // skip if we run into the current player
                continue;
            else { // otherwise, for other players...
                String playerButton = request.queryParams( playerName ); // get the button
                if( playerButton == null ) // not pressed
                    continue;
                else { // has been pressed
                    opponentName = playerName; // get the opponent's name
                    if( players.get( opponentName ).getOpponent() != null ) { // the selected opponent is already in game
                        String message = "Player \"" + opponentName + "\" is already playing a game.";
                        vm.put( "message", message );
                        vm.put( RoutesAndKeys.CURRENT_PLAYER, currentPlayerName );
                        Map< String, Player > otherPlayers = new HashMap<>( players );
                        otherPlayers.remove( currentPlayerName ); // remove the current player from being shown
                        vm.put( RoutesAndKeys.PLAYERS, otherPlayers );
                        vm.put( RoutesAndKeys.SIGNED_IN, true );
                        // render home with error message
                        return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
                    }
                    isFirstPlayer = true;
                    break;
                }
            }
        }

        // add main info
        Board boardModel = new Board( isFirstPlayer );
        BoardView board = boardModel.getBoardView();
        vm.put( "board", board );
        vm.put( "viewMode", ViewMode.PLAY );
        vm.put( RoutesAndKeys.CURRENT_PLAYER, currentPlayer );
        vm.put( "activeColor", Piece.Color.RED );

        // set opponent, redplayer, whiteplayer
        Player opponent;
        if( isFirstPlayer ) { // first player
            opponent = players.get( opponentName );
            currentPlayer.addOpponent( opponent );
            vm.put( "redPlayer", currentPlayer );
            vm.put( "whitePlayer", opponent );
        }
        else { // second player
            opponent = playerLobby.findOpponent( currentPlayer );
            currentPlayer.addOpponent( opponent );
            vm.put( "redPlayer", opponent );
            vm.put( "whitePlayer", currentPlayer );
        }

        // render
        return templateEngine.render( new ModelAndView( vm, "game.ftl" ) );

    }
}
