package com.webcheckers.ui;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Eugene on 3/1/2018.
 */
public class PostSignInRoute implements Route{

    static final String PLAYER_LOBBY_KEY = "playerLobby";
    static final String SIGNED_IN = "isSignedIn";
    static final String CURRENT_PLAYER = "currentPlayer";
    static final String PLAYERS = "players";

    private static final Logger LOG = Logger.getLogger(PostSignInRoute.class.getName());
    private final TemplateEngine templateEngine;
    private HashMap<String, Object> players;

    public PostSignInRoute(TemplateEngine templateEngine, final HashMap<String,Object> players) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(templateEngine, "players must not be null");
        //
        this.templateEngine = templateEngine;
        this.players = players;
        //
        LOG.config("PostSignInRoute is initialized.");
    }
    @Override
    public Object handle( Request request, Response response ) {

        final Map< String, Object > vm = new HashMap<>();
        vm.put("title", "Sign In");
        final Session session = request.session();
        final String userName = request.queryParams("name");
        if( userName.isEmpty() ) {
            String msg = "You must enter a username.";
            vm.put( "message", msg) ;
            return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
        }
        else {
            PlayerLobby playerLobby = session.attribute( PLAYER_LOBBY_KEY );
            Player player = new Player( userName );
            playerLobby.addPlayer( player );
            session.attribute( PLAYER_LOBBY_KEY, playerLobby );
            session.attribute( SIGNED_IN, true );
            session.attribute( CURRENT_PLAYER, userName );
            response.redirect( WebServer.HOME_URL );
        }

        return null;
    }
}
