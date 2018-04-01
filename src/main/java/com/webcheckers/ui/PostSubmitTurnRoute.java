/*
 * POST "/submitTurn" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;


import java.util.Objects;
import java.util.logging.Logger;


public class PostSubmitTurnRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostSubmitTurnRoute.class.getName() );
    private GlobalInformation gi;


    /**
     * Constructor for the PostSubmitTurnRoute Handler
     * @param gi the global information
     */
    public PostSubmitTurnRoute( GlobalInformation gi ) {
        // validate
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );

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

        //if you are RED and your opponent is out of pieces, you won
        if( currentPlayer.equals(game.getPlayerOne()) && board.getWhitePiecesInPlay() <= 0){

        }

        //if you are WHITE and your opponent is out of pieces, you won
        if( currentPlayer.equals( game.getPlayerTwo() ) && board.getRedPiecesInPlay() <= 0 ){

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
