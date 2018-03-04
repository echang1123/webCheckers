/**
 * Class that handles player sign-in and sign-out
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Emily Wesson
 */


package com.webcheckers.appl;

import com.webcheckers.model.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger( PlayerLobby.class.getName() );
    private HashMap<String, Player> players;


    /**
     * Constructor for a PlayerLobby
     * @param players the hashtable of all signed-in players
     */
    public PlayerLobby( final HashMap<String,Player> players ) {
        this.players = players;
        LOG.fine( "New player lobby instance created." );
    }


    /**
     * Adds a player to the hash table
     * Ideally used ONLY for sign in
     * @param player the player to add ( sign in )
     */
    public boolean addPlayer( Player player, Map< String, Object > vm ) {
        char quotes = '"'; // to be used for checking username
        if( player.getName().indexOf( quotes ) != -1 ) { // contains the quotes
            vm.put( "message", "Username cannot contain quotes" );
            return false;
        }
        if( players.containsKey( player.getName() ) ) {
            vm.put( "message", "The username " + player.getName() + " is already taken" );
            return false;
        }
        players.put( player.getName(), player );
        return true;
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


    /**
     * Get a player's opponent if exists
     * @param player the player whose opponent we ae looking for
     * @return the opponent Player, if exists.
     * @return null otherwise
     */
    public Player findOpponent( Player player ) {
        for( String key : this.players.keySet() ) {
            Player currPlayerBeingViewed = this.players.get( key ); //current player- one of the other signed in players
            if( key.equals( player.getName() ) ) {
                continue;
            }
            else if( currPlayerBeingViewed.getOpponent() == null ) {
                continue;
            }
            else if( currPlayerBeingViewed.getOpponent().equals( player ) ) {
                return currPlayerBeingViewed; // returns
            }
        }
        return null;
    }


}
