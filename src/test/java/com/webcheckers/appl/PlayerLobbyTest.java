package com.webcheckers.appl;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Appl-tier")
public class PlayerLobbyTest {
    //Attributes
    private static final String name1 = "Hongda";
    private static final String name2 = "Karthik";
    private static final String name3 = "";
    final Player player1 = new Player(name1);
    final Player player2 = new Player(name2);
    final Player player3 = new Player(name3);
    private final HashMap<String,Object> player = new HashMap<>();
    private final PlayerLobby playerLobby = new PlayerLobby();

    @Test
    public void test_PlayerLobby(){
        assertNotNull(playerLobby);
    }
    @Test
    public void test_GetPlayer(){
        assertNull(playerLobby.getPlayer(name1));
    }
    @Test
    public void test_GetPlayers(){
        assertEquals(playerLobby.getPlayers(),player);
        assertNotNull(playerLobby.getPlayers());
    }
    @Test
    public void test_addPlayer(){
        assertTrue(playerLobby.addPlayer(player1,player));
    }
    @Test
    public void test_addPlayer1(){
        assertTrue(playerLobby.addPlayer(player3,player));
    }
    @Test
    public void test_removePlayer(){
        playerLobby.removePlayer(name1);
        assertNull(playerLobby.getPlayer(name1));
    }
    @Test
    public void test_findOpponent(){
        assertNull(playerLobby.findOpponent(player1));
        assertNull(playerLobby.findOpponent(player2));
    }
}
