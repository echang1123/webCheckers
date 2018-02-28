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


  /**
   * Constructor for the Player class
   * @param name the name of the player
   */
  public Player( String name ) {
    this.name = name;
  }


  /**
   * Getter for the player's name
   * @return the name of the player
   */
  public String getName() {
    return this.name;
  }

}
