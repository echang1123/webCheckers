/*
 * Class that will validate a given Move
 *
 * @author Emily Wesson
 * @author Eugene Chang
 * @author Karthik Iyer
 */


package com.webcheckers.appl;


import com.webcheckers.model.*;


public class MoveValidator {

    // Attribute
    private Game game;


    /**
     * Constructor for the Move Validator
     * @param game the Game for which moves must be validated
     */
    public MoveValidator( Game game ) {
        this.game = game;
    }


    /**
     * Function that checks if the move is a valid simple move
     * @param move the move to check
     * @return true if the move is a valid simple move, false otherwise
     */
    private boolean isSimpleMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        // first player's turn
        // if the end row is one greater than the start row, and
        // the end column is adjacent to start column,
        // it is a valid simple move
        if( game.getWhoseTurn() == 0 ) {
            return ( end.getRow() - start.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }

        // second player's turn
        // if the end row is one lesser than the start row, and
        // the end column is adjacent to start column,
        // it is a valid simple move
        else{
            return ( start.getRow() - end.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }

    }


    /**
     * Function that checks if the move is a valid king move (moving backwards, no jumping involved)
     * @param move the Move to check
     * @return true if the move is a valid King move, false otherwise
     */
    private boolean isKingSimpleMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Space space = game.getBoard().getSpace( start.getRow(), start.getCell() );
        // if the space has a king piece
        Piece.PieceType type = space.getPiece().getType();
        if( type.equals( Piece.PieceType.KING ) ) {
            return ( Math.abs( end.getRow() - start.getRow() ) == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }
        return false;
    }


    /**
     * Helper function that determines if the start position and end position of a move match that of a jump move up
     * the board (in terms of row index)
     * @param rowStart the row index of the start position
     * @param rowEnd the row index of the end position
     * @param columnStart the column index of the start position
     * @param columnEnd the column index of the end position
     * @return boolean whether it matched a jump move up the board
     */
    private boolean isUpJumpMove( int rowStart, int rowEnd, int columnStart, int columnEnd ) {
        // left jump
        if( ( rowEnd == rowStart + 2 ) && ( columnEnd == columnStart - 2 ) ){
            return true;
        }
        // right jump
        if( ( rowEnd == rowStart + 2 ) && ( columnEnd == columnStart + 2)){
            return true;
        }
        return false;
    }


    /**
     * Helper function that determines if the start position and end position of a move match that of a jump move down
     * the board (in terms of row index)
     * @param rowStart the row index of the start position
     * @param rowEnd the row index of the end position
     * @param columnStart the column index of the start position
     * @param columnEnd the column index of the end position
     * @return boolean whether it matched a jump move down the board
     */
    private boolean isDownJumpMove( int rowStart, int rowEnd, int columnStart, int columnEnd ){
        // left jump
        if( ( rowEnd == rowStart - 2 ) && ( columnEnd == columnStart - 2 ) ) {
            return true;
        }
        // right jump
        if( ( rowEnd == rowStart - 2 ) && ( columnEnd == columnStart + 2 ) ) {
            return true;
        }
        return false;
    }


    /**
     * Function that checks if the move is a valid jump move
     * @param move the move to check
     * @return boolean whether the move is a valid jump move, false otherwise
     */
    private boolean isSingleJumpMove( Move move ) {

        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        int rowStart = move.getStart().getRow();
        int rowEnd = move.getEnd().getRow();
        int columnStart = move.getStart().getCell();
        int columnEnd = move.getEnd().getCell();

        if( currentPlayerColor == Piece.Color.RED  && isUpJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ) {
            // jumping forward on the board is "up" for RED (from row # perspective)
            return true;
        }
        if( currentPlayerColor == Piece.Color.WHITE && isDownJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ) {
            // jumping forward on the board is "down" for WHITE (from row # perspective)
            return true;
        }
        return false;
    }


    /**
     * Helper function that determines if a given move is a king jump move
     * it determines the color of the player making the move, and accordingly verifies the move
     * @param move the move to check
     * @return boolean whether it is a valid king jump move
     */
    private boolean isKingJumpMove( Move move ){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        int rowStart = move.getStart().getRow();
        int rowEnd = move.getEnd().getRow();
        int columnStart = move.getStart().getCell();
        int columnEnd = move.getEnd().getCell();

        if( currentPlayerColor == Piece.Color.WHITE && isUpJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ) {
            // jumping backward on the board is "up" for white kings (from row # perspective)
            return true;
        }
        if( currentPlayerColor == Piece.Color.RED && isDownJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ) {
            // jumping backward on the board is "down" for red kings (from row # perspective)
            return true;
        }
        return false;
    }


    /**
     * Method to determine if the piece at (row, col) can make a jump move, checks both the case of a RED
     * or a WHITE piece
     * @param row row of the piece that may be able to make a jump move
     * @param col column of the piece that may be able to make a jump move
     * @param board board of the game
     * @return boolean whether the piece at (row, col) can make a jump move
     */
    private boolean singleJumpAvailable( int row, int col, Board board ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        // if it is a red piece, check if there is a white piece diagonally adjacent to it
        if( currentPlayerColor == Piece.Color.RED ) {
            // check if you can jump left
            if ( hasOpponentPiece( row + 1, col - 1, Piece.Color.WHITE ) ) {
                // make sure that the index is within bounds
                if( ( row + 2 <= 7 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace( row + 2, col - 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
            // check if you can jump right
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.WHITE ) ) {
                // make sure that the index is within bounds
                if( ( row + 2 <= 7 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row + 2, col + 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
           }
        }

        // if it is a white piece, check if there is a red piece diagonally adjacent to it
        else if( currentPlayerColor == Piece.Color.WHITE ){
            // check if you can jump left
            if( hasOpponentPiece( row - 1, col - 1, Piece.Color.RED ) ) {
                // make sure that the index is within bounds
                if( ( row - 2 >= 0 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row - 2, col - 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
            // check if you can jump right
            if ( hasOpponentPiece( row - 1, col + 1 , Piece.Color.RED ) ) {
                // make sure that the index is within bounds
                if( ( row - 2 >= 0 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row - 2, col + 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
        }

        // no jump available for piece at (row, col)
        return false;
    }


    /**
     * Helper method to determine if a (row, col) position on the board has an opponent's piece on it
     * @param row row to check
     * @param col column to check
     * @param opponentColor the opponent's color, RED or WHITE
     * @return boolean whether opponent has a piece at that position
     */
    private boolean hasOpponentPiece( int row, int col, Piece.Color opponentColor ){
        Board board = game.getBoard();
        Piece piece = board.getSpace( row, col ).getPiece();
        // check that there is a piece, and that it is of the same color as the opponent
        return ( piece != null ) && ( piece.getColor() == opponentColor );
    }


    /**
     * Method to determine if the piece at (row, col) can make a backward (KING) jump move, checks both the
     * case of a RED or a WHITE piece, opposite movement on the board of singlePieceJumpAvailable
     * @param row row of piece that may be able to make a jump
     * @param col column of piece that may be able to make a jump
     * @param board board of the game
     * @return boolean if the piece at (row,col) can make a single backward (king) jump
     */
    private boolean kingJumpAvailable( int row, int col, Board board){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        // if it is a white piece, check if there is a red piece diagonally adjacent to it
        if( currentPlayerColor == Piece.Color.WHITE ) {
            // check if you can jump left
            if ( hasOpponentPiece( row + 1, col - 1, Piece.Color.RED ) ) {
                // make sure that the index is within bounds
                if( ( row + 2 <= 7 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row + 2, col - 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
            // check if you can jump right
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.RED ) ) {
                // make sure that the index is within bounds
                if( ( row + 2 <= 7 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row + 2, col + 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
        }

        // if it is a red piece, check if there is a white piece diagonally adjacent to it
        else if( currentPlayerColor == Piece.Color.RED ){
            //check if you can jump left
            if( hasOpponentPiece( row - 1, col - 1, Piece.Color.WHITE ) ) {
                // make sure that the index is within bounds
                if( ( row - 2 >= 0 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row - 2, col - 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
            //check if you can jump right
            if ( hasOpponentPiece( row - 1, col +1 , Piece.Color.WHITE ) ){
                // make sure that the index is within bounds
                if( ( row - 2 >= 0 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row - 2, col + 2 );
                    // if the landing space is empty, return true
                    return destination.getPiece() == null;
                }
            }
        }

        //no jump available for piece at (row, col)
        return false;
    }


    /**
     * A helper function that gets the color of the current player
     * @return the color of the current player
     */
    private Piece.Color getCurrentPlayerColor() {
        if( game.getWhoseTurn() == 0 ) {
            return Piece.Color.RED;
        }
        else {
            return Piece.Color.WHITE;
        }
    }


    /**
     * Function that goes through the pieces on the board and checks if a valid jump move is possible for any of them
     * It finds the pieces that are the same color as the player whose turn it is, it then checks if either a simple
     * jump move is possible or a king jump move is possible (given that the piece is a king piece)
     * @return boolean whether a jump move is available for that player
     */
    private boolean jumpMoveAvailable() {

        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        Board board = game.getBoard();

        // iterate through all spaces on the board and check if the space has a piece
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                Space space = board.getSpace( row, col );
                // make sure that the space is a valid space, and that there is a piece on the space
                if( space.isValid() && space.getPiece() != null ) {

                    // the space has a piece with the same color as the current player
                    if( space.getPiece().getColor() == currentPlayerColor ) {
                        // check if that piece is able to make a jump move
                        if( singleJumpAvailable( row, col, board ) ) {
                            return true;
                        }

                        // a jump move was not possible, check if the piece is a king piece and check
                        // if a king jump move is possible
                        if( ( space.getPiece().getType() == Piece.PieceType.KING )
                                && ( kingJumpAvailable( row, col, board ) ) ) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * The main validation function, will test the move for every possible valid move and then return the result
     * The function first determines if a jump move is available for the player. If so, then a non-jump move is not
     * legal. If there is no jump move available, then either a simple move can be made or a king simple move can be
     * made
     * @param move the Move to check
     * @return boolean whether the move was legal or not
     */
    public boolean validate( Move move) {

        // get the piece at the starting position
        Piece piece = game.getSpaceAt( move.getStart() ).getPiece();

        // if one or move jump move(s) available, player must select one
        if( jumpMoveAvailable() ) {
            // player must submit a jump move if one is available
            if( !isSingleJumpMove( move ) ) {
                // the submitted move was not a simple jump move, check if it was a king jump move
                if( ( piece.getType() == Piece.PieceType.KING ) &&
                        !isKingJumpMove( move ) ) {
                    return false;
                }
                // if it is a king piece and it is not a king jump move
                return false;
            }
            // player submitted a jump move
            else {
                return true;
            }
        }

        // if no jump moves available, then check if it is a simple move or a king simple move
        if( isSimpleMove( move ) || isKingSimpleMove( move ) ) { // it is valid simple or valid king move
            return true;
        }

        // the move didn't match any possible valid move
        return false;
    }

}
