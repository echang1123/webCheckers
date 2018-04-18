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
public class PositionTest {
    //Attributes
    private static final  int row = 3;
    private static final  int cell = 4;
    private static final Object jump = new Position(row,cell);
    private Position position = new Position(row,cell);

    @Test
    public void test_Position(){
        assertNotNull(position);
    }
    @Test
    public void test_getRow(){
        assertNotNull(position.getRow());
        assertEquals(position.getRow(),row);
    }
    @Test
    public void test_getCell(){
        assertNotNull(position.getCell());
        assertEquals(position.getCell(),cell);
    }
    @Test
    public void test_hasdcode(){
        position.hashCode();
    }
    @Test
    public void test_euals(){
        assertTrue(position.equals(jump));
    }
}
