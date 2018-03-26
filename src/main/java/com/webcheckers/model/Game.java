/*
 * Class that represents a Checkers Game
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.model;


import com.webcheckers.appl.MoveValidator;

public class Game {

	// Attributes
	private Board board; // the board
	private Player playerOne; // player 1
	private Player playerTwo; // player 2
	private MoveValidator moveValidator; // a move validator

    /**
     * Constructor for the Game class
     * @param board the board for the Game
     * @param playerOne player one (RED)
     * @param playerTwo player two (WHITE)
     */
	public Game( Board board, Player playerOne, Player playerTwo ){
		this.board = board;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}


    /**
     * Getter for player one
     * @return first player (RED)
     */
    public Player getPlayerOne(){return playerOne;}


    /**
     * Getter for player two
     * @return second player (WHITE)
     */
    public Player getPlayerTwo() {return playerTwo;}


    /**
     * Returns the space at the given position
     * @return the space at that position
     */
    public Space getSpaceAt( Position position ) {
        int row = position.getRow();
        int cell = position.getCell();
        return board.getSpace( row, cell );
    }


	/**
	 * Returns the space at a given row index and cell index
	 * @param row row index
	 * @param cell cell index
	 * @return the space at that row and cell index
	 */
	public Space getSpaceAt( int row, int cell ) {
    	return board.getSpace( row, cell );
	}
    @Override
    public boolean equals(Object o) {
        if( o != null ){
            if( o instanceof Game ){
                Game g = ( Game ) o;
                return ( this.playerOne.equals( g.getPlayerOne() ) && this.playerTwo.equals( g.getPlayerTwo() ) );
            }
        }
        return false;
    }


	/**
	 * Returns a boolean for whether a given player is in the game
	 * @param player the Player to search for
	 * @return true if player given is in this Game
	 */
	public boolean contains( Player player ) {
		return playerOne.equals(player) || playerTwo.equals( player );
	}
}
