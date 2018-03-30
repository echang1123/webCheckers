/*
 * Class that will validate a given Move
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 */


package com.webcheckers.appl;


import com.webcheckers.model.*;


public class MoveValidator {

    // Attribute
    private Game game;


    //TODO make methods that can be private private!

    /**
     * Constructor for the Move Validator
     * @param game the Game for which moves must be validated
     */
    public MoveValidator( Game game ) {
        this.game = game;
    }

    /**
     * Function that checks if the Move is a valid simple move
     * @param move the Move to check
     * @return true if the move is a valid simple move, false otherwise
     */
    public boolean isSimpleMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        //first player's turn
        if (game.getWhoseTurn() == 0) {
            return ( end.getRow() - start.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }

        //second player's turn
        else{
            return ( start.getRow() - end.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }

        // if the end row is one greater than the start row, and
        // the end column is adjacent to start column,
        // it is a valid simple move
    }

    /**
     * Function that checks if the Move is a valid King move (moving backwards, no jumping involved)
     * @param move the Move to check
     * @return true if the move is a valid King move, false otherwise
     */
    public boolean isKingMove( Move move ){
        Position start = move.getStart();
        Position end = move.getEnd();
        Space s = game.getBoard().getSpace(start.getRow(), start.getCell());
        //if the space has a king piece
        Piece.PieceType type = s.getPiece().getType();
        if( type.equals(Piece.PieceType.KING) ) {
            return ( Math.abs( end.getRow() - start.getRow() ) == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }
        return false;
    }

    private boolean isUpJumpMove( int rowStart, int rowEnd, int columnStart, int columnEnd ){
        //left jump
        if( ( rowEnd == rowStart + 2 ) && ( columnEnd == columnStart - 2 ) ){
            return true;
        }
        //right jump
        if( ( rowEnd == rowStart + 2 ) && ( columnEnd == columnStart + 2)){
            return true;
        }
        return false;
    }

    private boolean isDownJumpMove( int rowStart, int rowEnd, int columnStart, int columnEnd ){
        //left jump
        if( (rowEnd == rowStart - 2 ) && ( columnEnd == columnStart - 2) ){
            return true;
        }
        //right jump
        if( (rowEnd == rowStart - 2 ) && ( columnEnd == columnStart + 2) ){
            return true;
        }
        return false;
    }

    /**
     * Function that checks if the Move is a valid single jump move
     * @param move the Move to check
     * @return true if the move is a valid jump move, false otherwise
     */
    public boolean isSimpleJumpMove( Move move ){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        int rowStart = move.getStart().getRow();
        int rowEnd = move.getEnd().getRow();
        int columnStart = move.getStart().getCell();
        int columnEnd = move.getEnd().getCell();

        if( currentPlayerColor == Piece.Color.RED  && isUpJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ){
            //jumping forward on the board is "up" for RED (from row # perspective)
            return true;
        }
        if( currentPlayerColor == Piece.Color.WHITE && isDownJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ){
            //jumping forward on the board is "down" for WHITE (from row # perspective)
            return true;
        }
        return false;
    }

    /**
     *
     * @param move
     * @return
     */
    public boolean isKingJumpMove( Move move ){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        int rowStart = move.getStart().getRow();
        int rowEnd = move.getEnd().getRow();
        int columnStart = move.getStart().getCell();
        int columnEnd = move.getEnd().getCell();

        if( currentPlayerColor == Piece.Color.WHITE && isUpJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ){
            //jumping backward on the board is "up" for white kings (from row # perspective)
            return true;
        }
        if( currentPlayerColor == Piece.Color.RED && isDownJumpMove( rowStart, rowEnd, columnStart, columnEnd ) ){
            //jumping backward on the board is "down" for red kings (from row # perspective)
            return true;
        }
        return false;
    }

    /**
     * Method to determine if the piece at (row,col) can make a single forward jump move, checks both the case of a RED
     * or a WHITE piece
     * @param row row of piece that may be able to make a jump
     * @param col column of piece that may be able to make a jump
     * @param board board of the game
     * @return boolean if the piece at (row,col) can make a single forward jump
     */
    public boolean singlePieceJumpAvailable( int row, int col, Board board ){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        //if you are Red and a diagonally adjacent space has a white piece
        if( currentPlayerColor == Piece.Color.RED ){
            //check if you can jump left
            if ( hasOpponentPiece( row + 1, col-1, Piece.Color.WHITE ) ){
                if( ( row + 2 <= 7 ) && ( col - 2 >= 0 ) ){
                    Space destination = board.getSpace( row + 2, col -2);
                    return destination.getPiece() == null;
                }
            }
            //check if you can jump right
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.WHITE )){
                if( ( row + 2 <= 7 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row + 2, col + 2);
                    return destination.getPiece() == null;
                }
           }
        }
        //currentPlayer is WHITE, check if diagonally adj. spaces have a red piece, and next diag space is empty
        else if( currentPlayerColor == Piece.Color.WHITE ){
            //check if you can jump left
            if( hasOpponentPiece( row - 1, col-1, Piece.Color.RED )){
                if( ( row - 2 >= 0 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row - 2, col - 2);
                    return destination.getPiece() == null;
                }
            }
            //check if you can jump right
            if ( hasOpponentPiece( row - 1, col +1 , Piece.Color.RED ) ){
                if( ( row - 2 >= 0 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row - 2, col + 2);
                    return destination.getPiece() == null;
                }
            }
        }
        //no jump available for piece at (row, col)
        return false;
    }

