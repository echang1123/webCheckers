/*
 * Move verifier class
 *
 * @author Karthik Iyer
 */


package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Space;

public class MoveVerifier {


	/**
	 * Simple Helper function that checks if two given co-ordinates are within the board
	 *
	 * @param row the row index
	 * @param col the column index
	 * @return whether (row, col) is in the board
	 */
	public boolean isWithinBounds( int row, int col ) {
		return ( ( row <= 7 ) && ( row >= 0 ) && ( col <= 7 ) && ( col >= 0 ) );
	}


	/**
	 * A simple helper method that gets the current player's color
	 *
	 * @param game the Web Checkers game
	 * @return the current player's color (red or white)
	 */
	public Piece.Color getCurrentPlayerColor( Game game ) {
		if( game.getWhoseTurn() == 0 ) {
			return Piece.Color.RED;
		} else {
			return Piece.Color.WHITE;
		}
	}


	/**
	 * Helper method that determines if a simple move "up" the board is possible geometrically; also checks that
	 * the landing cell is empty (piece == null)
	 *
	 * @param game the Web Checkers game
	 * @param row  the row index
	 * @param col  the col index
	 * @return whether it is geometrically possible
	 */
	public boolean isUpSimpleMovePossible( Game game, int row, int col ) {
		if( isWithinBounds( row + 1, col + 1 ) ) {
			Space space = game.getSpaceAt( row + 1, col + 1 );
			if( space.getPiece() == null ) {
				return true;
			}
		}
		if( isWithinBounds( row + 1, col - 1 ) ) {
			Space space = game.getSpaceAt( row + 1, col - 1 );
			if( space.getPiece() == null ) {
				return true;
			}
		}
		return false;
	}


	/**
	 * Helper method that determines if a simple move "down" the board is possible geometrically; also checks that
	 * the landing cell is empty (piece == null)
	 *
	 * @param game the Web Checkers game
	 * @param row  the row index
	 * @param col  the col index
	 * @return whether it is geometrically possible
	 */
	public boolean isDownSimpleMovePossible( Game game, int row, int col ) {
		if( isWithinBounds( row - 1, col + 1 ) ) {
			Space space = game.getSpaceAt( row + 1, col + 1 );
			if( space.getPiece() == null ) {
				return true;
			}
		}
		if( isWithinBounds( row - 1, col - 1 ) ) {
			Space space = game.getSpaceAt( row + 1, col - 1 );
			if( space.getPiece() == null ) {
				return true;
			}
		}
		return false;
	}


	/**
	 * A function that goes through all the pieces of the current player's color and determines if a simple move is
	 * possible for ANY of them
	 *
	 * @param game the Web Checkers game
	 * @return boolean whether a simple move is possible for any of the pieces
	 */
	public boolean isSimpleMoveAvailable( Game game ) {
		Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
		// First player (Red)
		// This means row must increase by one, and column varies by one
		if( currentPlayerColor.equals( Piece.Color.RED ) ) {
			for( int row = 0; row < 8; row++ ) {
				for( int col = 0; col < 8; col++ ) {
					if( isUpSimpleMovePossible( game, row, col ) ) {
						return true;
					}
				}
			}
		}
		// Second player (White)
		// This means row must decrease by one, and column varies by one
		else {
			for( int row = 0; row < 8; row++ ) {
				for( int col = 0; col < 8; col++ ) {
					if( isDownSimpleMovePossible( game, row, col ) ) {
						return true;
					}
				}
			}
		}
		// no simple moves are possible
		return false;
	}


	/**
	 * A function that goes through all the pieces of the current player color and determines if any of those pieces
	 * is a king, and if any of them can make a king move
	 *
	 * @param game the Web Checkers game
	 * @return whether a king move is possible
	 */
	public boolean isKingMoveAvailable( Game game ) {
		Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
		// First player (Red)
		// Row decreases by one, column varies by one
		if( currentPlayerColor.equals( Piece.Color.RED ) ) {
			for( int row = 0; row < 8; row++ ) {
				for( int col = 0; col < 8; col++ ) {
					if( isDownSimpleMovePossible( game, row, col ) &&
							game.getSpaceAt( row, col ).getPiece().getType() == Piece.PieceType.KING ) {
						return true;
					}
				}
			}
		}
		// Second player (White)
		// Row increases by one, column varies by one
		if( currentPlayerColor.equals( Piece.Color.WHITE ) ) {
			for( int row = 0; row < 8; row++ ) {
				for( int col = 0; col < 8; col++ ) {
					if( isUpSimpleMovePossible( game, row, col ) &&
							game.getSpaceAt( row, col ).getPiece().getType() == Piece.PieceType.KING ) {
						return true;
					}
				}
			}
		}
		// no king moves possible
		return false;
	}


	public boolean isJumpMoveAvailable( Game game ) {
		Piece.Color currentPlayerColor = getCurrentPlayerColor( game );

		if( currentPlayerColor.equals( Piece.Color.RED ) ) {

		} else {

		}

		return false;
	}


	/**
	 * The "main" method of this class, if you will. Takes in a move and verifies it.
	 *
	 * @param move the move to verify
	 * @param game the Web Checkers game
	 * @return whether the move was verified to be valid
	 */
	public boolean verifyMove( Move move, Game game ) {
		return false;
	}


}
