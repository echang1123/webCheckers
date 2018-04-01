/*
 * Class that represents a Player
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Hongda Lin
 */


package com.webcheckers.model;

public class Player {

    // attributes
    private String name; // the name of the player
    private Boolean isInGame;
    private Player opponent;


    /**
     * Constructor for the Player class
     *
     * @param name the name of the player
     */
    public Player( String name ) {
        this.name = name;
        isInGame = false;
        opponent = null;
    }


    /**
     * Getter for the player's name
     *
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }


    /**
     * Add an opponent to the current player, and logically make isInGame true
     *
     * @param opponent the opponent to add
     * @return true if added successfully, false if already in game
     */
    public Boolean addOpponent( Player opponent ) {
        if( this.isInGame ) { // already in a game, has an opponent
            return false;
        }
        this.isInGame = true;
        this.opponent = opponent;
        return true;
    }


    /**
     * Getter for the player's opponent
     *
     * @return the opponent
     */
    public Player getOpponent() {
        return this.opponent;
    }


    /**
     * Helper function that removes the player's opponent, and sets isInGame to false
     */
    public void removeOpponent() {
        if( this.isInGame ) {
            this.opponent = null;
            this.isInGame = false;
        }
    }


    /**
     * Override the default equals to check player name
     *
     * @param o the object to compare with (Player)
     * @return whether has the same username or not
     */
    @Override
    public boolean equals( Object o ) {
        if( o instanceof Player ) {
            Player player = ( Player ) o;
            return this.name.equals( player.getName() );
        }
        return false;
    }


    /**
     * Override the default hashCode method to use only the player's username
     *
     * @return the hashcode that is representative of this player object
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
