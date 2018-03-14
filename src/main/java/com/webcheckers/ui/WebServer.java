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

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

import com.google.gson.Gson;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.TemplateEngine;


public class WebServer {
  private static final Logger LOG = Logger.getLogger( WebServer.class.getName() );

  // Keys
  public static final String HOME_URL = "/";
  public static final String SIGN_URL = "/signin";
  public static final String SIGN_OUT_URL = "/signout";
  public static final String INGAME_URL = "/board";

  // Attributes
  private final TemplateEngine templateEngine;
  private final Gson gson;
  private final PlayerLobby playerLobby;


  /**
   * The constructor for the Web Server.
   * @param templateEngine The default {@link TemplateEngine} to render page-level HTML views.
   * @param gson  The Google JSON parser object used to render Ajax responses.
   * @throws NullPointerException If any of the parameters are {@code null}.
   */
  public WebServer( final TemplateEngine templateEngine, final Gson gson, PlayerLobby playerLobby ) {
    // validation
    Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
    Objects.requireNonNull( gson, "gson must not be null" );
    Objects.requireNonNull( playerLobby, "playerLobby must not be null" );

    this.templateEngine = templateEngine;
    this.gson = gson;
    this.playerLobby = playerLobby;
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
    get( HOME_URL, new GetHomeRoute( templateEngine, playerLobby ) );
    get( SIGN_URL, new GetSignInRoute( templateEngine, playerLobby ) );
    post( SIGN_URL, new PostSignInRoute( templateEngine, playerLobby ) );
    get( SIGN_OUT_URL, new GetSignOutRoute( playerLobby ) );
    get( INGAME_URL, new GetBoardRoute( templateEngine, playerLobby ) );
    LOG.config( "WebServer is initialized." );
  }

}