/**
 * Class that handles player sign-in and sign-out
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 */


package com.webcheckers.appl;

import com.webcheckers.model.Player;
import java.util.HashMap;
import java.util.logging.Logger;


public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());
    private HashMap<String, Object> players;


    /**
     * Constructor for a PlayerLobby
     * @param players the hashtable of all signed-in players
     */
    public PlayerLobby (final HashMap<String,Object> players) {
        this.players = players;
        LOG.fine("New player lobby instance created.");
    }


    /**
     * Adds a player to the hash table
     * Ideally used ONLY for sign in
     * @param player the player to add ( sign in )
     */
    public void addPlayer( Player player ){
        players.put( player.getName(), player );
    }


    /**
     * Function that removes a player from the hash table
     * Ideally to be used ONLY for sign out
     * @param player the player to remove ( sign out )
     */
    public void removePlayer( Player player ){
        if ( players.containsKey( player.getName() ) ) {
            players.remove( player.getName() );
        }
    }
}
