package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Eugene on 3/1/2018.
 */
public class PostSignInRoute implements Route{

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
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign In");
        final Session session = request.session();
        final String userName = request.queryParams("name");
        if(userName.isEmpty()){
            String msg = new String("You must enter a username.");
            vm.put("message",msg);
            return templateEngine.render( new ModelAndView( vm, "signin.ftl" ) );
        }
        //session.at;
//        final PlayerLobby playerLobby = session.attribute("signedin", true);
        return null;
    }
}
