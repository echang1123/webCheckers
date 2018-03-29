/*
 * Class that will validate a given Move
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 */


package com.webcheckers.appl;


import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;


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
     * Function that checks if the Move is a valid simple move
     * @param move the Move to check
     * @return true if the move is a valid simple move, false otherwise
     */
    public boolean isSimpleMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        if (game.getWhoseTurn() == 0) { //first player's turn
            return ( end.getRow() - start.getRow() == 1 ) &&
                    ( Math.abs( end.getCell() - start.getCell() ) == 1 );
        }
        else{ //second player's turn
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
        //check if the piece is a King

        return false;
    }

    /**
     * Function that checks if the Move is a valid single jump move
     * @param move the Move to check
     * @return true if the move is a valid jump move, false otherwise
     */
    public boolean isJumpMove( Move move ){

        return false;
    }

    /**
     *Function that checks if the Move is a valid simple move
     * @return true is a jump move is available for that player
     */
    public boolean jumpMoveAvailable(){
        //check the move for the piece color, then check all of the dark squares
        // and see if any of those pieces have jump moves available


        return false;
    }

    /**
     * The main validation function, will test the move for every possible valid move and then return the result
     * @param move the Move to check
     * @return boolean whether the move was legal or not
     */
    public boolean validate( Move move) {
        if( jumpMoveAvailable() ){ //if one or move jump move(s) available, player must select one
            if( !isJumpMove( move )){
                return false;
            }
            else{ //player did submit a jump move
                return true;
            }
        }
        //first check if any jump moves available
        //If even one jump move is available, then the move parameter must be a
        //jump more or it's invalid

        //if no jump moves available, then check if isSimpleMove or isKingMove
        if( isSimpleMove( move ) || isKingMove( move )) { // it is valid simple move
            return true;
        }
        return false;
    }


}
