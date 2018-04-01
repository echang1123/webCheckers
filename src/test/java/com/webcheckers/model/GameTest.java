package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
    private  static final  int  Row  = 5;
    private  static final  int  Col  = 5;
    final Board board = new Board();
    final Position position = new Position(Row,Col);
    final Player player1 = new Player(name);
    final Player player2 = new Player(opponent);
    private Game game = new Game(board,player1,player2);

    @Test
    public void  test_Game(){
        assertNotNull(game, "The game is exist");
    }
    @Test
    public void test_GetPlayer1()
    {
        assertEquals(game.getPlayerOne(),player1);
    }
    @Test
    public void test_GetPlater2(){
        assertEquals(game.getPlayerTwo(),player2);
    }
    @Test
    public void test_GetSpaceAT1(){
        assertNotNull(game.getSpaceAt(Row,Col));
    }
    @Test
    public void test_GetSpaceAT2(){
        assertNotNull(game.getSpaceAt(Row,Col));
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
}
