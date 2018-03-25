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
	 * Constructor for the Global Information class
	 */
	public GlobalInformation() {
		this.playerLobby = new PlayerLobby();
		this.gameLobby = new GameLobby();
	}


	/**
	 * Getter for the player lobby
	 * @return the player lobby
	 */
	public PlayerLobby getPlayerLobby() {
		return this.playerLobby;
	}


	/**
	 * Getter for the game lobby
	 * @return the game lobby
	 */
	public GameLobby getGameLobby() {
		return this.gameLobby;
	}

}
