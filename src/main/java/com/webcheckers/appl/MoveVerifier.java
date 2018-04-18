/*
 * Move verifier class
 *
 * @author Karthik Iyer
 */


package com.webcheckers.appl;

import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Position;

import java.util.ArrayList;

public class MoveVerifier {

    // Attributes
    private ArrayList< Move > jumpMoves;
    private ArrayList< Move > simpleMoves;


    /**
     * Default constructor for the MoveVerifier
     */
    public MoveVerifier() {
        this.jumpMoves = new ArrayList<>();
        this.simpleMoves = new ArrayList<>();
    }


    /**
     * Resets the move lists
     */
    private void reset() {
        this.simpleMoves = new ArrayList<>();
        this.jumpMoves = new ArrayList<>();
    }


    /**
     * A simple helper method that checks if a given co-ordinate (row, col) is within the board
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
    private Piece.Color getCurrentPlayerColor( Game game ) {
        if( game.getWhoseTurn() == 0 ) {
            return Piece.Color.RED;
        } else {
            return Piece.Color.WHITE;
        }
    }


    /**
     * Helper method that checks if an opponent's piece is present at a given (row, col) on the board
     *
     * @param game               the Web Checkers game
     * @param currentPlayerColor the color of the current player
     * @param row                the row index
     * @param col                the column index
     * @return whether there is an opponent piece at (row, col)
     */
    private boolean hasOpponentPiece( Game game, Piece.Color currentPlayerColor, int row, int col ) {
        Piece piece = game.getPieceAt( row, col );
        return ( ( piece != null ) && ( piece.getColor() != currentPlayerColor ) );
    }


    /**
     * Helper method that determines if a simple move "up" the board is possible geometrically; also checks that
     * the landing cell is empty (piece == null)
     *
     * @param game               the Web Checkers game
     * @param currentPlayerColor the color of the current player
     * @param row                the row index
     * @param col                the col index
     * @return whether it is geometrically possible
     */
    private boolean isUpSimpleMovePossible( Game game, Piece.Color currentPlayerColor, int row, int col ) {
        Piece currentPiece = game.getPieceAt( row, col );
        boolean result = false;
        if( currentPiece != null ) {
            if( currentPiece.getColor() == currentPlayerColor ) {
                if( isWithinBounds( row + 1, col + 1 ) ) {
                    Piece landingPiece = game.getPieceAt( row + 1, col + 1 );
                    if( landingPiece == null ) {
                        Position start = new Position( row, col );
                        Position end = new Position( row + 1, col + 1 );
                        Move upSimpleMove = new Move( start, end );
                        this.simpleMoves.add( upSimpleMove );
                        result = true;
                    }
                }
                if( isWithinBounds( row + 1, col - 1 ) ) {
                    Piece landingPiece = game.getPieceAt( row + 1, col - 1 );
                    if( landingPiece == null ) {
                        Position start = new Position( row, col );
                        Position end = new Position( row + 1, col - 1 );
                        Move upSimpleMove = new Move( start, end );
                        this.simpleMoves.add( upSimpleMove );
                        result = true;
                    }
                }
            }
        }
        return result;
    }


    /**
     * Helper method that determines if a simple move "down" the board is possible geometrically; also checks that
     * the landing cell is empty (piece == null)
     *
     * @param game               the Web Checkers game
     * @param currentPlayerColor the color of the current player
     * @param row                the row index
     * @param col                the col index
     * @return whether it is geometrically possible
     */
    private boolean isDownSimpleMovePossible( Game game, Piece.Color currentPlayerColor, int row, int col ) {
        Piece currentPiece = game.getPieceAt( row, col );
        boolean result = false;
        if( currentPiece != null ) {
            if( currentPiece.getColor() == currentPlayerColor ) {
                if( isWithinBounds( row - 1, col + 1 ) ) {
                    Piece landingPiece = game.getPieceAt( row - 1, col + 1 );
                    if( landingPiece == null ) {
                        Position start = new Position( row, col );
                        Position end = new Position( row - 1, col + 1 );
                        Move downSimpleMove = new Move( start, end );
                        this.simpleMoves.add( downSimpleMove );
                        result = true;
                    }
                }
                if( isWithinBounds( row - 1, col - 1 ) ) {
                    Piece landingPiece = game.getPieceAt( row - 1, col - 1 );
                    if( landingPiece == null ) {
                        Position start = new Position( row, col );
                        Position end = new Position( row - 1, col - 1 );
                        Move downSimpleMove = new Move( start, end );
                        this.simpleMoves.add( downSimpleMove );
                        result = true;
                    }
                }
            }
        }
        return result;
    }


