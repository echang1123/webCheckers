/*
 * POST "/submitTurn" handler
 *
 * @author Karthik Iyer
 * @Emily Wesson
 */


package com.webcheckers.ui;


import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import spark.*;


import java.util.Objects;
import java.util.logging.Logger;


public class PostSubmitTurnRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostSubmitTurnRoute.class.getName() );
    private GlobalInformation gi;


    /**
     * Constructor for the PostSubmitTurnRoute Handler
     *
     * @param gi the global information
     */
    PostSubmitTurnRoute( GlobalInformation gi ) {
        // validate
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );

        this.gi = gi;
    }


    /**
     * Handler for the http request
     *
     * @param request  the http request
     * @param response the response
     * @return a Message that is either of type info (if successful) or error (if unsuccessful)
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "PostSubmitTurnRoute is invoked" );

        Session httpSession = request.session();
        PlayerLobby playerLobby = gi.getPlayerLobby();
        GameLobby gameLobby = gi.getGameLobby();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_NAME_KEY );
        if( currentPlayerName == null ) {
            return new Message( "", Message.MessageType.error );
        }
        Player currentPlayer = playerLobby.getPlayer( currentPlayerName );
        if( currentPlayer == null ) {
            return new Message( "", Message.MessageType.error );
        }

        Game game = gameLobby.findGame( currentPlayer );
        if( game == null ) {
            return new Message( "", Message.MessageType.error );
        }

        Board board = game.getBoard();
        MoveVerifier mv = game.getMoveVerifier();
        Move latestMove = game.getLatestVerifiedMove();
        if( mv.checkMultipleJumps( latestMove, game ) ) {
            return new Message( "There are jump(s) you must make!", Message.MessageType.error );
        }

        int numberOfMovesSubmitted = 0;

        // iterate till we have used all the validated moves
        while( !game.outOfVerifiedMoves() ) {
            Move move = game.getFirstVerifiedMove();
            board.doMove( move );
            numberOfMovesSubmitted++;
        }

        // there were no validated moves
        // return an error message
        if( numberOfMovesSubmitted == 0 ) {
            return new Message( "", Message.MessageType.error );
        }

        boolean wonGame = false;
        // if you are Red and either your opponent is out of pieces or they can't move, you won
        if( currentPlayer.equals( game.getPlayerOne() ) &&
            ( board.getWhitePiecesInPlay() <= 0 || ( !game.anyMovesAvailableForPlayerTwo() ) ) ) {
            wonGame = true;
        }

        // if you are White and either your opponent is out of pieces or has no moves available, you won
        if( currentPlayer.equals( game.getPlayerTwo() ) &&
            ( board.getRedPiecesInPlay() <= 0 || ( !game.anyMovesAvailableForPlayerOne() ) ) ) {
            wonGame = true;
        }

        if( wonGame ) {
            game.setGameState( Game.GameState.complete );
            game.setWinner( currentPlayer );
        }

        // all validated moves were submitted successfully
        game.switchTurn();
        return new Message( "", Message.MessageType.info );
    }

}
