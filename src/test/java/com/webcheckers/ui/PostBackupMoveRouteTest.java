package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.mockito.internal.matchers.NotNull;
import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.HashMap;

/**
 * The unit test suite for the GetGameRoute
 *
 * @author Hongda Lin
 */
@Tag("UI-tier")
public class PostBackupMoveRouteTest{
    //Attributes
    private static final String Player1 = "Hongda";
    private static final String Player2 = "Karthik";
    private Player player1 = new Player(Player1);
    private Player player2 = new Player(Player2);
    private Board board = new Board();
    private final HashMap<String,Object> players = new HashMap<>();

    // Component under test
    private PostBackupMoveRoute CuT;

    // Friendly objects
    private GlobalInformation gi; // we need this to be real
    private GlobalInformation gol;
    private Message message;
    // Mock objects
    private Session session;
    private Request request;
    private Response response;
    private TemplateEngine templateEngine;
    private PlayerLobby playerLobby;
    private Player player;
    /**
     * Create all the mock objects before running the tests
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        gol = mock(GlobalInformation.class);
        player = mock(Player.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);

        gi = new GlobalInformation();
        CuT = new PostBackupMoveRoute( gi);
    }
    @Test
    public void  getBackupMove() {
        gi.getPlayerLobby().addPlayer(player1,players);
        gi.getPlayerLobby().addPlayer(player2,players);
        final Game game = new Game(board,player1,player2);
        gi.getGameLobby().addGame(game);

        when(session.attribute(RoutesAndKeys.CURRENT_PLAYER_KEY)).thenReturn(Player1);
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelNotExists();
    }
    @Test
    public void  getBackupMoveNogame() {
        gi.getPlayerLobby().addPlayer(player1,players);
        gi.getPlayerLobby().addPlayer(player2,players);

        when(session.attribute(RoutesAndKeys.CURRENT_PLAYER_KEY)).thenReturn(Player1);
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelNotExists();
    }
    @Test
    public void  getBackupMoveNogameNocurrentPlayer() {
        gi.getPlayerLobby().addPlayer(player1,players);
        gi.getPlayerLobby().addPlayer(player2,players);

        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelNotExists();
    }
    @Test
    public void  getBackupMoveNoPlayer() {

        gi.getPlayerLobby().addPlayer(player1,players);
        gi.getPlayerLobby().addPlayer(player2,players);
        final Game game = new Game(null,null,null);
        gi.getGameLobby().addGame(game);

        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelNotExists();
    }
}
