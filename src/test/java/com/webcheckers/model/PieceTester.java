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
public class PieceTester {


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


	@Test
	public void getType() {
		final Piece CuT = new Piece( SINGLE_PIECE, RED_COLOR );

	}


}
