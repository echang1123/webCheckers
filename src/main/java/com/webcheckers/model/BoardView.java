/**
 * Class that represents a Board
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable< Row > {

  // Attributes
  private ArrayList< Row > rows;


  /**
   * Constructor for the BoardView class
   * Automagically creates and adds the rows
   */
  public BoardView() {
    for( int i = 0; i < 8; i++ ) {
      this.rows.add( new Row( i ) );
    }
  }


  /**
   * Implement the iterator function since we're implementing Iterable
   * We can just use the iterator of the rows, since it is an ArrayList
   * @return an iterator that can be used to iterate through the rows
   */
  public Iterator< Row > iterator() {
    return this.rows.iterator();
  }

}
