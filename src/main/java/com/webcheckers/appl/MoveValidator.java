package com.webcheckers.appl;

import com.webcheckers.model.Move;

/**
 * Created by Eugene on 3/15/2018.
 */
public class MoveValidator {

    //attributes
    private Move move;

    public MoveValidator(Move move){
        this.move = move;
    }
    public Boolean isLegal(){
        return false;
    }
}