    /**
     * A method that goes through all the pieces of the current player's color and determines if a simple move is
     * possible for ANY of them
     *
     * @param game the Web Checkers game
     * @return boolean whether a simple move is possible for any of the pieces
     */
    private boolean isSimpleMoveAvailable( Game game ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        boolean result = false;
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( isUpSimpleMovePossible( game, currentPlayerColor, row, col ) ) {
                        result = true;
                    }
                }
            }
        }
        // Second player (White)
        // This means row must decrease by one, and column varies by one
        else {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( isDownSimpleMovePossible( game, currentPlayerColor, row, col ) ) {
                        result = true;
                    }
                }
            }
        }
        // no simple moves are possible
        return result;
    }


    /**
     * A method that goes through all the pieces of the current player color and determines if any of those pieces
     * is a king, and if any of them can make a king move
     *
     * @param game the Web Checkers game
     * @return whether a king move is possible
     */
    private boolean isKingMoveAvailable( Game game ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        boolean result = false;
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) &&
                        isDownSimpleMovePossible( game, currentPlayerColor, row, col ) ) {
                        result = true;
                    }
                }
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) &&
                        isUpSimpleMovePossible( game, currentPlayerColor, row, col ) ) {
                        result = true;
                    }
                }
            }
        }
        // no king moves possible
        return result;
    }


    /**
     * A helper method that determines if an "up" jump move is possible. Checks that there is an opponent diagonally
     * adjacent to current position, and then checks that the landing spot is empty.
     *
     * @param game               the Web Checkers game
     * @param currentPlayerColor the color of the current player
     * @param row                the row index
     * @param col                the column index
     * @return whether an "up" jump move is possible from the given (row, col) co-ordinate on the board
     */
    private boolean isUpJumpMovePossible( Game game, Piece.Color currentPlayerColor, int row, int col ) {
        Piece currentPiece = game.getPieceAt( row, col );
        boolean result = false;
        if( currentPiece != null ) {
            if( currentPiece.getColor() == currentPlayerColor ) {
                if( isWithinBounds( row + 1, col + 1 ) ) {
                    if( hasOpponentPiece( game, currentPlayerColor, row + 1, col + 1 ) ) {
                        if( isWithinBounds( row + 2, col + 2 ) ) {
                            Piece landingPiece = game.getPieceAt( row + 2, col + 2 );
                            if( landingPiece == null ) {
                                Position start = new Position( row, col );
                                Position end = new Position( row + 2, col + 2 );
                                Move upJumpMove = new Move( start, end );
                                jumpMoves.add( upJumpMove );
                                result = true;
                            }
                        }
                    }
                }
                if( isWithinBounds( row + 1, col - 1 ) ) {
                    if( hasOpponentPiece( game, currentPlayerColor, row + 1, col - 1 ) ) {
                        if( isWithinBounds( row + 2, col - 2 ) ) {
                            Piece landingPiece = game.getPieceAt( row + 2, col - 2 );
                            if( landingPiece == null ) {
                                Position start = new Position( row, col );
                                Position end = new Position( row + 2, col - 2 );
                                Move upJumpMove = new Move( start, end );
                                jumpMoves.add( upJumpMove );
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    /**
     * A helper method that determines if a "down" jump move is possible. Checks that there is an opponent diagonally
     * adjacent to current position, and then checks that the landing spot is empty.
     *
     * @param game               the Web Checkers game
     * @param currentPlayerColor the color of the current player
     * @param row                the row index
     * @param col                the column index
     * @return whether a "down" jump move is possible from the given (row, col) co-ordinate on the board
     */
    private boolean isDownJumpMovePossible( Game game, Piece.Color currentPlayerColor, int row, int col ) {
        Piece currentPiece = game.getPieceAt( row, col );
        boolean result = false;
        if( currentPiece != null ) {
            if( currentPiece.getColor() == currentPlayerColor ) {
                if( isWithinBounds( row - 1, col + 1 ) ) {
                    if( hasOpponentPiece( game, currentPlayerColor, row - 1, col + 1 ) ) {
                        if( isWithinBounds( row - 2, col + 2 ) ) {
                            Piece landingPiece = game.getPieceAt( row - 2, col + 2 );
                            if( landingPiece == null ) {
                                Position start = new Position( row, col );
                                Position end = new Position( row - 2, col + 2 );
                                Move downJumpMove = new Move( start, end );
                                jumpMoves.add( downJumpMove );
                                result = true;
                            }
                        }
                    }
                }
                if( isWithinBounds( row - 1, col - 1 ) ) {
                    if( hasOpponentPiece( game, currentPlayerColor, row - 1, col - 1 ) ) {
                        if( isWithinBounds( row - 2, col - 2 ) ) {
                            Piece landingPiece = game.getPieceAt( row - 2, col - 2 );
                            if( landingPiece == null ) {
                                Position start = new Position( row, col );
                                Position end = new Position( row - 2, col - 2 );
                                Move downJumpMove = new Move( start, end );
                                jumpMoves.add( downJumpMove );
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    /**
     * A method that goes through all the pieces on the board of the current player's color, then checks if any of them
     * can do a simple jump move (not king jump)
     *
     * @param game the Web Checkers game
     * @return whether a simple jump is possible
     */
    private boolean isSimpleJumpMoveAvailable( Game game ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( isUpJumpMovePossible( game, currentPlayerColor, row, col ) ) {
                        return true;
                    }
                }
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( isDownJumpMovePossible( game, currentPlayerColor, row, col ) ) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    /**
     * A method that goes through all the pieces on the board of the current player's color, then checks if any of them
     * can do a king jump move and that the piece is a King piece
     *
     * @param game the Web Checkers game
     * @return whether a simple jump is possible
     */
    private boolean isKingJumpMoveAvailable( Game game ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) &&
                        isDownJumpMovePossible( game, currentPlayerColor, row, col ) ) {
                        return true;
                    }
                }
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) &&
                        isUpJumpMovePossible( game, currentPlayerColor, row, col ) ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * A helper method that determines if any of the pieces of the current player's color can do a jump move (king or
     * simple). This is used to enforce the rule which states that if a jump move of any kind is available, then any
     * other type of move is illegal
     *
     * @param game the Web Checkers game
     * @return whether any jump move is available
     */
    public boolean isAnyJumpMoveAvailable( Game game ) {
        return isKingJumpMoveAvailable( game ) || isSimpleJumpMoveAvailable( game );
    }


    /**
     * A helper method that checks if the red player can make any moves (simple, king simple, simple jump, king jump).
     * Useful for determining if a player is out of moves (game loss)
     *
     * @param game the Web Checkers game
     * @return whether any moves can be made by player one
     */
    public boolean isAnyMoveAvailableForPlayerOne( Game game ) {
        Piece.Color currentPlayerColor = Piece.Color.RED;
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                if( ( isUpSimpleMovePossible( game, currentPlayerColor, row, col ) )
                    ||
                    ( ( game.isThereAKingPieceAt( row, col ) ) &&
                        ( isDownSimpleMovePossible( game, currentPlayerColor, row, col ) ) )
                    ||
                    ( isUpJumpMovePossible( game, currentPlayerColor, row, col ) )
                    ||
                    ( ( game.isThereAKingPieceAt( row, col ) ) &&
                        ( isDownJumpMovePossible( game, currentPlayerColor, row, col ) ) ) ) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * A helper method that checks if the white player can make any moves (simple, king simple, simple jump, king jump).
     * Useful for determining if a player is out of moves (game loss)
     *
     * @param game the Web Checkers game
     * @return whether any moves can be made by player two
     */
    public boolean isAnyMoveAvailableForPlayerTwo( Game game ) {
        Piece.Color currentPlayerColor = Piece.Color.WHITE;
        for( int row = 0; row < 8; row++ ) {
            for( int col = 0; col < 8; col++ ) {
                if( ( isDownSimpleMovePossible( game, currentPlayerColor, row, col ) )
                    ||
                    ( ( game.isThereAKingPieceAt( row, col ) ) &&
                        ( isUpSimpleMovePossible( game, currentPlayerColor, row, col ) ) )
                    ||
                    ( isDownJumpMovePossible( game, currentPlayerColor, row, col ) )
                    ||
                    ( ( game.isThereAKingPieceAt( row, col ) ) &&
                        ( isUpJumpMovePossible( game, currentPlayerColor, row, col ) ) ) ) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Method that verifies that a given move is a simple move
     *
     * @param game     the Web Checkers game
     * @param startRow the starting row index
     * @param startCol the starting col index
     * @param endRow   the ending row index
     * @param endCol   the ending col index
     * @return whether the move was verified as a simple move
     */
    private boolean verifySimpleMove( Game game, int startRow, int startCol, int endRow, int endCol ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            if( isUpSimpleMovePossible( game, currentPlayerColor, startRow, startCol ) ) {
                // for a simple up move, the row increases by 1 and the column varies by 1
                return ( endRow - startRow == 1 ) && ( Math.abs( endCol - startCol ) == 1 );
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            if( isDownSimpleMovePossible( game, currentPlayerColor, startRow, startCol ) ) {
                // for a simple down move, the row decreases by 1, and the column varies by 1
                return ( startRow - endRow == 1 ) && ( Math.abs( endCol - startCol ) == 1 );
            }
        }
        return false;
    }


    /**
     * Method that verifies that a given move is a king simple move
     *
     * @param game     the Web Checkers game
     * @param startRow the starting row index
     * @param startCol the starting col index
     * @param endRow   the ending row index
     * @param endCol   the ending col index
     * @return whether the move was verified as a king simple move
     */
    private boolean verifyKingSimpleMove( Game game, int startRow, int startCol, int endRow, int endCol ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            if( ( game.isThereAKingPieceAt( startRow, startCol ) ) &&
                ( isDownSimpleMovePossible( game, currentPlayerColor, startRow, startCol ) ) ) {
                return ( startRow - endRow == 1 ) && ( Math.abs( endCol - startCol ) == 1 );
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            if( ( game.isThereAKingPieceAt( startRow, startCol ) ) &&
                ( isUpSimpleMovePossible( game, currentPlayerColor, startRow, startCol ) ) ) {
                return ( endRow - startRow == 1 ) && ( Math.abs( endCol - startCol ) == 1 );
            }
        }
        return false;
    }


    /**
     * Method that verifies that a given move is a simple jump move
     *
     * @param game     the Web Checkers game
     * @param startRow the starting row index
     * @param startCol the starting col index
     * @param endRow   the ending row index
     * @param endCol   the ending col index
     * @return whether the move was verified as a simple jump move
     */
    private boolean verifySimpleJumpMove( Game game, int startRow, int startCol, int endRow, int endCol ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            if( isUpJumpMovePossible( game, currentPlayerColor, startRow, startCol ) ) {
                return ( endRow - startRow == 2 ) && ( Math.abs( endCol - startCol ) == 2 );
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            if( isDownJumpMovePossible( game, currentPlayerColor, startRow, startCol ) ) {
                return ( startRow - endRow == 2 ) && ( Math.abs( endCol - startCol ) == 2 );
            }
        }
        return false;
    }


    /**
     * Method that verifies that a given move is a king jump move
     *
     * @param game     the Web Checkers game
     * @param startRow the starting row index
     * @param startCol the starting col index
     * @param endRow   the ending row index
     * @param endCol   the ending col index
     * @return whether the move was verified as a king jump move
     */
    private boolean verifyKingJumpMove( Game game, int startRow, int startCol, int endRow, int endCol ) {
        Piece.Color currentPlayerColor = getCurrentPlayerColor( game );
        if( currentPlayerColor == Piece.Color.RED ) {
            if( ( game.isThereAKingPieceAt( startRow, startCol ) ) &&
                ( isDownJumpMovePossible( game, currentPlayerColor, startRow, startCol ) ) ) {
                return ( startRow - endRow == 2 ) && ( Math.abs( endCol - startCol ) == 2 );
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            if( ( game.isThereAKingPieceAt( startRow, startCol ) ) &&
                ( isUpJumpMovePossible( game, currentPlayerColor, startRow, startCol ) ) ) {
                return ( endRow - startRow == 2 ) && ( Math.abs( endCol - startCol ) == 2 );
            }
        }
        return false;
    }


    /**
     * Helper method that generates all possible jump moves
     *
     * @param game the Web Checkers game
     */
    private void generateJumpMoves( Game game, Piece.Color currentPlayerColor ) {
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) ) {
                        isDownJumpMovePossible( game, currentPlayerColor, row, col );
                    }
                    isUpJumpMovePossible( game, currentPlayerColor, row, col );
                }
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) ) {
                        isUpJumpMovePossible( game, currentPlayerColor, row, col );
                    }
                    isDownJumpMovePossible( game, currentPlayerColor, row, col );
                }
            }
        }

    }


    /**
     * Helper method that generates all simple moves
     *
     * @param game the Web Checkers game
     */
    private void generateSimpleMoves( Game game, Piece.Color currentPlayerColor ) {
        if( currentPlayerColor == Piece.Color.RED ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) ) {
                        isDownSimpleMovePossible( game, currentPlayerColor, row, col );
                    }
                    isUpSimpleMovePossible( game, currentPlayerColor, row, col );
                }
            }
        }
        if( currentPlayerColor == Piece.Color.WHITE ) {
            for( int row = 0; row < 8; row++ ) {
                for( int col = 0; col < 8; col++ ) {
                    if( game.isThereAKingPieceAt( row, col ) ) {
                        isUpSimpleMovePossible( game, currentPlayerColor, row, col );
                    }
                    isDownSimpleMovePossible( game, currentPlayerColor, row, col );
                }
            }
        }
    }


    /**
     * Helper method that returns valid moves
     *
     * @param game the game
     * @return valid moves
     */
    public ArrayList< Move > getValidMoves( Game game, Piece.Color currentPlayerColor ) {
        this.reset();
        if( isAnyJumpMoveAvailable( game ) ) {
            generateJumpMoves( game, currentPlayerColor );
            return this.jumpMoves;
        }
        else {
            generateSimpleMoves( game, currentPlayerColor );
            return this.simpleMoves;
        }
    }


    /**
     * The "main" method of this class, if you will. Takes in a move and verifies it.
     *
     * @param move the move to verify
     * @param game the Web Checkers game
     * @return whether the move was verified to be valid
     */
    public boolean verifyMove( Move move, Game game ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        int startRow = start.getRow();
        int startCol = start.getCell();
        int endRow = end.getRow();
        int endCol = end.getCell();

        if( isAnyJumpMoveAvailable( game ) ) {
            if( verifySimpleJumpMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.JUMP );
                this.reset();
                return true;
            }
            if( verifyKingJumpMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.KING_JUMP );
                this.reset();
                return true;
            }
        } else {
            if( verifySimpleMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.SIMPLE );
                this.reset();
                return true;
            }
            if( verifyKingSimpleMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.KING_SIMPLE );
                this.reset();
                return true;
            }
        }
        this.reset();
        return false;
    }


    /**
     * The alternative "main" method of the class that will be used for verifying consequent jump moves
     *
     * @param move the jump move to verify
     * @param game the Web Checkers game
     * @return whether the jump move was verified to be correct
     */
    public boolean verifyJumpMove( Move move, Game game ) {
        Position start = move.getStart();
        Position end = move.getEnd();
        int startRow = start.getRow();
        int startCol = start.getCell();
        int endRow = end.getRow();
        int endCol = end.getCell();

        if( isAnyJumpMoveAvailable( game ) ) {
            if( verifySimpleJumpMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.JUMP );
                this.reset();
                return true;
            }
            if( verifyKingJumpMove( game, startRow, startCol, endRow, endCol ) ) {
                move.setMoveType( Move.MoveType.KING_JUMP );
                this.reset();
                return true;
            }
        }

        this.reset();
        return false;
    }

}
