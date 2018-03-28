/*
 * Class that represents a move on the checkers board
 * Contains a start position and an end position
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;


public class Move {

    private Position start; // the position where the move begins
    private Position end; // the position where the move ends


    /**
     * Constructor for the Move class
     * @param start the starting position
     * @param end the ending position
     */
    public Move( Position start, Position end ) {
        this.start = start;
        this.end = end;
    }


    /**
     * Getter for the starting position of the move
     * @return the starting position
     */
    public Position getStart() {
        return this.start;
    }


    /**
     * Getter for the ending position of the move
     * @return the ending position
     */
    public Position getEnd() {
        return this.end;
    }
}
