/**
 * Class that represents a Board
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;

public class Board {

  // Attributes
  private Space spaces[][]; // the spaces


  /**
   * Constructor for the Board class
   * Automagically adds the spaces to the Board
   */
  public Board() {
    this.spaces = new Space[ 8 ][ 8 ]; // initialize ( construct ) the 2D array

    // create empty spaces
    for( int row = 0; row < 8; row++ ) {
      for( int col = 0; col < 8; col++ ) {
          if(row%2==col%2  && row<3){
            Piece redPiece= new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
            this.spaces[ row ][ col ] = new Space( col, redPiece, ( row % 2 ) == ( col % 2 ) );
          }
          if(row%2==col%2  && row>4){
            Piece whitePiece= new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
            this.spaces[ row ][ col ] = new Space( col, whitePiece, ( row % 2 ) == ( col % 2 ) );
          }
          // a space is valid ( dark ) if both the row index and the column index share the same parity ( even or odd)
      }
    }

  }


  /**
   * A function that allows you to access a Space in the Board
   * @param row the row index of the board
   * @param col the column index of the board
   * @return the Space at ( row, col ).
   */
  public Space getSpace( int row, int col ) {
    if( ( row < 0 ) || ( row > 7 ) ) {
      throw new ArrayIndexOutOfBoundsException( "The row index must be in [ 0, 7 ]" );
    }
    if( ( col < 0 ) || ( col > 7 ) ) {
      throw new ArrayIndexOutOfBoundsException( "The column index must be in [ 0, 7 ]" );
    }

    return this.spaces[ row ][ col ];
  }


  /**
   * BoardView generator for this Board (this is only there for the View)
   * The BoardView can iterate over a Collection of Rows, and Row can iterate over a Collection of Spaces
   * @return the BoardView representing this Board
   */
  public BoardView getBoardView() {
    return new BoardView( this );
  }


}
