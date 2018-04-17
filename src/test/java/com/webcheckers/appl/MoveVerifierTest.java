package com.webcheckers.appl;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.PanelUI;
import java.util.HashMap;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Appl-tier")
public class MoveVerifierTest {
    //Attributes
    private static final String name = "Hongda Lin";
    private static final String opponent = "Karthik";
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    final Board board = new Board();
    final Space space = new Space(0,null,false);
    final Game game = new Game(board, player1, player2);
    final Move move = new Move(start,end);
    final MoveVerifier mv = new MoveVerifier();

    @Test
    public void test_isWithinBounds(){
        assertTrue(mv.isWithinBounds(1,1));
    }
    @Test
    public void test_isAnyMoveAvailableForPlayerOne(){
        assertTrue(mv.isAnyMoveAvailableForPlayerOne(game));
    }
    @Test
    public void test_isAnyMoveAvailableForPlayerTwo(){
       assertTrue(mv.isAnyMoveAvailableForPlayerTwo(game));
    }
    @Test
    public void test_verifyMove(){
        final Position start = new Position(0,0);
        final Position end = new Position(1,1);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        board.getSpace(start).setPiece(piece);
        final Move move = new Move(start,end);
        assertFalse(mv.verifyMove(move,game));
    }

    @Test
    public void TestisSimpleMove(){
        final Position start = new Position(1,1);
        final Position end = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.SIMPLE);
        board.getSpace(start).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
    @Test
    public void Test1UpJump(){
        final Position start = new Position(1,1);
        final Position end = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.JUMP);
        board.getSpace(start).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
    @Test
    public void Test1UpJump2(){
        final Position start = new Position(0,0);
        final Position end = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.JUMP);
        board.getSpace(start).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
    @Test
    public void TestKingJump1(){
        final Position start = new Position(3,4);
        final Position end = new Position(2,3);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.RED);
        piece.setColor(Piece.Color.RED);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.SIMPLE);
        board.getSpace(start).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
    @Test
    public void TestKingJump2(){
        final Position start = new Position(3,4);
        final Position end = new Position(2,3);
        Piece piece = new Piece(Piece.PieceType.KING, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.SIMPLE);
        board.getSpace(start).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
    @Test
    public void Test_DownJump(){
        final Board board = new Board();
        final Game game = new Game(board,player1,player2);
        game.switchTurn();
        final Position start = new Position(3,4);
        final Position end = new Position(2,2);
        Piece piece = new Piece(Piece.PieceType.SINGLE, Piece.Color.WHITE);
        piece.setColor(Piece.Color.WHITE);
        final Move move = new Move(start,end);
        move.setMoveType(Move.MoveType.JUMP);
        board.getSpace(start).setPiece(piece);
        board.getSpace(end).setPiece(piece);
        game.addVerifiedMove(move);
        game.setGameState(Game.GameState.in_progress);
        mv.verifyMove(move,game);
    }
}
