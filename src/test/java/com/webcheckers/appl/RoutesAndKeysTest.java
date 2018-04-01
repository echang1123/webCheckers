package com.webcheckers.appl;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import net.bytebuddy.asm.Advice;
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
public class RoutesAndKeysTest {
    //Attributes
    private final RoutesAndKeys routesAndKeys = new RoutesAndKeys();
    @Test
    public void test_HOME_URL(){
        assertEquals(routesAndKeys.HOME_URL, "/" );
    }
    @Test
    public void test_SIGN_IN_URL(){
        assertEquals(routesAndKeys.SIGN_IN_URL,"/signin");
    }
    @Test
    public void test_SIGN_OUT_URL(){
        assertEquals(routesAndKeys.SIGN_OUT_URL,"/signout");
    }
    @Test
    public void test_GAME_URL(){
        assertEquals(routesAndKeys.GAME_URL,"/game");
    }
    @Test
    public void test_CHECK_TURN_URL(){
        assertEquals(routesAndKeys.CHECK_TURN_URL,"checkTurn");
    }
    @Test
    public void test_VALIDATE_MOVE_URL(){
        assertEquals(routesAndKeys.VALIDATE_MOVE_URL, "/validateMove");
    }
    @Test
    public void test_BACKUP_MOVE_URL(){
        assertEquals(routesAndKeys.BACKUP_MOVE_URL,"/backupMove");
    }
    @Test
    public void test_SUBMIT_TURN_URL(){
        assertEquals(routesAndKeys.SUBMIT_TURN_URL,"/submitTurn");
    }
    @Test
    public void test_SIGNED_IN_KEY(){
        assertEquals(routesAndKeys.SIGNED_IN_KEY, "isSignedIn");
    }
    @Test
    public void test_CURRENT_PLAYER_KEY(){
        assertEquals(routesAndKeys.CURRENT_PLAYER_KEY,"currentPlayer");
    }
    @Test
    public void test_PLAYERS_KEY(){
        assertEquals(routesAndKeys.PLAYERS_KEY,"players");
    }
    @Test
    public void test_PLAYER_LOBBY_KEY(){
        assertEquals(routesAndKeys.PLAYER_LOBBY_KEY,"playerLobby");
    }
    @Test
    public void test_GAME_LOBBY_KEY(){
        assertEquals(routesAndKeys.GAME_LOBBY_KEY,"gameLobby");
    }
    @Test
    public void test_GLOBAL_INFORMATION_KEY(){
        assertEquals(routesAndKeys.GLOBAL_INFORMATION_KEY,"globalInformation");
    }
}
