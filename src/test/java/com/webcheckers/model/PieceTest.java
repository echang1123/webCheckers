/*
 * Tester class for Piece
 *
 * @author Karthik Iyer
 */


package com.webcheckers.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("Model-tier")
public class PieceTest {


	private static final Piece.Color RED_COLOR = Piece.Color.RED;
	private static final Piece.Color WHITE_COLOR = Piece.Color.WHITE;
	private static final Piece.PieceType SINGLE_PIECE = Piece.PieceType.SINGLE;
	private static final Piece.PieceType KING_PIECE = Piece.PieceType.KING;


	/**
	 * Tests that the constructor works without failure
	 */
	@Test
	public void ctor_withArg(){
		final Piece CuT = new Piece(SINGLE_PIECE, RED_COLOR);
		assertEquals( "Piece {type: " + SINGLE_PIECE + ", color: " + RED_COLOR + "}", CuT.toString() );
	}


	/**
	 * Tests the getter for the type
	 */
	@Test
	public void getType() {
		Piece CuT = new Piece(SINGLE_PIECE, RED_COLOR);
		assertEquals(SINGLE_PIECE, CuT.getType());
		CuT = new Piece(KING_PIECE, WHITE_COLOR); // try the other type
		assertEquals(KING_PIECE, CuT.getType());
	}


	/**
	 * Tests the getter for the color
	 */
	@Test
	public void getColor() {
		Piece CuT = new Piece(SINGLE_PIECE, RED_COLOR);
		assertEquals(RED_COLOR, CuT.getColor());
		CuT = new Piece(SINGLE_PIECE, WHITE_COLOR); // try the other color
		assertEquals(WHITE_COLOR, CuT.getColor());
	}


}
