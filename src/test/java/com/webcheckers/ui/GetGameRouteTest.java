package com.webcheckers.ui;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.*;
import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
    private static final String Player1 = "Hongda";
    private static final String Player2 = "Karthik";
    private static final int Row = 2;
    private static final int Cell = 2;
    private static final int row = 0;
    private static final int col = 0;
    private final HashMap<String,Object> players = new HashMap<>();
    // Component under test
    private GetGameRoute CuT;

    // Friendly objects
    private GlobalInformation gi; // we need this to be real
    private JsonUtils jsonUtils;
    private Game games;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;
    private Message message;
    // Mock objects
    private Session session;
    private Request request;
    private Response response;
    private TemplateEngine templateEngine;
    private Player player1 = new Player(Player1);
    private Player player2 = new Player(Player2);
    private Board board = new Board();
    final Position start = new Position(Row,Cell);
    final Position end  = new Position(row,col);
    private Move move = new Move(start,end);
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
        gi = new GlobalInformation();
        games = new Game(board,player1,player2);
        playerLobby = new PlayerLobby();
        gameLobby = new GameLobby();
        message = new Message(" ",Message.MessageType.error);
        CuT = new GetGameRoute(templateEngine,gi);
    }
    @Test
    public void  startAgame(){
        playerLobby.addPlayer(player1,players);
        playerLobby.addPlayer(player2,players);
        player2.addOpponent(player2);

        gi.getPlayerLobby().addPlayer(player1,players);
        gi.getPlayerLobby().addPlayer(player2,players);
        final Game game = new Game(board,player1,player2);
        gi.getGameLobby().addGame(game);
        when(session.attribute(RoutesAndKeys.CURRENT_PLAYER_NAME_KEY )).thenReturn(Player1);
        // setup
        when(request.queryParams("name")).thenReturn(Player1);
        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
        // invoke the test
        CuT.handle(request, response);
        // check view model
        templateEngineTester.assertViewModelExists();
        templateEngineTester.assertViewModelAttribute("title","Welcome!");
        templateEngineTester.assertViewModelAttribute(RoutesAndKeys.IN_GAME_KEY,null);
        // assert view name
        templateEngineTester.assertViewName("game.ftl");
    }
}
