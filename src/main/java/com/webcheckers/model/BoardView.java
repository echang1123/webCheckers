/**
 * Class that represents a BoardView to be used in the Freemarker template (game.ftl)
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardView implements Iterable< Row > {

  // Attributes
  private ArrayList< Row > rows;
  private boolean isFirst;


  /**
   * Constructor for the Board view
   * Generates a collection of rows, each having a collection of Spaces
   * @param board the Board that this BoardView needs to represent
   */
  public BoardView( Board board ) {
    this.isFirst = board.isOwnedByFirstPlayer();
    this.rows = new ArrayList<>();
    for( int row = 0; row < 8; row++ ) {
      Row newRow = new Row( row, this.isFirst );
      for( int col = 0; col < 8; col++ ) {
        newRow.add( col, board.getSpace( row, col ) );
      }
      newRow.setIterator( this.isFirst );
      rows.add( row, newRow );
    }
  }


  /**
   * Override of the iterator function since we are implementing Iterable
   * We can just use the in-built ArrayList Iterator since rows is an ArrayList
   * @return
   */
  @Override
  public Iterator< Row > iterator() {
    if( this.isFirst ) {
      return this.rows.iterator();
    }
    else {
      return new ReverseIterator<>( this.rows );
    }
  }


  /**
   * Getter for the is first
   * @return whether the board view is for the first player
   */
  public boolean getIsFirst(){ return isFirst;}

}
