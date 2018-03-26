/*
 * GET "/" Route Handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 * @author Emily Wesson
 */


package com.webcheckers.ui;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Player;
import spark.*;


public class GetHomeRoute implements Route {

    private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;


    /**
     * Constructor for the GetHomeRoute routehandler
     * @param templateEngine the HTML template rendering engine
     */
    public GetHomeRoute( final TemplateEngine templateEngine, final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "GI must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
        LOG.config( "GetHomeRoute is initialized." );
    }


    /**
     * Render the WebCheckers Home page.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetHomeRoute is invoked.") ;

        // Create the view model
        Map< String, Object > vm = new HashMap<>();
        vm.put( "title", "Welcome!" );

        // first time opening
        final Session httpSession = request.session();
        final PlayerLobby playerLobby = gi.getPlayerLobby();

        // player has not signed in
        if( ( httpSession.attribute( RoutesAndKeys.SIGNED_IN ) == null ) ||
                ( httpSession.attribute( RoutesAndKeys.SIGNED_IN ).equals( false ) ) ) {
            httpSession.attribute( RoutesAndKeys.SIGNED_IN, false );
            vm.put( RoutesAndKeys.SIGNED_IN, false );
            vm.put( RoutesAndKeys.PLAYERS, playerLobby.getPlayers() );
        }

        else { // player is signed in
            String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER );
            HashMap< String, Player > players = playerLobby.getPlayers();
            Player currentPlayer = players.get( currentPlayerName );

            Map< String, Player > otherPlayers = new HashMap<>( players ); // create a copy
            otherPlayers.remove( currentPlayerName ); // remove the current player, so doesn't get shown

            // check if you have been selected for a game
            Player opponent = playerLobby.findOpponent( currentPlayer ); // get the opponent

            if( opponent != null ) { // someone has selected you for a game
                response.redirect( RoutesAndKeys.GAME_URL );
            }

            // you have not been selected for a game, display home ( populate the vm )
            vm.put( RoutesAndKeys.CURRENT_PLAYER, currentPlayerName);
            vm.put( RoutesAndKeys.PLAYERS, otherPlayers );
            vm.put( RoutesAndKeys.SIGNED_IN, true );

        }

        // render
        return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );

    }

}