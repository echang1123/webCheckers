/**
 * This class contains unit tests for the Row class
 *
 * @author Emily Wesson
 */


package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model-tier")
public class RowTest {

    private int index = 0;
    private Row row;

    @BeforeEach
    public void test_constructor(){
        row = new Row(index);
        assertNotNull(row);
    }

    @Test
    public void test_getIndex(){ assertEquals( 0, row.getIndex() ); }

    @Test
    public void test_iterator(){ assertNotNull( row.iterator() ); }

    @Test
    public void test_add_space_null_piece(){
        Space s = new Space( 0, null, false );
        row.add( 0, s );
        Space zero = row.iterator().next();
        assertNotNull( zero ); //space you added
    }

    @Test
    public void test_add_space_non_null_piece(){
        Piece p = new Piece( Piece.PieceType.SINGLE, Piece.Color.RED );
        Space s = new Space( 0, null, false );
        row.add( 0, s );
        Space zero = row.iterator().next();
        assertNotNull( zero ); //space you added
    }


}
