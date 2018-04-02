package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Model-tier")
public class BoardViewTest {
    //attributes
    private static final int index = 0;
    private ArrayList< Row > rows = new ArrayList<Row>();
    private Iterator<Row> rowIterator ;
    private Board board = new Board();
    private BoardView boardView = new BoardView(board,true);
    private Row row;
    @Test
    public void ArrayList(){
        rowIterator = rows.iterator();
        row = new Row(index);
        rows.add(row);
    }
    @Test
    public void  test_BoardVeiw(){

        assertNotNull(boardView,"The board is exist");
        assertNotEquals(boardView,rowIterator);
    }
    @Test
    public void test_Iterator(){
        assertNotNull(boardView.iterator());
    }
}
