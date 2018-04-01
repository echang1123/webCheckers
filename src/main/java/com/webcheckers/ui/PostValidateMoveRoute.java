/*
 * POST "/validateMove" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.*;
import com.webcheckers.model.Game;
import com.webcheckers.model.Message;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;


public class PostValidateMoveRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostValidateMoveRoute.class.getName() );
    private GlobalInformation gi;


    /**
     * Constructor for the PostValidateMoveRoute route handler
     * @param gi the Global Information object
     */
    public PostValidateMoveRoute( final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( gi, "GI cannot be null" );
        this.gi = gi;
    }


    /**
     * Handles the POST "/validateMove" request
     * @param request the request
     * @param response the response
     * @return the Object containing the return data
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "PostValidateMoveRoute is invoked." );

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

        MoveValidator moveValidator = new MoveValidator( game );

        final String dataString = request.body();
        Move move = JsonUtils.fromJson( dataString, Move.class );
        if( move == null ) {
            return new Message( "", Message.MessageType.ERROR );
        }

        boolean isValidMove = moveValidator.validate( move );

        // if the move is valid, add it to the validatedMoves array list and
        // return a message of type INFO
        if( isValidMove ) {
            game.addValidatedMove( move );
            return new Message( "", Message.MessageType.INFO );
        }

        // if the move is not valid, return a message of type ERROR
        else {
            return new Message( "", Message.MessageType.ERROR );
        }

    }
}
