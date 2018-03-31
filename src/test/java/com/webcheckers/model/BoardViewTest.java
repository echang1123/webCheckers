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

public class BoardViewTest {
    //attributes
    private Board board;
    private BoardView boardView = new BoardView(board,false);
    private Row row;

    @Test
    public void  test_BoardVeiw(){
        assertNotNull(boardView,"The board is exist");
    }
    @Test
    public void test_Iterator(){
        assertNotNull(boardView.iterator());
    }
}
