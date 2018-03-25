/*
 * Class that is an accumulation of all global information
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.appl;


public class GlobalInformation {

	// Attributes
	private final PlayerLobby playerLobby; // the player lobby
	private final GameLobby gameLobby; // the game lobby


	/**
	 * Constructor for the Global Information classß
	 */
	public GlobalInformation() {
		this.playerLobby = new PlayerLobby();
		this.gameLobby = new GameLobby();
	}

}
