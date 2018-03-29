/*
 * The server that initializes the set of HTTP request handlers.
 * This defines the web application interface for this
 * WebCheckers application.
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers.ui;


import static spark.Spark.*;


import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.JsonUtils;
import com.webcheckers.appl.RoutesAndKeys;
import spark.TemplateEngine;


public class WebServer {

    private static final Logger LOG = Logger.getLogger( WebServer.class.getName() );
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;


    /**
     * The constructor for the Web Server.
     * @param templateEngine The default {@link TemplateEngine} to render page-level HTML views.
     * @param gi the Global Information
     * @throws NullPointerException If any of the parameters are {@code null}.
     */
    public WebServer( final TemplateEngine templateEngine, GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "playerLobby must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
    }


    /**
     * Initialize all of the HTTP routes that make up this web application.
     * Initialization of the web server includes defining the location for static
     * files, and defining all routes for processing client requests. The method
     * returns after the web server finishes its initialization.
     */
    public void initialize() {

        // Configuration to serve static files
        staticFileLocation( "/public" );

        // Set up the route handlers
        get( RoutesAndKeys.HOME_URL, new GetHomeRoute( templateEngine, gi ) );
        get( RoutesAndKeys.SIGN_IN_URL, new GetSignInRoute( templateEngine, gi ) );
        post( RoutesAndKeys.SIGN_IN_URL, new PostSignInRoute( templateEngine, gi ) );
        get( RoutesAndKeys.SIGN_OUT_URL, new GetSignOutRoute( gi ) );
        get( RoutesAndKeys.GAME_URL, new GetGameRoute( templateEngine, gi ) );
        post( RoutesAndKeys.VALIDATE_MOVE_URL, new PostValidateMoveRoute( gi ), JsonUtils.json() );
        post( RoutesAndKeys.CHECK_TURN_URL, new PostCheckTurn( gi ) );

        LOG.config( "WebServer is initialized." );
    }

}