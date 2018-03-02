package com.webcheckers.ui;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by Eugene on 3/1/2018.
 */
public class PostSigninRoute implements Route{

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Session session = request.session();

        //session.at;
//        final PlayerLobby playerLobby = session.attribute("signedin", true);
        return null;
    }
}
