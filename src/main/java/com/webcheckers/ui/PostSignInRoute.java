/**
 * POST "/signin" Route Handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostSignInRoute implements Route{

    // Keys
    static final String PLAYER_LOBBY_KEY = "playerLobby";
    static final String SIGNED_IN = "isSignedIn";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String PLAYERS = "players";
    public static final String INGAME_URL = "/board";

    // Attributes
    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private HashMap<String, Object> players;


    /**
     * Constructor for the PostSignInRoute route handler
     * @param templateEngine the template engine to render the HTML template
     * @param players the hash table of players
     */
    public PostSignInRoute(TemplateEngine templateEngine, final HashMap<String,Object> players) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(templateEngine, "players must not be null");

        this.templateEngine = templateEngine;
        this.players = players;
        LOG.config("PostSignInRoute is initialized.");
    }


    /**
     * Process the sign-in information and redirect to Home
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the sign-in page if invalid username or taken username
     * @return null if successful sign-in (redirects to "/")
     */
    @Override
    public Object handle( Request request, Response response ) {

        final Map< String, Object > vm = new HashMap<>();
        vm.put("title", "Sign In");
        final Session session = request.session();
        final String userName = request.queryParams("name");
        if( userName.isEmpty() ) {
            String msg = "You must enter a username.";
            vm.put( "message", msg); // add the error message
            return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
        }
        else {
            PlayerLobby playerLobby = session.attribute( PLAYER_LOBBY_KEY );
            Player player = new Player( userName );
            if( playerLobby.addPlayer( player, vm ) ) {
                session.attribute( SIGNED_IN, true );
                session.attribute( CURRENT_PLAYER, userName );
                response.redirect( WebServer.HOME_URL );
            }
            else {
                return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
            }
        }
        return null;
    }
}
