/*
 * Class that keeps track of all gamesInProgress in play
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.appl;


import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.ArrayList;
import java.util.logging.Logger;


public class GameLobby {
    // Attributes
    private static final Logger LOG = Logger.getLogger( PlayerLobby.class.getName() );
    private ArrayList< Game > gamesInProgress; // the set of games in progress
    private ArrayList< Game > completedGames; // the set of completed games


    /**
     * Constructor for the GameLobby class
     */
    public GameLobby() {
        this.gamesInProgress = new ArrayList<>();
        this.completedGames = new ArrayList<>();
    }


    /**
     * Function that adds a Game to the Game Lobby
     *
     * @param game the game to add
     * @return if added successfully
     */
    public boolean addGame( Game game ) {
        //if( this.gamesInProgress.contains( game ) ) {
          //  return false;
        //}
        this.gamesInProgress.add( game );
        return true;
    }


    /**
     * Removes a game from the game lobby if it exists and moves it to completed games
     *
     * @param game the game to move
     * @return boolean whether the game was successfully moved
     */
    public boolean moveGameToCompleted( Game game ) {
        if( this.gamesInProgress.contains( game ) ) {
            int gameIndex = this.gamesInProgress.indexOf( game );
            this.completedGames.add( this.gamesInProgress.remove( gameIndex ) );
            return true;
        }
        return false;
    }


    /**
     * Function that goes through all the gamesInProgress and finds the latest game that the player is part of
     *
     * @param player the player
     * @return the game that the player is in, or null if no such game exists
     */
    public Game findGame( Player player ) {
        for( int i = this.gamesInProgress.size() - 1; i >= 0; i-- ) {
            Game game = this.gamesInProgress.get( i );
            if( game.contains( player ) ) {
                return game;
            }
        }
        return null;
    }


    /**
     * Method that returns all the games that a player is part of
     *
     * @param player the player
     * @return list of games
     */
    public ArrayList< Game > findGames( Player player ) {
        ArrayList< Game > games = new ArrayList<>();
        for( int i = this.gamesInProgress.size() - 1; i >= 0; i-- ) {
            Game game = this.gamesInProgress.get( i );
            if( game.contains( player ) ) {
                games.add( game );
            }
        }
        return games;
    }


}
