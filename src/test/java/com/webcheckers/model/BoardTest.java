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
    private static final  int row = 2;
    private static final  int col = 3;
    private Board board;

    @BeforeEach
    public void test_constructor(){
        board = new Board();
        assertNotNull(board);
    }
    @Test
    public void getSpaceTest(){
        assertNotNull(board.getSpace(row,col));
    }
}
