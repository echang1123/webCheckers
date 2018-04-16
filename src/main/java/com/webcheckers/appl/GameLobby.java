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
    }


    /**
     * Function that adds a Game to the Game Lobby
     *
     * @param game the game to add
     * @return if added successfully
     */
    public boolean addGame( Game game ) {
        if( this.gamesInProgress.contains( game ) ) {
            return false;
        }
        this.gamesInProgress.add( game );
        return true;
    }


    /**
     * Removes a game from the game lobby if it exists
     *
     * @param game the game to remove
     * @return boolean whether the game was successfully removed from the lobby
     */
    public boolean removeGame( Game game ) {
        if( this.gamesInProgress.contains( game ) ) {
            this.gamesInProgress.remove( game );
            return true;
        }
        return false;
    }


    /**
     * Function that goes through all the gamesInProgress and finds a game that the player is part of
     *
     * @param player the player
     * @return the game that the player is in, or null if no such game exists
     */
    public Game findGame( Player player ) {
        for( Game game : this.gamesInProgress ) {
            if( game.contains( player ) ) {
                if( !game.isComplete() )
                    return game;
            }
        }
        return null;
    }


}
