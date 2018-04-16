/*
 * GET "/playerHelp" route handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import spark.Request;
import spark.Response;
import spark.Route;


import java.util.Objects;
import java.util.logging.Logger;


public class GetPlayerHelpRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( GetPlayerHelpRoute.class.getName() );


    /**
     * Default constructor for the GetPlayerHelpRoute route handler
     */
    public GetPlayerHelpRoute() {
        Objects.requireNonNull( LOG, "Log cannot be null" );
    }


    @Override
    public Object handle( Request request, Response response ) {
        return null;
    }

}
