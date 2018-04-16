/*
 * GET "/signout" route handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Hongda Lin
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import spark.*;


import java.util.Objects;
import java.util.logging.Logger;


public class GetSignOutRoute implements Route {

    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
    private final GlobalInformation gi;

    /**
     * Constructor for the GetSignOutRoute route handler
     *
     * @param gi the Global Information
     */
    public GetSignOutRoute( final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( gi, "GI must not be null" );
        this.gi = gi;
        LOG.config( "GetSignOutRoute is initialized." );
    }


    /**
     * Render the signed-in WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page (redirects to GetHomeRoute )
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetSignOutRoute is invoked." );

        final PlayerLobby playerLobby = gi.getPlayerLobby();
        if( playerLobby == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        final Session session = request.session();
        if( session == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        String username = session.attribute( RoutesAndKeys.CURRENT_PLAYER_NAME_KEY );
        if( username == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        playerLobby.removePlayer( username );
        session.attribute( RoutesAndKeys.SIGNED_IN_KEY, false );
        response.redirect( RoutesAndKeys.HOME_URL );
        return null;
    }
}
