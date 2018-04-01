/*
 * Class that represents a Space
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

public class Space {

    // Attributes
    private int cellIdx; // the index of the space (on the Row) { 0...7 }
    private Piece piece; // the piece that rests on this space
    private Boolean isValidSpace; // whether this Space is valid to play on


    /**
     * Constructor for the Space class
     * @param cellIdx the index of the space
     * @param piece the piece that rests on the space
     * @param isValidSpace whether the space is valid to play on
     */
    public Space( int cellIdx, Piece piece, Boolean isValidSpace ) {
        this.cellIdx = cellIdx;
        this.piece = piece;
        this.isValidSpace = isValidSpace;
    }


    /**
     * Getter for the index of the space
     * @return the index of the space
     */
    public int getCellIdx() {
        return this.cellIdx;
    }


    /**
     * Getter for the validity of the space
     * @return whether the space is valid to play on
     */
    public Boolean isValid() {
        return this.isValidSpace;
    }


    /**
     * Getter for the piece on the space
     * @return the piece that rests on this space
     */
    public Piece getPiece() {
        return this.piece;
    }


    /**
     * Setter for the piece on the board
     * @param piece the piece to place on the space
     */
    public void setPiece( Piece piece ) {
        this.piece = piece;
    }


    /**
     * Removes the piece from this space
     */
    public Piece removePiece() {
        Piece p = this.piece;
        this.piece = null;
        return p;
    }


}
