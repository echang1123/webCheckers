/*
 * GET "/playerHelp" route handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetPlayerHelpRoute implements Route {

    // Attributes
    private static final Logger LOG = Logger.getLogger( GetPlayerHelpRoute.class.getName() );
    private TemplateEngine templateEngine;
    private final GlobalInformation gi;
    private final String PLAYER_ONE_BOARD_IMAGE = "../../../../resources/public/img/player1-board.jpg";
    private final String PLAYER_TWO_BOARD_IMAGE = "../../../public/img/player2-board.jpg";


    /**
     * Default constructor for the GetPlayerHelpRoute route handler
     */
    public GetPlayerHelpRoute( GlobalInformation gi, TemplateEngine templateEngine ) {
        Objects.requireNonNull( LOG, "Log cannot be null" );
        Objects.requireNonNull( gi, "gi cannot be null" );
        Objects.requireNonNull( templateEngine, "template engine cannot be null" );
        this.gi = gi;
        this.templateEngine = templateEngine;
    }


    /**
     * Handler method for GET "/playerHelp"
     *
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendering by the template engine
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetPlayerHelpRoute is invoked." );

        PlayerLobby playerLobby = gi.getPlayerLobby();
        GameLobby gameLobby = gi.getGameLobby();
        Session httpSession = request.session();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_NAME_KEY );
        if( currentPlayerName == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }
        Player currentPlayer = playerLobby.getPlayer( currentPlayerName );
        if( currentPlayer == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }
        Game game = gameLobby.findGame( currentPlayer );
        if( game == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        Map< String, Object > vm = new HashMap<>();
        if( currentPlayer.equals( game.getPlayerOne() ) ) {
            vm.put( RoutesAndKeys.IMAGE_SRC, PLAYER_ONE_BOARD_IMAGE );
        }
        else {
            vm.put( RoutesAndKeys.IMAGE_SRC, PLAYER_TWO_BOARD_IMAGE );
        }

        // render
        return templateEngine.render( new ModelAndView( vm, "playerHelp.ftl" ) );
    }

}
