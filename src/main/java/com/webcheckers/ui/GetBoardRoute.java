package com.webcheckers.ui;

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

    static final String PLAYER_LOBBY_KEY = "playerLobby";
    static final String SIGNED_IN = "isSignedIn";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String PLAYERS = "players";

    private final TemplateEngine templateEngine;
    private HashMap<String, Object> players;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetBoardRoute(final TemplateEngine templateEngine, final HashMap<String,Object> players) {
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
        final Session session = request.session();
//        String username = session.attribute(CURRENT_PLAYER);
        response.redirect( WebServer.INGAME_URL );
        return null;
    }
}
