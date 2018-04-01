package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.Player;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import javax.print.attribute.standard.RequestingUserName;
import java.util.HashMap;

/**
 * Created by Eugene on 3/20/2018.
 */
@Tag("UI-tier")
public class GetHomeRouteTest {
    /*Component under test*/
    private GetHomeRoute CuT;

    /*friendly objects*/
    private GlobalInformation lobby;

    /*attributes holding mock objects*/
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private Player player;
    private TemplateEngineTester testHelper;

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        testHelper = mock(TemplateEngineTester.class);

        lobby = new GlobalInformation();
        CuT = new GetHomeRoute(engine, lobby);
    }

    /**
     * Test that CuT shows the Home view when the session is brand new. (played is NOT signed in)
     */
    @Test
    public void new_session(){
        testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute("title","Welcome!");

        //because player is NOT signed in
        testHelper.assertViewModelAttribute(RoutesAndKeys.SIGNED_IN_KEY, false);
        testHelper.assertViewModelAttribute(RoutesAndKeys.PLAYERS_KEY, lobby.getPlayerLobby().getPlayers());

        //test view name
        testHelper.assertViewName("home.ftl");
    }

    /**
     * Test that CuT accounts for when the player is signed in
     */
    @Test
    public void old_session(){
        // Arrange the test scenario: There is an existing session with a PlayerLobby object
        when(session.attribute(RoutesAndKeys.PLAYERS_KEY)).thenReturn(lobby.getPlayerLobby());
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        String currentPlayerName = session.attribute(RoutesAndKeys.CURRENT_PLAYER_KEY);
        testHelper.assertViewModelAttribute(RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName);
        testHelper.assertViewModelAttribute(RoutesAndKeys.PLAYERS_KEY, lobby.getPlayerLobby());
        testHelper.assertViewModelAttribute(RoutesAndKeys.SIGNED_IN_KEY, false);
        testHelper.assertViewName("home.ftl");
    }
}
