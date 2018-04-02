package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Created by Eugene on 3/15/2018.
 */
@Tag("Model-tier")
public class SpaceTest {
    private static final  int CellIdx = 0;
    private static final Piece.Color WHITE_COLOR = Piece.Color.WHITE;
    private static final Piece.PieceType SINGLE_PIECE = Piece.PieceType.SINGLE;
    private Piece piece = new Piece(SINGLE_PIECE,WHITE_COLOR);
    private Boolean isValidSpace = true;
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
    @Test
    public void test_getCellIdx(){
        assertNotNull(s.getCellIdx());
        assertEquals(s.getCellIdx(),CellIdx);
    }
    @Test
    public void test_getPiese(){
        assertNull(s.getPiece());
    }
    @Test
    public void test_getRemovePiece(){
        s.removePiece();
        assertNull(s.getPiece());
    }
}
