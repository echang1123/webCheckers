/**
 * This class represents a Row on the board
 * @author Karthik Iyer
 */


package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Row implements Iterable< Space > {

  // Attributes
  private int index; // the index of the row
  private ArrayList< Space > spaces; // the spaces in the row


  /**
   * Constructor for the Row class
   * Automagically populates the spaces in the row
   * @param index the index of the row
   */
  public Row( int index ) {
    this.index = index;

    if( index % 2 == 0 ) { // if even-index row, then even-index spaces are dark and valid
      for( int i = 0; i < 8; i++ ) {
        this.spaces.add( new Space( i, null, ( i % 2 == 0 ) ) );
      }
    }

    else { // if odd-index row, then odd-index spaces are dark and valid
      for( int i = 0; i < 8; i++ ) {
        this.spaces.add( new Space( i, null, ( i % 2 != 0 ) ) );
      }
    }
  }


  /**
   * Getter for the row index
   * @return the index of the row
   */
  public int getIndex() {
    return this.index;
  }


  /**
   * Returns an iterator for the spaces of the row
   * Since spaces is an ArrayList, we can just return it's iterator
   * @return the iterator for the row spaces
   */
  public Iterator< Space > iterator() {
    return this.spaces.iterator();
  }


}
