/*
 * Class that represents a Board
 *
 * @author Karthik Iyer
 * @author Gaurav Pant
 * @author Hongda Lin
 */


package com.webcheckers.model;

public class Board {

	// Attributes
	private Space spaces[][]; // the spaces


	/**
	 * Constructor for the Board class
	 * Automagically adds the Spaces and Pieces to the Board
     *
	 */
	public Board( ) {
		this.spaces = new Space[ 8 ][ 8 ]; // initialize ( construct ) the 2D array
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                if( ( row % 2 == col % 2 ) && ( row < 3 ) ) { // needs a red piece
                    Piece redPiece = new Piece( Piece.PieceType.SINGLE, Piece.Color.RED );
                    this.spaces[ row ][ col ] = new Space( col, redPiece, ( row % 2 ) == ( col % 2 ) );
                } else if( ( row % 2 == col % 2 ) && ( row > 4 ) ) { // needs a white piece
                    Piece whitePiece = new Piece( Piece.PieceType.SINGLE, Piece.Color.WHITE );
                    this.spaces[ row ][ col ] = new Space( col, whitePiece, ( row % 2 ) == ( col % 2 ) );
                } else {
                    this.spaces[ row ][ col ] = new Space( col, null, ( row % 2 ) == ( col % 2 ) );
                }
                // a space is valid ( dark ) if both the row index and the column index share the same parity ( even or odd )
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
     * Overloaded the getSpace function to work with position objects as well
     * @param position the position on the board
     * @return the space at the specified position
     */
	public Space getSpace( Position position ) {
	    return this.getSpace( position.getRow(), position.getCell() );
    }


    /**
     * Function that updates the board to reflect a move that has been done (submitted)
     * @param move the move to 'do'
     */
	public void doMove( Move move ) {

        Position start = move.getStart();
        Position end = move.getEnd();

        // no matter the move type we need to do the following
        // 1. transfer (copy) the piece from start to end
        // 2. remove the piece from the start position
        Piece oldPiece = this.getSpace( start ).getPiece();
        Piece newPiece = new Piece( oldPiece.getType(), oldPiece.getColor() );
        this.getSpace( end ).setPiece( newPiece );
        this.getSpace( start ).removePiece();

        // if it is a jump move, remove the piece in the middle (the captured piece)
        if( move.getMoveType() == Move.MoveType.JUMP ) {
            int middleRow = ( start.getRow() + end.getRow() ) / 2;
            int middleCol = ( start.getCell() + end.getCell() ) / 2;
            this.getSpace( middleRow, middleCol ).removePiece();
        }
    }


}