    /**
     * Helper method to determine if a (row,col) position on the board has an opponent's piece on it
     * @param row row to check
     * @param col column to check
     * @param opponentColor the opponent's color, RED or WHITE
     * @return boolean, true if opponent has a piece at that position
     */
    private boolean hasOpponentPiece( int row, int col, Piece.Color opponentColor ){
        Board board = game.getBoard();
        return board.getSpace( row, col ).getPiece().getColor() == opponentColor;
    }

    /**
     * Method to determine if the piece at (row,col) can make a single backward (KING)  jump move, checks both the
     * case of a RED or a WHITE piece, opposite movement on the board of singlePieceJumpAvailable
     * @param row row of piece that may be able to make a jump
     * @param col column of piece that may be able to make a jump
     * @param board board of the game
     * @return boolean if the piece at (row,col) can make a single backward (king) jump
     */
    public boolean kingPieceJumpAvailable( int row, int col, Board board){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        //if you are White and a diagonally adjacent space has a red piece
        if( currentPlayerColor == Piece.Color.WHITE ){
            //check if you can jump left
            if ( hasOpponentPiece( row + 1, col-1, Piece.Color.RED ) ){
                if( ( row + 2 <= 7 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row + 2, col - 2);
                    return destination.getPiece() == null;
                }
            }
            //check if you can jump right
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.RED )){
                if( ( row + 2 <= 7 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row + 2, col + 2);
                    return destination.getPiece() == null;
                }
            }
        }
        //currentPlayer is red, check if diagonally adj. spaces have a white piece, and next diag space is empty
        else if( currentPlayerColor == Piece.Color.RED ){
            //check if you can jump left
            if( hasOpponentPiece( row - 1, col-1, Piece.Color.WHITE )){
                if( ( row - 2 >= 0 ) && ( col - 2 >= 0 ) ) {
                    Space destination = board.getSpace(row - 2, col - 2);
                    return destination.getPiece() == null;
                }
            }
            //check if you can jump right
            if ( hasOpponentPiece( row - 1, col +1 , Piece.Color.WHITE ) ){
                if( ( row - 2 >= 0 ) && ( col + 2 <= 7 ) ) {
                    Space destination = board.getSpace(row - 2, col + 2);
                    return destination.getPiece() == null;
                }
            }
        }
        //no jump available for piece at (row, col)
        return false;
    }


    /**
     * Gets the color of the current Player
     * @return color of the current Player
     */
    private Piece.Color getCurrentPlayerColor(){
        if( game.getWhoseTurn() == 0 ){
            return Piece.Color.RED;
        }
        else{
            return Piece.Color.WHITE;
        }
    }

    /**
     *Function that checks if the Move is a valid simple move
     * @return true is a jump move is available for that player
     */
    public boolean jumpMoveAvailable(){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();
        Board board = game.getBoard();

        //iterate through all spaces on the board and check if the space has a piece
        for (int row = 0; row < 8; row ++){
            for( int col = 0; col < 8; col++){
                Space s = board.getSpace( row, col );
                if( s.isValid() && s.getPiece() != null ){

                    //s has a piece with same color as current player
                    if( s.getPiece().getColor() == currentPlayerColor ){
                        //check if that piece is able to make a jump move
                        if( singlePieceJumpAvailable( row, col, board ) ){
                            return true;
                        }
                        if( s.getPiece().getType() == Piece.PieceType.KING && kingPieceJumpAvailable( row, col, board) ){
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
     * @param move the Move to check
     * @return boolean whether the move was legal or not
     */
    public boolean validate( Move move) {
        int pieceRow = move.getStart().getRow();
        int pieceColumn = move.getStart().getCell();
        Board board = game.getBoard();
        Piece piece = board.getSpace( pieceRow, pieceColumn ).getPiece();

        //if one or move jump move(s) available, player must select one
        if( jumpMoveAvailable() ){
            //player must submit a jump move if one is available
            if( !isSimpleJumpMove( move ) ){
                if( piece.getType() == Piece.PieceType.KING && !isKingJumpMove( move )) {
                    return false;
                }
                //if it is a king piece and it is not a king jump move
                return false;
            }
            //player submitted a jump move
            else{
                return true;
            }
        }

        //if no jump moves available, then check if isSimpleMove or isKingMove
        if( isSimpleMove( move ) || isKingMove( move )) { // it is valid simple or valid king move
            return true;
        }
        return false;
    }


}
