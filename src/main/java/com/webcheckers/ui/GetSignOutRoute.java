/**
 * GET "/signout" route handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Hongda Lin
 */


package com.webcheckers.ui;

import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetSignOutRoute implements Route{

    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );

    // Keys
    static final String PLAYER_LOBBY_KEY = "playerLobby";
    static final String SIGNED_IN = "isSignedIn";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String PLAYERS = "players";
    public static final String INGAME_URL = "/board";

    // Attributes
    private final TemplateEngine templateEngine;
    private HashMap< String, Player > players;


    /**
     * Constructor for the GetSignOutRoute route handler
     * @param templateEngine  the HTML template rendering engine
     */
    public GetSignOutRoute( final TemplateEngine templateEngine, final HashMap< String, Player > players ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        //
        this.templateEngine = templateEngine;
        this.players = players;
        //
        LOG.config( "GetSignOutRoute is initialized." );
    }


    /**
     * Render the signed-in WebCheckers Home page.
     * @param request the HTTP request
     * @param response  the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetSignOutRoute is invoked." );
        //
        Map< String, Object > vm = new HashMap<>();
        vm.put( "title", "Welcome!" );
        final Session session = request.session();
        String username = session.attribute( CURRENT_PLAYER );
        if( players.containsKey( username ) )
            players.remove(username);
        session.attribute( SIGNED_IN,false );
        response.redirect( WebServer.HOME_URL );
        return null;
    }
}
