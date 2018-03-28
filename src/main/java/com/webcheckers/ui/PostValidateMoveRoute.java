/*
 * POST "/validateMove" handler
 *
 * @author Karthik Iyer
 */


package com.webcheckers.ui;


import com.webcheckers.appl.*;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;
import java.util.logging.Logger;


public class PostValidateMoveRoute implements Route {

	// Attributes
	private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
	private GlobalInformation gi;


	/**
	 * Constructor for the PostValidateMoveRoute route handler
	 * @param gi the Global Information object
	 */
	public PostValidateMoveRoute( final GlobalInformation gi ) {
		// validation
		Objects.requireNonNull( gi, "GI cannot be null" );
		this.gi = gi;
	}


	/**
	 * Handles the POST "/validateMove" request
	 * @param request the request
	 * @param response the response
	 * @return the Object containing the return data
	 */
	@Override
	public Object handle( Request request, Response response ) {

		Session httpSession = request.session();
		PlayerLobby playerLobby = gi.getPlayerLobby();
		GameLobby gameLobby = gi.getGameLobby();

		String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_KEY );
		Player currentPlayer = playerLobby.getPlayer( currentPlayerName );

		Game game = gameLobby.findGame( currentPlayer );
		MoveValidator moveValidator = new MoveValidator( game );

		final String dataString = request.body();
		Move move = JsonUtils.fromJson( dataString, Move.class );

		return null;

	}
}
