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
public class BoardTest {
    //attribute
    private static final  int row = 7;
    private static final  int col = 7;
    private static final  int row1 = 6;
    private static  final int cell = 6;

    final Position position = new Position(row,col);
    final Position start = new Position(row1,cell);
    final Move move = new Move(start,position);
    final Space space = new Space(row,null,true);
    private Board board;

    @BeforeEach
    public void test_constructor(){
        board = new Board();
        assertNotNull(board);
    }
    @Test
    public void getSpaceTest(){
        assertNotEquals(board.getSpace(row,col),space);
        assertNotNull(board.getSpace(row,col));
    }
    @Test
    public void test_getSpaceP(){
        assertNotNull(board.getSpace(position));
        assertNotEquals(board.getSpace(position),space);
    }
    @Test
    public void test_deMove(){
        board.doMove(move);
        assertNotNull(board.getSpace(position));
    }
}
