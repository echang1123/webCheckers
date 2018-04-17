
package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;
import com.webcheckers.model.*;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */

@Tag("Appl-tier")
public class MoveValidatorTest {
    //Attributes

    private static final String name = "Hongda Lin";
    private static final String opponent = "Karthik";
    private static final int Row = 2;
    private static final int Cell = 2;
    private static final int row = 0;
    private static final int col = 0;
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    final Position start = new Position(Row,Cell);
    final Position end  = new Position(row,col);
    final Board board = new Board();
    final Space space = new Space(0,null,false);
    final Game game = new Game(board, player1, player2);
    final MoveValidator moveValidator = new MoveValidator();
    final Move move = new Move(start,end);

    @Test
    public void test_MoveValidator() {
        assertNotNull(moveValidator);
    }
    @Test
    public void test_isNormalMoveAvailable(){
        assertTrue(moveValidator.isNormalMoveAvailable(game,Row,Cell,board));
    }
    @Test
    public void test_singleJumpAvailable(){
        assertFalse(moveValidator.singleJumpAvailable(game,Row,Cell,board));
    }
    @Test
    public void test_isKingMoveAvailable(){
        final Position start = new Position(1,2);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(1,1).setPiece(null);
        assertFalse(moveValidator.isKingMoveAvailable(game,1,2,board));
        moveValidator.isNormalMoveAvailable(game,1,2,board);
        moveValidator.isNormalMoveAvailable(game,0,0,board);
    }
    @Test
    public void test_isKingMoveAvailablewithWhiePiece(){
        final Position start = new Position(2,2);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        board.getSpace(1,1).setPiece(null);
        moveValidator.isKingMoveAvailable(game,2,2,board);
        moveValidator.isNormalMoveAvailable(game,2,2,board);
    }
    @Test
    public void test_WhitePieceKingMove(){
        final Position start = new Position(1,0);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        moveValidator.isKingMoveAvailable(game,1,0,board);
        moveValidator.isNormalMoveAvailable(game,1,0,board);
    }
    @Test
    public void test_isKingMoveAvailablewithRedPiece(){
        final Position start = new Position(1,2);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        piece.setColor(Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        moveValidator.isKingMoveAvailable(game,1,2,board);
        moveValidator.isNormalMoveAvailable(game,1,2,board);
    }
    @Test
    public void test_RedPieceKingMove(){
        final Position start = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        piece.setColor(Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(1,3).setPiece(null);
        moveValidator.isKingMoveAvailable(game,2,2,board);
    }
    @Test
    public void test_singleJump(){
        final Position start = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(3,1).setPiece(piece1);
        moveValidator.singleJumpAvailable(game,2,2,board);
        moveValidator.kingJumpAvailable(game,2,2,board);
     }
    @Test
    public void test_singleJump2(){
        final Position start = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(3,3).setPiece(piece1);
        moveValidator.singleJumpAvailable(game,2,2,board);
        moveValidator.kingJumpAvailable(game,2,2,board);
    }
    @Test
    public void test_singleJumpWhitePiece(){
        final Position start = new Position(2,2);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(1,1).setPiece(piece1);
        moveValidator.singleJumpAvailable(game,2,2,board);
        moveValidator.kingJumpAvailable(game,2,2,board);
    }
    @Test
    public void test_singleJumpWhitePiece2(){
        final Position start = new Position(3,2);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(2,3).setPiece(piece1);
        moveValidator.singleJumpAvailable(game,3,2,board);
        moveValidator.kingJumpAvailable(game,3,2,board);
    }
    @Test
    public void test_KingJumpWhitePiece1(){
        final Position start = new Position(4,3);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(5,4).setPiece(piece1);
        moveValidator.kingJumpAvailable(game,4,3,board);
    }
    @Test
    public void test_KingJumpWhitePiece(){
        final Position start = new Position(5,3);
        game.switchTurn();
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        board.getSpace(6,2).setPiece(piece1);
        moveValidator.kingJumpAvailable(game,5,3,board);
    }
    @Test
    public void test_KingJumpRedPiece(){
        final Position start = new Position(7,7);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        board.getSpace(6,6).setPiece(piece1);
        moveValidator.kingJumpAvailable(game,7,7,board);
    }
    @Test
    public void test_KingJumpRedPiece2(){
        final Position start = new Position(5,5);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        Piece piece1 = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        board.getSpace(4,6).setPiece(piece1);
        moveValidator.kingJumpAvailable(game,5,5,board);
    }
    @Test
    public void test_validate(){
        assertFalse(moveValidator.validate(game,move));
    }
    @Test
    public void test_validate1(){
        final Position start = new Position(5,5);
        final Position end = new Position(7,3);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        board.getSpace(start).setPiece(piece);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.JUMP);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);

    }
}

