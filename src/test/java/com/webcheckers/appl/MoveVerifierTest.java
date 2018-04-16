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
    final Move move = new Move(start,end);
    final MoveVerifier mv = new MoveVerifier();

    @Test
    public void test_isWithinBounds(){
        assertTrue(mv.isWithinBounds(row,Cell));
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
        assertFalse(mv.verifyMove(move,game));
    }
    @Test
    public void Test(){
        game.setGameState(Game.GameState.in_progress);
        game.switchTurn();
    }
}
