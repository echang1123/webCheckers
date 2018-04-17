/*
 * POST "/backupMove" handler
 *
 * @author Emily Wesson
 */

package com.webcheckers.ui;

import com.webcheckers.appl.*;
import com.webcheckers.model.Game;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;

public class PostBackupMoveRoute implements Route {
    // Attributes
    private static final Logger LOG = Logger.getLogger( PostBackupMoveRoute.class.getName() );
    private GlobalInformation gi;


    /**
     * Constructor for the PostBackupMoveRoute route handler
     *
     * @param gi the Global Information object
     */
    public PostBackupMoveRoute( final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( gi, "GI cannot be null" );
        this.gi = gi;
    }


    /**
     * Handles the POST "/backupMove" request
     *
     * @param request  the request
     * @param response the response
     * @return the Object containing the return data
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "PostBackupMoveRoute is invoked." );

        Session httpSession = request.session();
        if( httpSession == null ) {
            return new Message( "", Message.MessageType.error );
        }

        PlayerLobby playerLobby = gi.getPlayerLobby();
        if( playerLobby == null ) {
            return new Message( "", Message.MessageType.error );
        }

        GameLobby gameLobby = gi.getGameLobby();
        if( gameLobby == null ) {
            return new Message( "", Message.MessageType.error );
        }

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

        // if validatedMoves array list has at least one move, remove it and return a message of type INFO
        if( !game.outOfVerifiedMoves() ) {
            game.backupVerifiedMove();
            return new Message( "", Message.MessageType.info );
        }

        // if there are no moves in the validatedMove array list, return a message of type ERROR
        else {
            return new Message( "", Message.MessageType.error );
        }

    }

}
