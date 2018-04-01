/*
 * Class that represents a Checkers Game
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.model;


import com.webcheckers.appl.MoveValidator;

import java.util.ArrayList;


public class Game {

    // Attributes
    private Board board; // the board
    private Player playerOne; // player 1
    private Player playerTwo; // player 2
    private int whoseTurn; // 0 is for player1 and 1 is for player2
    private ArrayList< Move > validatedMoves; // keeps track of moves that have been validated
    private final MoveValidator mv = new MoveValidator();

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
        this.whoseTurn = 0; // 0 is for player1 and 1 is for player2
        this.validatedMoves = new ArrayList<>();
    }

    /**
     * Getter for whoseTurn
     * @return int representing current player: 0 for player1 and 1 for player2
     */
    public int getWhoseTurn() {
        return this.whoseTurn;
    }

    /**
     * Function that switches which player's turn it is
     */
    public void switchTurn() {
        this.whoseTurn = ( this.whoseTurn + 1 ) % 2;
    }


    /**
     * Helper function to remove player one (when resigned or game over)
     */
    public void removePlayerOne() {
        this.playerOne = null;
    }


    /**
     * Helper function to remove player two (when resigned or game over)
     */
    public void removePlayerTwo() {
        this.playerTwo = null;
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
     * Getter for the board of the game
     * @return the board that represents the game
     */
    public Board getBoard() {
        return this.board;
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


    /**
     * Method that adds a validated move to the validatedMoves array list
     * does not check if the move is validated; (move validity is a pre-condition)
     * @param move the validated move to add
      */
    public void addValidatedMove( Move move ) {
        this.validatedMoves.add( move );
    }


    /**
     * Method that removes the most recently added validated move from the validatedMoves array list
     * @return the move that was removed
     */
    public Move backupValidatedMove() {
        return this.validatedMoves.remove( this.validatedMoves.size() - 1 );
    }


    /**
     * Getter for the validated moves
     * @return the array list of validated moves
     */
    public ArrayList< Move > getValidatedMoves() {
        return this.validatedMoves;
    }


    /**
     * Helper function that serves as a boolean flag for whether we're out of validated moves
     * @return boolean whether it is empty or not
     */
    public boolean outOfValidatedMoves() {
        return this.validatedMoves.isEmpty();
    }


    /**
     * Helper function that returns the first move in validatedMoves
     * used in PostSubmitTurnRoute to get the moves in the right order
     * @return the first move
     */
    public Move getFirstValidatedMove() {
        return this.validatedMoves.remove( 0 );
    }

    /**
     * Function to determine if playerOne (RED) has any more moves available
     * checks each space for red pieces, then if any type of move is available for that piece
     * @return
     */
    public boolean noMovesAvailableForPlayerOne() {
        for( int r = 0; r < 8; r++ ){
            for ( int c = 0; c < 8; c++ ) {
                if( ( board.getSpace( r, c ).getPiece() != null ) &&
                        ( board.getSpace( r, c ).getPiece().getColor() == Piece.Color.RED ) ) {
                    if( mv.isNormalMoveAvailable( this, r, c, board ) || mv.isKingMoveAvailable( this, r, c, board )
                            || mv.singleJumpAvailable( this, r, c, board ) || mv.kingJumpAvailable( this, r, c, board ) ){
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public boolean noMovesAvailableForPlayerTwo() {
        for( int r = 0; r < 8; r++ ){
            for ( int c = 0; c < 8; c++ ) {
                if( ( board.getSpace( r, c ).getPiece() != null ) &&
                        ( board.getSpace( r, c ).getPiece().getColor() != Piece.Color.WHITE ) ) {
                    if( mv.isNormalMoveAvailable( this, r, c, board ) || mv.isKingMoveAvailable( this, r, c, board )
                            || mv.singleJumpAvailable( this, r, c, board ) || mv.kingJumpAvailable( this, r, c, board ) ){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
