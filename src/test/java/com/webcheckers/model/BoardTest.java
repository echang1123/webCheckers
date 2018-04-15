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
    private static final  int row = 5;
    private static final  int col = 5;
    private static final  int row1 = 6;
    private static final int cell = 6;
    private static final int redPiecesInPlay = 12;
    private static final int whitePiecesInPlay = 12;
    final Position position = new Position(row,col);
    final Position start = new Position(row1,cell);
    private Move move= new Move(position,start); ;
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
        final Move.MoveType moveType =  Move.MoveType.JUMP;
        final Move moves1 = new Move(position,start);
        moves1.setMoveType(moveType);
        moves1.getStart();
        moves1.getEnd();
        board.doMove(moves1);
        assertNotNull(board.getSpace(position));
    }
    @Test
    public void test_getRedPiecesInPlay(){
        assertNotNull(board.getRedPiecesInPlay());
        assertEquals(board.getRedPiecesInPlay(),redPiecesInPlay);
    }
    @Test
    public void testgetWhitePiecesInPlay(){
        assertNotNull(board.getWhitePiecesInPlay());
        assertEquals(board.getWhitePiecesInPlay(),whitePiecesInPlay);
    }
}
