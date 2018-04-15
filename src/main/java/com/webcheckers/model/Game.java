/*
 * Class that represents a Checkers Game
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Eugene Chang
 */


package com.webcheckers.model;


import com.webcheckers.appl.MoveVerifier;


import java.util.ArrayList;


public class Game {

    // enums
    public enum GameState {
        in_progress, complete
    }

    // Attributes
    private Board board; // the board
    private Player playerOne; // player 1
    private Player playerTwo; // player 2
    private int whoseTurn; // 0 is for player1 and 1 is for player2
    private ArrayList< Move > verifiedMoves; // keeps track of moves that have been validated
    private final MoveVerifier mv;
    private GameState gameState;

    /**
     * Constructor for the Game class
     *
     * @param board     the board for the Game
     * @param playerOne player one (RED)
     * @param playerTwo player two (WHITE)
     */
    public Game( Board board, Player playerOne, Player playerTwo ) {
        this.board = board;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.whoseTurn = 0; // 0 is for player1 and 1 is for player2
        this.verifiedMoves = new ArrayList<>();
        this.mv = new MoveVerifier();
        this.gameState = GameState.in_progress;
    }

    /**
     * Getter for whoseTurn
     *
     * @return int representing current player: 0 for player1 and 1 for player2
     */
    public int getWhoseTurn() {
        return this.whoseTurn;
    }


    /**
     * Setter for the state of the game
     *
     * @param gameState the new state of the game
     */
    public void setGameState( Game.GameState gameState ) {
        this.gameState = gameState;
    }


    /**
     * Method that returns whether the game is complete
     *
     * @return boolean whether the game is complete
     */
    public boolean isComplete() {
        return this.gameState == GameState.complete;
    }


    /**
     * Getter for moveVerifier
     *
     * @return moveVerifier object
     */
    public MoveVerifier getMoveVerifier() {
        return this.mv;
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
     *
     * @return first player (RED)
     */
    public Player getPlayerOne() {
        return playerOne;
    }


    /**
     * Getter for player two
     *
     * @return second player (WHITE)
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }


    /**
     * Returns the space at the given position
     *
     * @return the space at that position
     */
    public Space getSpaceAt( Position position ) {
        int row = position.getRow();
        int cell = position.getCell();
        return board.getSpace( row, cell );
    }


    /**
     * Getter for the piece at a given (row, col) co-ordinate on the board
     *
     * @param row the row index
     * @param col the column index
     * @return the piece at the (row, col) co-ordinate; null if no piece or out of bounds
     */
    public Piece getPieceAt( int row, int col ) {
        if( mv.isWithinBounds( row, col ) ) {
            return this.board.getSpace( row, col ).getPiece();
        }
        return null;
    }


    /**
     * Helper method that checks if a piece at a position is a king piece
     *
     * @param row the row index
     * @param col the col index
     * @return whether the piece there (if it exists), it is a king piece
     */
    public boolean isThereAKingPieceAt( int row, int col ) {
        return ( getPieceAt( row, col ) != null ) && ( getPieceAt( row, col ).getType() == Piece.PieceType.KING );
    }


    /**
     * Getter for the piece at a Position
     *
     * @param position the position
     * @return the piece at the position; null if no piece or out of bounds
     */
    public Piece getPieceAt( Position position ) {
        int row = position.getRow();
        int col = position.getCell();
        return this.getPieceAt( row, col );
    }


    /**
     * Getter for the board of the game
     *
     * @return the board that represents the game
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Returns the space at a given row index and cell index
     *
     * @param row  row index
     * @param cell cell index
     * @return the space at that row and cell index
     */
    public Space getSpaceAt( int row, int cell ) {
        return board.getSpace( row, cell );
    }


    /**
     * Override of the equals function to compare player one and player two of two games
     *
     * @param o the Object to compare with
     * @return whether the two Web Checkers games are equal
     */
    @Override
    public boolean equals( Object o ) {
        if( o != null ) {
            if( o instanceof Game ) {
                Game g = ( Game ) o;
                return ( this.playerOne.equals( g.getPlayerOne() ) && this.playerTwo.equals( g.getPlayerTwo() ) );
            }
        }
        return false;
    }


    /**
     * Returns a boolean for whether a given player is in the game
     *
     * @param player the Player to search for
     * @return true if player given is in this Game
     */
    public boolean contains( Player player ) {
        return ( ( playerOne != null && playerOne.equals( player ) ) || ( playerTwo != null && playerTwo.equals( player ) ) );
    }


    /**
     * Method that adds a verified move to the verifiedMoves array list
     * does not check if the move is verified; (move validity is a pre-condition)
     *
     * @param move the verified move to add
     */
    public void addVerifiedMove( Move move ) {
        this.verifiedMoves.add( move );
        this.simulateVerifiedMove( move );
    }


    /**
     * Method that removes the most recently added verified move from the verifiedMoves array list
     *
     * @return the move that was removed
     */
    public Move backupVerifiedMove() {
        this.reverseSimulateVerifiedMove( this.verifiedMoves.get( this.verifiedMoves.size() - 1 ) );
        return this.verifiedMoves.remove( this.verifiedMoves.size() - 1 );
    }


    /**
     * Helper function that serves as a boolean flag for whether we're out of verified moves
     *
     * @return boolean whether it is empty or not
     */
    public boolean outOfVerifiedMoves() {
        return this.verifiedMoves.isEmpty();
    }


    /**
     * Helper function that returns the first move in verifiedMoves
     * used in PostSubmitTurnRoute to get the moves in the right order
     *
     * @return the first move
     */
    public Move getFirstVerifiedMove() {
        return this.verifiedMoves.remove( 0 );
    }


    /**
     * Function to determine if player one (Red) has any more moves available
     * checks each space for red pieces, then if any type of move is available for that piece
     *
     * @return whether player one can make any moves
     */
    public boolean anyMovesAvailableForPlayerOne() {
        return mv.isAnyMoveAvailableForPlayerOne( this );
    }


    /**
     * Function to determine if player two (White) has any more moves available
     * checks each space for red pieces, then if any type of move is available for that piece
     *
     * @return whether player two can make any moves
     */
    public boolean anyMovesAvailableForPlayerTwo() {
        return mv.isAnyMoveAvailableForPlayerTwo( this );
    }


    /**
     * Simulates a verified move by moving the piece involved in the move.
     * It does not remove captured piece (if any), that is done in board.doMove()
     *
     * @param move the move to simulate
     */
    private void simulateVerifiedMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();

        Piece oldPiece = this.getPieceAt( start );
        Piece newPiece = new Piece( oldPiece.getType(), oldPiece.getColor() );
        this.getSpaceAt( end ).setPiece( newPiece );

    }


    private void reverseSimulateVerifiedMove( Move move ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        this.getSpaceAt( end ).removePiece();
    }

}
