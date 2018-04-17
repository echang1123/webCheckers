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
    private static final  int row1 = 7;
    private static final int cell = 7;
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
//    @Test
//    public void test_GetSpace(){
//        assertEquals(board.getSpace(8,8),"The row index must be in [ 0, 7 ]");
//    }
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
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.doMove(moves1);
        assertNotNull(board.getSpace(position));
    }
    @Test
    public void test_deMove2(){
        final Position start = new Position(1,1);
        final Position end = new Position(0,0);
        final Move.MoveType moveType =  Move.MoveType.SIMPLE;
        final Move moves1 = new Move(position,start);
        moves1.setMoveType(moveType);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        board.doMove(moves1);
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
