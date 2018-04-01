/*
 * POST "/checkTurn" handler
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
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostCheckTurnRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( PostCheckTurnRoute.class.getName() );
    private GlobalInformation gi;
    private final TemplateEngine templateEngine;


    /**
     * Constructor for the PostCheckTurnRoute class
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
     * @param request the http request
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

        if( currentPlayer.equals( game.getPlayerOne() ) ) {
            // your opponent resigned
            if( game.getPlayerTwo() == null ) {
                currentPlayer.removeOpponent();
                httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
                Map< String, Object > vm = new HashMap<>();
                HashMap< String, Player > players = playerLobby.getPlayers();
                Map< String, Player > otherPlayers = new HashMap<>( players );
                otherPlayers.remove( currentPlayerName ); // remove the current player from being shown
                Message message = new Message( "Player 2 resigned.", Message.MessageType.INFO );
                vm.put( RoutesAndKeys.MESSAGE_KEY, message );
                vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName);
                vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
                vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );
                return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
            }

            if( game.getWhoseTurn() == 0 )
                return new Message( "true", Message.MessageType.INFO );
            else
                return new Message( "false", Message.MessageType.INFO );
        }
        else {
            // your opponent resigned
            if( game.getPlayerOne() == null ) {
                currentPlayer.removeOpponent();
                httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );
                Map< String, Object > vm = new HashMap<>();
                HashMap< String, Player > players = playerLobby.getPlayers();
                Map< String, Player > otherPlayers = new HashMap<>( players );
                otherPlayers.remove( currentPlayerName ); // remove the current player from being shown
                Message message = new Message( "Player 1 resigned.", Message.MessageType.INFO );
                vm.put( RoutesAndKeys.MESSAGE_KEY, message );
                vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName);
                vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
                vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );
                return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
            }

            if( game.getWhoseTurn() == 0 )
                return new Message( "false", Message.MessageType.INFO );
            else
                return new Message( "true", Message.MessageType.INFO );
        }
    }
}

