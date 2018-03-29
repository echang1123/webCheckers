/*
 * Class that represents a BoardView to be used in the Freemarker template (game.ftl)
 *
 * @author Karthik Iyer
 * @author Hongda Lin
 * @author Emily Wesson
 */


package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;

public class BoardView implements Iterable< Row > {

    // Attributes
    private ArrayList< Row > rows;
    private Iterator<Row> rowIterator;


    /**
     * Constructor for the Board view
     * Generates a collection of rows, each having a collection of Spaces, and assigning the rowIterator
     * @param board the Board that this BoardView needs to represent
     * @param forFirstPlayer boolean telling us if this boardview is for the first player
     */
    public BoardView( Board board, boolean forFirstPlayer ) {
        this.rows = new ArrayList<>( 8 );
        for( int row = 0; row < 8; row++ ) {
            Row newRow = new Row( row );
            for( int col = 0; col < 8; col++ ) {
                newRow.add( col, board.getSpace( row, col ) );
            }
            newRow.setSpaceIterator( forFirstPlayer ); // SET THE ITERATOR
            rows.add( row, newRow );
        }
        if( forFirstPlayer ) {
            this.rowIterator = new ReverseIterator<>( this.rows );
        }
        else {
            this.rowIterator = this.rows.iterator();
        }
    }


    /**
     * Override of the iterator function since we are implementing Iterable
     * @return the iterator to iterate over the rows
     */
    @Override
    public Iterator< Row > iterator() {
        return this.rowIterator;
    }


}
