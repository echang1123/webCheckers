/*
 * POST "/submitTurn" handler
 *
 * @author Karthik Iyer
 * @Emily Wesson
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.*;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostSubmitTurnRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostSubmitTurnRoute.class.getName() );
    private GlobalInformation gi;
    private final TemplateEngine templateEngine;


    /**
     * Constructor for the PostSubmitTurnRoute Handler
     * @param gi the global information
     */
    public PostSubmitTurnRoute( TemplateEngine templateEngine, GlobalInformation gi ) {
        // validate
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
    }


    /**
     * Handler for the http request
     * @param request the http request
     * @param response the response
     * @return a Message that is either of type info (if successful) or error (if unsuccessful)
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "PostSubmitTurnRoute is invoked" );

        Session httpSession = request.session();
        PlayerLobby playerLobby = gi.getPlayerLobby();
        GameLobby gameLobby = gi.getGameLobby();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_KEY );
        if( currentPlayerName == null ) {
            return new Message( "", Message.MessageType.ERROR );
        }
        Player currentPlayer = playerLobby.getPlayer( currentPlayerName );
        if( currentPlayer == null ) {
            return new Message( "", Message.MessageType.ERROR );
        }

        Game game = gameLobby.findGame( currentPlayer );
        if( game == null ) {
            return new Message( "", Message.MessageType.ERROR );
        }

        Board board = game.getBoard();
        int numberOfMovesSubmitted = 0;

        // iterate till we have used all the validated moves
        while( !game.outOfValidatedMoves() ) {
            Move move = game.getFirstValidatedMove();
            board.doMove( move );
            numberOfMovesSubmitted++;
        }

        // there were no validated moves
        // return an error message
        if( numberOfMovesSubmitted == 0 ) {
            return new Message( "", Message.MessageType.ERROR );
        }


        //get all of the players, remove the current player from that Map
        HashMap< String, Player > players = playerLobby.getPlayers();
        Map< String, Player > otherPlayers = new HashMap<>( players );
        otherPlayers.remove( currentPlayerName ); // remove the current player from being shown

        Map< String, Object > vm = new HashMap<>();

        //if you are RED and your opponent is out of pieces, you won
        if( currentPlayer.equals(game.getPlayerOne()) && board.getWhitePiecesInPlay() <= 0){

            currentPlayer.removeOpponent();
         //   gameLobby.removeGame( game ); when they find out they lost, the opponent will do this in checkTurnRoute
            httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
            Message message = new Message( "You are victorious!", Message.MessageType.INFO );

            vm.put( RoutesAndKeys.MESSAGE_KEY, message );
            vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName);
            vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
            vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );
            game.switchTurn();
            return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
        }

        //if you are WHITE and your opponent is out of pieces, you won
        if( currentPlayer.equals( game.getPlayerTwo() ) && board.getRedPiecesInPlay() <= 0 ){

            currentPlayer.removeOpponent();
            httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
            Message message = new Message( "You are victorious!", Message.MessageType.INFO );

            vm.put( RoutesAndKeys.MESSAGE_KEY, message );
            vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName);
            vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
            vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );
            game.switchTurn();
            return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
        }

        //check if opponent has 0 moves available
        //if you are red, check all white pieces for available jump or single moves, both pawn and king pieces

        //if you are white, check all red pieces


        // all validated moves were submitted successfully
        else {
            game.switchTurn();
            return new Message( "", Message.MessageType.INFO );
        }
    }

}
