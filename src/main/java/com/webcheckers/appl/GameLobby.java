/*
 * Class that keeps track of all games in play
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.appl;


import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class GameLobby {

	// Attributes
	private static final Logger LOG = Logger.getLogger( PlayerLobby.class.getName() );
	private Set<Game> games; // the set of games


	/**
	 * Constructor for the GameLobby class
	 */
	public GameLobby() {
		this.games = new HashSet<>();
	}


	/**
	 * Function that adds a Game to the Game Lobby
	 * @param game the game to add
	 * @return if added successfully
	 */
	public boolean addGame( Game game ) {
		if( this.games.contains( game ) ) {
			return false;
		}
		this.games.add( game );
		return true;
	}


	/**
	 * Function that goes through all the games and finds a game that the player is part of
	 * @param player the player
	 * @return the game that the player is in, or null if no such game exists
	 */
	public Game findGame( Player player ) {
		for( Game game : this.games ) {
			if( game.contains( player ) ) {
				return game;
			}
		}
		return null;
	}


}
