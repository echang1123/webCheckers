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
     * @return boolean whether it is a valid simple move
     */
    public boolean isSimpleMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        // if the end row is one greater than the start row, and
        // the end column is adjacent to start column,
        // it is a valid simple move
        return ( end.getRow() - start.getRow() == 1 ) &&
                ( Math.abs( end.getCell() - start.getCell() ) == 1 );
    }


    /**
     * The main validation function, will test the move for every possible valid move and then return the result
     * @param move the Move to check
     * @return boolean whether the move was legal or not
     */
    public boolean validate( Move move) {

        if( isSimpleMove( move ) ) { // it is valid simple move
            return true;
        }

        return false;
    }


}
