package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Model-tier")
public class GameTest {
    //attributes
    private  static final  String name = "Hongda Lin";
    private  static final  String opponent = "Karthik";
    private  static final  int  Row  = 2;
    private  static final  int  Cell  = 3;
    private static final int row1 = 3;
    private static final int cell1 = 4;
    private  static final  int whoseTurn = 0;
    private ArrayList< Move > validatedMoves;
    final Board board = new Board();
    final Position position = new Position(Row,Cell);
    final Position end = new Position(row1,cell1);
    final Move move = new Move(position,end);
    final Move move1 = new Move(position,end);
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    private Game game = new Game(board,player1,player2);

    @Test
    public void addMove(){
        validatedMoves.add(move);
        validatedMoves.add(move1);
    }
    @Test
    public void  test_Game(){

        assertNotNull(game, "The game is exist");
    }
    @Test
    public void test_GetPlayer1()
    {
        assertEquals(game.getPlayerOne(),player1);
        assertNotNull(game.getPlayerOne());
    }
    @Test
    public void test_GetPlayer2(){
        assertEquals(game.getPlayerTwo(),player2);
        assertNotNull(game.getPlayerTwo());
    }
    @Test
    public void test_RemovePlayer1(){
        game.removePlayerOne();
        assertNull(game.getPlayerOne());
    }
    @Test
    public void test_RemovePlayer2(){
        game.removePlayerTwo();
        assertNull(game.getPlayerTwo());
    }
    @Test
    public void test_SwitchTrun(){
        game.switchTurn();
        assertNotNull(game.getWhoseTurn());
    }
    @Test
    public void test_GetwhoseTurn(){
        assertNotNull(game.getWhoseTurn());
        assertEquals(game.getWhoseTurn(),whoseTurn);
    }
    @Test
    public void test_GetSpaceAT1(){
        assertNotNull(game.getSpaceAt(position));
        assertEquals(game.getSpaceAt(position),board.getSpace(Row,Cell));
    }
    @Test
    public void test_GetSpaceAT2(){
        assertNotNull(game.getSpaceAt(Row,Cell));
    }
    @Test
    public void test_GetBoard(){
        assertNotNull(game.getBoard());
        assertEquals(game.getBoard(),board);
    }
    @Test
    public void test_GetContains(){
        assertTrue(game.contains(player1));
        assertTrue(game.contains(player2));
    }
    @Test
    public void test_addValidMove(){
        game.addValidatedMove(move);
    }
    @Test
    public void test_outOfValidatedMove(){
        assertTrue(game.outOfValidatedMoves());
    }
//    @Test
//    public void test_backupValidatedMove(){
//        addMove();
//        assertNull(game.backupValidatedMove());
//    }
    @Test
    public void test_getValidatedMove(){
        validatedMoves = game.getValidatedMoves();
        assertNotNull(validatedMoves);
    }
//    @Test
//    public void test_getFirstValidateMove(){
//        addMove();
//        assertNull(game.getFirstValidatedMove());
//   }
}
