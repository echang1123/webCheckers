package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Eugene on 3/3/2018.
 */
public class GetBoardRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignInRoute.class.getName());
    public enum ViewMode { PLAY, SPECTATOR, REPLAY };

    static final String PLAYER_LOBBY_KEY = "playerLobby";
    static final String SIGNED_IN = "isSignedIn";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String PLAYERS = "players";
    public static final String INGAME_URL = "/board";

    private final TemplateEngine templateEngine;
    private HashMap<String, Player> players;


    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetBoardRoute(final TemplateEngine templateEngine, final HashMap<String,Player> players) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.players = players;
        //
        LOG.config("GetBoardRoute is initialized.");
    }


    /**
     * Render the signed-in WebCheckers Home page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetBoardRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        final Session httpSession = request.session();
        String currentPlayerName = httpSession.attribute( CURRENT_PLAYER );
        Player currentPlayer = players.get( currentPlayerName );
        PlayerLobby playerLobby = httpSession.attribute( PLAYER_LOBBY_KEY );

        // check if you are the first player
        Boolean isFirstPlayer = false;
        for( String playerName : players.keySet() ) { // iterate through all the players
            if( playerName.equals( currentPlayerName ) ) { // current player
                continue;
            }
            else { // other player
                String playerButton = request.queryParams( playerName ); // get the button
                if( playerButton == null ) {
                    continue;
                }
                else {
                    isFirstPlayer = true;
                    break;
                }
            }
        }

        Player opponent = playerLobby.findOpponent( currentPlayer );
        currentPlayer.addOpponent( opponent );
        Board boardModel = new Board( isFirstPlayer ); // create the board model and put in the pieces
        BoardView board = boardModel.getBoardView(); // create the view for the template

        vm.put( "board", board );
        vm.put( "viewMode", ViewMode.PLAY );
        vm.put( CURRENT_PLAYER, currentPlayer );
        if( isFirstPlayer ) {
            vm.put( "redPlayer", currentPlayer );
            vm.put( "whitePlayer", opponent );
        }
        else {
            vm.put( "redPlayer", opponent );
            vm.put( "whitePlayer", currentPlayer );
        }
        vm.put( "activeColor", Piece.Color.RED );

        return templateEngine.render( new ModelAndView( vm, "game.ftl" ) );
    }
}
