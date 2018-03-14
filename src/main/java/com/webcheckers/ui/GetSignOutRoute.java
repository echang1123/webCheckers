/*
 * GET "/signout" route handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Hongda Lin
 */


package com.webcheckers.ui;


import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import spark.*;


import java.util.Objects;
import java.util.logging.Logger;


public class GetSignOutRoute implements Route{

    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
    private final PlayerLobby playerLobby;


    /**
     * Constructor for the GetSignOutRoute route handler
     * @param playerLobby the player lobby
     */
    public GetSignOutRoute( final PlayerLobby playerLobby ) {
        // validation
        Objects.requireNonNull( playerLobby, "playerLobby must not be null" );
        this.playerLobby = playerLobby;
        LOG.config( "GetSignOutRoute is initialized." );
    }


    /**
     * Render the signed-in WebCheckers Home page.
     * @param request the HTTP request
     * @param response  the HTTP response
     * @return the rendered HTML for the Home page (redirects to GetHomeRoute )
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetSignOutRoute is invoked." );

        final Session session = request.session();
        String username = session.attribute( RoutesAndKeys.CURRENT_PLAYER );
        playerLobby.removePlayer( username );
        session.attribute( RoutesAndKeys.SIGNED_IN,false );
        response.redirect( RoutesAndKeys.HOME_URL );
        return null;
    }
}
