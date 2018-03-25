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
    public Player getPlayerOne(){
	    return playerOne;
    }

    /**
     * Getter for player two
     * @return second player (WHITE)
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }

    /**
     * Returns the space at the given position
     * @return the space at that position
     */
    public Space getSpaceAt( Position position ){
        int row = position.getRow();
        int cell = position.getCell();
        return board.getSpace( row, cell );
    }


}
