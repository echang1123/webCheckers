/*
 * Class that represents a position on the checkers board
 * Contains the row index and the space ( cell ) index
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

public class Position {

    private int row; // the index of the row
    private int cell; // the index of the space ( cell )


    /**
     * Constructor for the Position class
     *
     * @param row  the index of the row
     * @param cell the index of the space ( cell )
     */
    public Position( int row, int cell ) {
        this.row = row;
        this.cell = cell;
    }


    /**
     * Getter for the row index
     *
     * @return the row index of the position
     */
    public int getRow() {
        return this.row;
    }


    /**
     * Getter for the space ( cell ) index
     *
     * @return the space ( cell ) index of the position
     */
    public int getCell() {
        return this.cell;
    }

}
