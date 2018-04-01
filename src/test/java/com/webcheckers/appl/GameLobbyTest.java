package com.webcheckers.appl;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Appl-tier")
public class GameLobbyTest {
    //Attributes
    private static final String name1 = "Hongda";
    private static final String name2 = "Karthik";
    final Player player1 = new Player(name1);
    final Player player2 = new Player(name2);
    private Board board;
    private Game gameN = new Game(board,player1,player2);
    private  final Set<Game> game = new HashSet<>();
    private  final GameLobby gameLobby = new GameLobby();
    @Test
    public void Test_GameLobby(){
        assertNull(game);
        assertNull(gameLobby);
    }
    @Test
    public void Test_AddGame(){
        assertNotNull(gameLobby.addGame(gameN));
        assertTrue(gameLobby.addGame(gameN));
    }
    @Test
    public void Test_removeGame(){
        assertNotNull(gameLobby.removeGame(gameN));
        assertTrue(gameLobby.removeGame(gameN));
    }
    @Test
    public void Test_findGame(){
        assertEquals(gameLobby.findGame(player1),player1);
        assertEquals(gameLobby.findGame(player2),player2);
        assertNull(gameLobby.findGame(player1));
        assertNull(gameLobby.findGame(player2));
    }
}
