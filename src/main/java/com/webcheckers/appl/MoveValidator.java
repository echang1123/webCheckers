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


    //TODO make methods private that can be!

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

    /**
     * Function that checks if the Move is a valid single jump move
     * @param move the Move to check
     * @return true if the move is a valid jump move, false otherwise
     */
    public boolean isJumpMove( Move move ){

        //jumping to the right

        //jumping to the left

        //if king piece- opposite directions ok too

        return false;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public boolean singlePieceJumpAvailable( int row, int col, Board board ){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        //if you are Red and a diagonally adjacent space has a white piece
        if( currentPlayerColor == Piece.Color.RED ){
            if ( hasOpponentPiece( row + 1, col-1, Piece.Color.WHITE ) ){
                Space destination = board.getSpace( row + 2, col -2);
                return destination.getPiece() == null;
            }
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.WHITE )){
                Space destination = board.getSpace( row + 2, col + 2);
                return destination.getPiece() == null;
           }
        }
        //currentPlayer is WHITE, check if diagonally adj. spaces have a red piece, and next diag space is empty
        else if( currentPlayerColor == Piece.Color.WHITE ){
            if( hasOpponentPiece( row - 1, col-1, Piece.Color.RED )){
                Space destination = board.getSpace( row - 2, col - 2);
                return destination.getPiece() == null;
            }
            if ( hasOpponentPiece( row - 1, col +1 , Piece.Color.RED ) ){
                Space destination = board.getSpace( row - 2, col + 2);
                return destination.getPiece() == null;
            }
        }
        return false;
    }

    private boolean hasOpponentPiece( int row, int col, Piece.Color color ){
        Board board = game.getBoard();
        return board.getSpace( row, col ).getPiece().getColor() == color;
    }

    /**
     *
     * @param row
     * @param col
     * @return
     */
    public boolean kingPieceJumpAvailable( int row, int col, Board board){
        Piece.Color currentPlayerColor = getCurrentPlayerColor();

        //if you are White and a diagonally adjacent space has a red piece
        if( currentPlayerColor == Piece.Color.WHITE ){
            if ( hasOpponentPiece( row + 1, col-1, Piece.Color.RED ) ){
                Space destination = board.getSpace( row + 2, col -2);
                return destination.getPiece() == null;
            }
            if ( hasOpponentPiece( row + 1, col + 1, Piece.Color.RED )){
                Space destination = board.getSpace( row + 2, col + 2);
                return destination.getPiece() == null;
            }
        }
        //currentPlayer is red, check if diagonally adj. spaces have a white piece, and next diag space is empty
        else if( currentPlayerColor == Piece.Color.RED ){
            if( hasOpponentPiece( row - 1, col-1, Piece.Color.WHITE )){
                Space destination = board.getSpace( row - 2, col - 2);
                return destination.getPiece() == null;
            }
            if ( hasOpponentPiece( row - 1, col +1 , Piece.Color.WHITE ) ){
                Space destination = board.getSpace( row - 2, col + 2);
                return destination.getPiece() == null;
            }
        }
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

        //if one or move jump move(s) available, player must select one
        if( jumpMoveAvailable() ){
            //player must submit a jump move if one is available
            if( !isJumpMove( move )){
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
