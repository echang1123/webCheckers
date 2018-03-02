/**
 * This class represents a Row on the BoardView (for the template)
 * 
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
   * @param index the index of the row
   */
  public Row( int index ) {
    this.index = index;
  }


  /**
   * Getter for the row index
   * @return the index of the row
   */
  public int getIndex() {
    return this.index;
  }


  /**
   * Override of the iterator function since we are implementing Iterable
   * Returns an iterator for the spaces of the row
   * Since spaces is an ArrayList, we can just return it's iterator
   * @return the iterator for the row spaces
   */
  @Override
  public Iterator< Space > iterator() {
    return this.spaces.iterator();
  }


  /**
   * A function to add a Space to a row at a specified index
   * @param index the index to add the Space at
   * @param space the Space to add to
   */
  public void add( int index, Space space ) {
    this.spaces.add( index, space );
  }


}
