/**
 * Class that represents a Piece on the board
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

public class Piece {

  // Enums
  private enum PieceType { SINGLE, KING };
  private enum Color { RED, WHITE };

  // Attributes
  private PieceType pieceType; // the type of piece
  private Color color; // the color of the piece


  /**
   * Constructor for the Piece class
   * @param pieceType the type of the class
   * @param color the color of the piece
   */
  public Piece( PieceType pieceType, Color color ) {
    this.pieceType = pieceType;
    this.color = color;
  }


  /**
   * Getter for the type of the piece
   * @return the type of the piece
   */
  public PieceType getType() {
    return this.pieceType;
  }


  /**
   * Getter for the color of the piece
   * @return the color of the piece
   */
  public Color getColor() {
    return this.color;
  }

}