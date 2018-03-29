/*
 * Class that maintains player usernames, handles player sign in, sign out and also helps locate opponents
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
    private HashMap< String, Player > players; // usernames mapping to Player objects


    /**
     * Constructor for a PlayerLobby
     */
    public PlayerLobby() {
        this.players = new HashMap<>();
        LOG.fine( "New player lobby instance created." );
    }


    /**
     * Getter for the hash map of player usernames
     * @return the hash map of player usernames
     */
    public HashMap< String, Player > getPlayers() {
        return this.players;
    }


    /**
     * Function that returns the Player given the username
     * @param playerName the username of the player
     * @return the Player object, null if non-existent
     */
    public Player getPlayer( String playerName ) {
        return this.players.get( playerName );
    }


    /**
     * Adds a player to the hash table
     * Ideally used ONLY for sign in
     * @param player the player to add ( sign in )
     */
    public boolean addPlayer( Player player, Map< String, Object > vm ) {
        char quotes = '\"'; // to be used for checking username
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
     * @param playerName the player to remove ( sign out )
     */
    public void removePlayer( String playerName ){
        players.remove( playerName );
    }


    /**
     * Get a player's opponent if exists
     * @param player the player whose opponent we ae looking for
     * @return the opponent Player, if exists.
     * @return null otherwise
     */
    public Player findOpponent( Player player ) {
        for( String key : this.players.keySet() ) {
            Player currPlayerBeingViewed = this.players.get( key ); // current player: one of the signed in players
            if( key.equals( player.getName() ) ) { // self
                continue;
            }
            else if( currPlayerBeingViewed.getOpponent() == null ) { // no opponent
                continue;
            }
            else if( currPlayerBeingViewed.getOpponent().equals( player ) ) { // found it
                return currPlayerBeingViewed; // returns
            }
        }
        return null;
    }


}
