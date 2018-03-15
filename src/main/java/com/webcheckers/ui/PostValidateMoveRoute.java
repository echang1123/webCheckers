/*
 * POST "/validateMove" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.appl.JsonUtils;
import spark.Request;
import spark.Response;
import spark.Route;


public class PostValidateMoveRoute implements Route {

	private final JsonUtils jsonUtils;

	/**
	 * Constructor for the PostValidateMoveRoute route handler
	 * @param jsonUtils the JsonUtils object
	 */
	public PostValidateMoveRoute( JsonUtils jsonUtils ) {
		this.jsonUtils = jsonUtils;
	}


	/**
	 * Handles the POST "/validateMove" request
	 * @param request the request
	 * @param response the response
	 * @return the Object containing the return data
	 */
	@Override
	public Object handle( Request request, Response response ) {
		final String dataString = request.body();
		return null;
	}
}
