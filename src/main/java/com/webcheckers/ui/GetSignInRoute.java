/*
 * GET "/signin" route handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers.ui;

import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.RoutesAndKeys;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetSignInRoute implements Route {

    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;


    /**
     * Constructor for the GetSignInRoute route handler
     *
     * @param templateEngine the HTML template rendering engine
     * @param gi             the Global Information
     */
    public GetSignInRoute( final TemplateEngine templateEngine, final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "GI must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
        LOG.config( "GetSignInRoute is initialized." );
    }


    /**
     * Render the sign-in page
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetSignInRoute is invoked." );

        if( ( request.session().attribute( RoutesAndKeys.SIGNED_IN_KEY ) != null ) &&
            ( request.session().attribute( RoutesAndKeys.SIGNED_IN_KEY ).equals( true ) ) ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        Map< String, Object > vm = new HashMap<>();
        vm.put( "title", "Welcome!" );
        return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
    }
}
