/*
 * POST "/validateMove" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.JsonUtils;
import com.webcheckers.model.Move;
import spark.Request;
import spark.Response;
import spark.Route;


public class PostValidateMoveRoute implements Route {


	/**
	 * Handles the POST "/validateMove" request
	 * @param request the request
	 * @param response the response
	 * @return the Object containing the return data
	 */
	@Override
	public Object handle( Request request, Response response ) {
		final String dataString = request.body();
		Move move = JsonUtils.fromJson( dataString, Move.class );

		return null;
	}
}
