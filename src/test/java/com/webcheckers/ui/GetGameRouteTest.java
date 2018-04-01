package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.RoutesAndKeys;
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

    // Component under test
    private GetGameRoute CuT;

    // Friendly objects
    private GlobalInformation gameLobby; // we need this to be real

    // Mock objects
    private Session session;
    private Request request;
    private Response response;
    private TemplateEngine templateEngine;


    /**
     * Create all the mock objects before running the tests
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        templateEngine = mock(TemplateEngine.class);
        gameLobby = new GlobalInformation();
        CuT = new GetGameRoute(templateEngine, gameLobby);
    }
    @Test
    public void  startAgame(){
        //setup
        when(request.queryParams("player")).thenReturn(null);
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelExists();
        templateEngineTester.assertViewModelIsaMap();
        // check data in view model
        templateEngineTester.assertViewModelAttribute(RoutesAndKeys.CURRENT_PLAYER_KEY, gameLobby.getPlayerLobby());
        templateEngineTester.assertViewModelAttribute(RoutesAndKeys.SIGNED_IN_KEY,true);
        templateEngineTester.assertViewModelAttribute(RoutesAndKeys.PLAYERS_KEY,gameLobby.getPlayerLobby());
        templateEngineTester.assertViewModelAttribute("Button","Player");
        // assert view name
        templateEngineTester.assertViewName("game.ftl");
    }
    @Test
    public  void isIngame(){
        //setup
        when(request.queryParams("player")).thenReturn(notNull());
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelExists();
        templateEngineTester.assertViewModelIsaMap();
        // check data in view model
        templateEngineTester.assertViewModelAttribute("Button","Player");
        templateEngineTester.assertViewModelAttribute("message","The player is already playing a game");
        //  assert view name
        templateEngineTester.assertViewName("signin.ftl");
    }
}
