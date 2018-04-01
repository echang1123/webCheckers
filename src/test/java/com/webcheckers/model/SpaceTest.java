package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Created by Eugene on 3/15/2018.
 */
@Tag("Model-tier")
public class SpaceTest {
    private Space s = new Space(0,null,false);

    @Test
    public void test_spaceExists(){
        assertNotNull(s, "The Space exists");
    }

    @Test
    public void test_setPiece(){
        s.setPiece(new Piece(Piece.PieceType.SINGLE, Piece.Color.RED));
        assertNotNull(s.getPiece(), "The Piece Exists after setPiece()");
    }

    @Test
    public void test_isValid(){
        assertFalse(s.isValid().booleanValue(),"Is Valid works correctly");
    }

}
