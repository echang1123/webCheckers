/*
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
    private Iterator< Space > spaceIterator;


    /**
     * Constructor for the Row class
     *
     * @param index the index of the row
     */
    public Row( int index ) {
        this.index = index;
        this.spaces = new ArrayList<>();
        this.spaceIterator = null;
    }


    /**
     * Getter for the row index
     *
     * @return the index of the row
     */
    public int getIndex() {
        return this.index;
    }


    /**
     * Sets the iterator for the rows
     * NOTE
     * IF THIS FUNCTION IS NOT CALLED, THE ITERATOR WILL BE NULL!
     *
     * @param forFirstPlayer boolean whether for first player
     */
    public void setSpaceIterator( boolean forFirstPlayer ) {
        if( forFirstPlayer )
            this.spaceIterator = this.spaces.iterator();
        else
            this.spaceIterator = new ReverseIterator<>( this.spaces );
    }


    /**
     * Override of the iterator function since we are implementing Iterable
     * Returns an iterator for the spaces of the row
     * Since spaces is an ArrayList, we can just return it's iterator
     *
     * @return the iterator for the row spaces
     */
    @Override
    public Iterator< Space > iterator() {
        return this.spaceIterator;
    }


    /**
     * A function to add a Space to a row at a specified index
     *
     * @param index the index to add the Space at
     * @param space the Space to add to
     */
    public void add( int index, Space space ) {
        this.spaces.add( index, space );
    }


}
