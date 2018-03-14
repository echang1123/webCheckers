/*
 * GET "/signin" route handler
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


public class GetSignInRoute implements Route{

    // Attributes
    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    /**
     * Constructor for the GetSignInRoute route handler
     * @param templateEngine the HTML template rendering engine
     */
    public GetSignInRoute( final TemplateEngine templateEngine, final PlayerLobby playerLobby ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( playerLobby, "playerLobby must not be null" );

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        LOG.config( "GetSignInRoute is initialized." );
    }


    /**
     * Render the sign-in page
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetSignInRoute is invoked." );
        //
        Map< String, Object > vm = new HashMap<>();
        vm.put( "title", "Welcome!" );
        return templateEngine.render( new ModelAndView( vm , "signin.ftl" ) );
    }
}
