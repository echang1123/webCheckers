/*
 * Class that will validate a given Move
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 */


package com.webcheckers.appl;


import com.webcheckers.model.Move;
import com.webcheckers.model.Position;


public class MoveValidator {

    //attributes
    private Move move;


    /**
     * Constructor for the MoveValidator class
     * @param move the Move to validate
     */
    public MoveValidator( Move move ){
        this.move = move;
    }


    /**
     * Function that checks if the Move is a valid simple move
     * @return boolean whether it is a valid simple move
     */
    public boolean isSimpleMove() {
        Position start = move.getStart();
        Position end = move.getEnd();

        return ( end.getRow() - start.getRow() == 1 ) &&
                ( Math.abs( end.getCell() - start.getCell() ) == 1 );
    }


    public boolean validate() {

        if( this.isSimpleMove() ) { // it is valid simple move
            return true;
        }

        return false;
    }


}
