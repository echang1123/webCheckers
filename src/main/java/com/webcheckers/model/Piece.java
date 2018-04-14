/*
 * Class that represents a Piece on the board
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

public class Piece {

    // Enums
    public enum PieceType { SINGLE, KING } // type of the piece

    public enum Color {RED, WHITE} // color of the piece

    // Attributes
    private PieceType pieceType; // the type of piece
    private Color color; // the color of the piece


    /**
     * Constructor for the Piece class
     *
     * @param pieceType the type of the class
     * @param color     the color of the piece
     */
    public Piece( PieceType pieceType, Color color ) {
        this.pieceType = pieceType;
        this.color = color;
    }


    /**
     * Getter for the type of the piece
     *
     * @return the type of the piece
     */
    public PieceType getType() {
        return this.pieceType;
    }


    /**
     * Getter for the color of the piece
     *
     * @return the color of the piece
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Setter for the color
     *
     * @param color the piece color
     */
    public void setColor( Piece.Color color ) {
        this.color = color;
    }


    /**
     * Setter for the piece type
     *
     * @param pieceType the new piece type
     */
    public void setPieceType( Piece.PieceType pieceType ) {
        this.pieceType = pieceType;
    }


    /**
     * Override the toString to return a String that is useful
     *
     * @return a String representation of a Piece object
     */
    @Override
    public String toString() {
        return "Piece {type: " + this.pieceType + ", color: " + this.color + "}";
    }


}
