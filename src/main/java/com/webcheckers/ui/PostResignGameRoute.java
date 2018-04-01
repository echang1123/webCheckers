/*
 * POST "/resignGame" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Game;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;


import java.util.Objects;
import java.util.logging.Logger;


public class PostResignGameRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostResignGameRoute.class.getName() );
    private GlobalInformation gi;


    /**
     * Constructor for the PostResignGameRoute handler
     * @param gi the global information
     */
    public PostResignGameRoute( GlobalInformation gi ) {
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );
        this.gi = gi;
    }


    /**
     * Handler function
     * @param request the http session request
     * @param response the response
     * @return a message of either info type or error type
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "PostResignGameRoute invoked" );

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

        currentPlayer.removeOpponent();

        // you are the first player
        if( game.getPlayerOne().equals( currentPlayer ) ) {
            httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
            game.removePlayerOne();
            if( game.getWhoseTurn() == 0 )
                game.switchTurn();
            return new Message( "", Message.MessageType.INFO );
        }

        // you are the second player
        else if( game.getPlayerTwo().equals( currentPlayer ) ) {
            httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
            game.removePlayerTwo();
            if( game.getWhoseTurn() == 1 )
                game.switchTurn();
            return new Message( "", Message.MessageType.INFO );
        }

        // error
        else {
            return new Message( "", Message.MessageType.ERROR );
        }
    }

}
