/*
 * POST "/signin" Route Handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Player;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class PostSignInRoute implements Route{

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;

    /**
     * Constructor for the PostSignInRoute route handler
     * @param templateEngine the template engine to render the HTML template
     * @param gi the Global Information
     */
    public PostSignInRoute( TemplateEngine templateEngine, final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "GI must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
        LOG.config( "PostSignInRoute is initialized." );
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
        LOG.finer( "PostSignInRoute is invoked." );

        // construct the view model
        final Map< String, Object > vm = new HashMap<>();
        final PlayerLobby playerLobby = gi.getPlayerLobby();
        vm.put( "title", "Sign In" );
        final Session session = request.session();
        final String userName = request.queryParams( "name" );
        if( userName.isEmpty() ) {
            String msg = "You must enter a username.";
            vm.put( "message", msg ); // add the error message
            return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
        }
        else {
            Player player = new Player( userName );
            if( playerLobby.addPlayer( player, vm ) ) { // try adding the username to the hash table
                session.attribute( RoutesAndKeys.SIGNED_IN_KEY, true );
                session.attribute( RoutesAndKeys.CURRENT_PLAYER_KEY, userName );
                response.redirect( RoutesAndKeys.HOME_URL );
            }
            else { // didn't work!
                return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
            }
        }
        return null;
    }
}
