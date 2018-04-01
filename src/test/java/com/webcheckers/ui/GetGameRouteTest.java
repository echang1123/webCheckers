package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.RoutesAndKeys;
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
public class GetGameRouteTest {

    //Attributes
    private static final String Opponent = "Karthik";
    private static final String Player1 = "Hongda";
    private Player player = new Player(Player1);

    // Component under test
    private GetGameRoute CuT;

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

    /**
     * Create all the mock objects before running the tests
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        player = mock(Player.class);
        templateEngine = mock(TemplateEngine.class);
        playerLobby = mock(PlayerLobby.class);
        gol = mock(GlobalInformation.class);
        gi = new GlobalInformation();
        CuT = new GetGameRoute(templateEngine, gi);
    }
    @Test
    public void  startAgame(){
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        when(gol.getPlayerLobby()).thenReturn(gi.getPlayerLobby());
        final PlayerLobby playerLobbyTest = new PlayerLobby();
        when(playerLobby.getPlayer(Player1)).thenReturn(playerLobbyTest.getPlayer(Player1));
        // check view model
        templateEngineTester.assertViewModelNotExists();
        // check data in view model
        //templateEngineTester.assertViewModelAttribute(RoutesAndKeys.SIGNED_IN_KEY,false);
        //templateEngineTester.assertViewModelAttribute(RoutesAndKeys.PLAYERS_KEY,gi.getPlayerLobby().getPlayers());
        //templateEngineTester.assertViewModelAttribute(RoutesAndKeys.IN_GAME_KEY,gi.getGameLobby());
        //templateEngineTester.assertViewModelAttribute("Button","Player");
        // assert view name
        //templateEngineTester.assertViewName("game.ftl");
    }
    @Test
    public  void isIngame(){
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        when(gol.getPlayerLobby()).thenReturn(gi.getPlayerLobby());
        final PlayerLobby playerLobbyTest = new PlayerLobby();
        when(playerLobby.getPlayer(Player1)).thenReturn(playerLobbyTest.getPlayer(Player1));
        // check view model
        templateEngineTester.assertViewModelNotExists();
        // check data in view model

        //templateEngineTester.assertViewModelAttribute(RoutesAndKeys.MESSAGE_KEY, message.getText());
        //  assert view name
        //templateEngineTester.assertViewName("signin.ftl");
    }
}
