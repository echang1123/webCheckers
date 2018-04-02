package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Model-tier")
public class MoveTest {
    //Attributes
    private static final int row = 2;
    private static final int cell = 3;
    private static final int row1 = 3;
    private static final int cell1 = 4;

    private  Position start = new Position(row,cell);
    private  Position end = new Position(row1,cell1);
    private  Move.MoveType moveType = Move.MoveType.SIMPLE;
    private  Move move = new Move(start,end);

    @Test
    public void test_Move(){
        assertNotNull(move);
    }
    @Test
    public void test_GetStart(){
        assertEquals(move.getStart(),start);
        assertNotNull(move.getStart());
    }
    @Test
    public void test_GetEnd(){
        assertEquals(move.getEnd(),end);
        assertNotNull(move.getEnd());
    }
    @Test
    public void test_GetMoveType(){
        move.setMoveType(moveType);
        assertNotNull(move.getMoveType());
        assertEquals(move.getMoveType(),moveType);
    }
}
