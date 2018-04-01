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

public class GlobalInformationTest {
    //Attributes
    private PlayerLobby playerLobby = new PlayerLobby();
    private GameLobby gameLobby = new GameLobby();
    private GlobalInformation globalInformation = new GlobalInformation();
    @Test
    public void test_GlobalInformation(){
        assertNotNull(globalInformation);
    }
    @Test
    public void test_GetPlayerLobby(){
        assertEquals(globalInformation.getPlayerLobby(),playerLobby);
        assertNotNull(globalInformation.getPlayerLobby());
    }
    @Test
    public void test_GetGameLobby(){
        assertEquals(globalInformation.getGameLobby(),gameLobby);
        assertNotNull(globalInformation.getGameLobby());
    }
}
