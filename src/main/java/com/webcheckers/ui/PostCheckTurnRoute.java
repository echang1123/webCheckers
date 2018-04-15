/*
 * POST "/checkTurn" handler
 *
 * @author Karthik Iyer
 * @Emily Wesson
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Game;
import com.webcheckers.model.Message;
import com.webcheckers.model.Player;
import spark.*;


import java.util.Objects;
import java.util.logging.Logger;


public class PostCheckTurnRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostCheckTurnRoute.class.getName() );
    private GlobalInformation gi;
    private final TemplateEngine templateEngine;


    /**
     * Constructor for the PostCheckTurnRoute class
     *
     * @param gi the Global Information object
     */
    public PostCheckTurnRoute( final TemplateEngine templateEngine, GlobalInformation gi ) {
        // Validation
        Objects.requireNonNull( LOG, "LOG cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );
        Objects.requireNonNull( templateEngine, "templateEngine cannot be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
    }


    /**
     * Handler function for PostCheckTurnRoute
     * The function retrieves the current player from the player lobby, and the game that the current player is in
     * from the game lobby. It determines if the current player is player one or two, and then checks whose turn it
     * is in the game. If it is the current player's turn, then it returns a message saying "true", otherwise it
     * returns a message saying "false".
     *
     * @param request  the http request
     * @param response the response
     * @return a Message with 'true' or 'false'
     */
    @Override
    public Object handle( Request request, Response response ) {

        Session httpSession = request.session();
        PlayerLobby playerLobby = gi.getPlayerLobby();
        GameLobby gameLobby = gi.getGameLobby();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_KEY );
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

        if( game.getGameState() != null ) {
            if( game.isComplete() ) {
                return new Message( "true", Message.MessageType.info );
            }
        }
        else {
            System.out.println( "Game State was NULL" );
        }

        if( currentPlayer.equals( game.getPlayerOne() ) ) {
            if( game.getWhoseTurn() == 0 )
                return new Message( "true", Message.MessageType.info );
            else
                return new Message( "false", Message.MessageType.info );
        } else {

            if( game.getWhoseTurn() == 0 )
                return new Message( "false", Message.MessageType.info );
            else
                return new Message( "true", Message.MessageType.info );
        }
    }
}

