/**
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
  private Boolean inGame;
  private Player opponent;

  /**
   * Constructor for the Player class
   * @param name the name of the player
   */
  public Player( String name ) {
    this.name = name;
    inGame = false;
    opponent = null;
  }


  /**
   * Getter for the player's name
   * @return the name of the player
   */
  public String getName() {
    return this.name;
  }

}
