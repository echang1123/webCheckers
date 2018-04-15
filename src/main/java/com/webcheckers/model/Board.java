/*
 * Class that represents a Board
 *
 * @author Karthik Iyer
 * @author Gaurav Pant
 * @author Hongda Lin
 * @Emily Wesson
 */


package com.webcheckers.model;

public class Board {

    // Attributes
    private Space spaces[][]; // the spaces
    private int redPiecesInPlay;
    private int whitePiecesInPlay;

    /**
     * Constructor for the Board class
     * Automagically adds the Spaces and Pieces to the Board
     */
    public Board() {
        this.spaces = new Space[ 8 ][ 8 ]; // initialize ( construct ) the 2D array
        this.redPiecesInPlay = 12;
        this.whitePiecesInPlay = 12;
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
     *
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
     *
     * @param position the position on the board
     * @return the space at the specified position
     */
    public Space getSpace( Position position ) {
        return this.getSpace( position.getRow(), position.getCell() );
    }

    /**
     * Getter for red piece count
     *
     * @return number of red pieces still in play
     */
    public int getRedPiecesInPlay() {
        return this.redPiecesInPlay;
    }

    /**
     * Getter for white piece count
     *
     * @return number of white pieces still on the board
     */
    public int getWhitePiecesInPlay() {
        return this.whitePiecesInPlay;
    }


    /**
     * Function that updates the board to reflect a move that has been done (submitted)
     *
     * @param move the move to 'do'
     */
    public void doMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        Piece piece = this.getSpace( end ).getPiece();

        if( ( piece.getColor() == Piece.Color.RED ) && ( end.getRow() == 7 ) ) {
            piece.setPieceType( Piece.PieceType.KING );
        }
        if( ( piece.getColor() == Piece.Color.WHITE ) && ( end.getRow() == 0 ) ) {
            piece.setPieceType( Piece.PieceType.KING );
        }

        // if it is a jump move, remove the piece in the middle (the captured piece)
        if( ( move.getMoveType() == Move.MoveType.JUMP ) ||
            ( move.getMoveType() == Move.MoveType.KING_JUMP ) ) {
            int middleRow = ( start.getRow() + end.getRow() ) / 2;
            int middleCol = ( start.getCell() + end.getCell() ) / 2;
            Piece removed = this.getSpace( middleRow, middleCol ).removePiece();
            if( removed.getColor() == Piece.Color.RED ) {
                redPiecesInPlay--;
            } else {
                whitePiecesInPlay--;
            }
        }
    }


}
