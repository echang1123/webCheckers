//package com.webcheckers.ui;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.webcheckers.appl.GlobalInformation;
//import com.webcheckers.appl.RoutesAndKeys;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import com.webcheckers.appl.PlayerLobby;
//import com.webcheckers.model.Player;
//import org.mockito.internal.matchers.NotNull;
//import spark.HaltException;
//import spark.ModelAndView;
//import spark.Request;
//import spark.Response;
//import spark.Session;
//import spark.TemplateEngine;
//
///**
// * The unit test suite for the GetGameRoute
// *
// * @author Hongda Lin
// */
////@Tag("ui-tier")
//public class PostBackupMoveRouteTest {
//    //Attributes
//    private static final String Player = "Hongda";
//
//    // Component under test
//    private PostBackupMoveRoute CuT;
//
//    // Friendly objects
//    private GlobalInformation gi; // we need this to be real
//
//    // Mock objects
//    private Session session;
//    private Request request;
//    private Response response;
//    private TemplateEngine templateEngine;
//
//
//    /**
//     * Create all the mock objects before running the tests
//     */
//    @BeforeEach
//    public void setup() {
//        request = mock(Request.class);
//        response = mock(Response.class);
//        session = mock(Session.class);
//        when(request.session()).thenReturn(session);
//        templateEngine = mock(TemplateEngine.class);
//        gi = new GlobalInformation();
//        CuT = new PostBackupMoveRoute(gi);
//    }
//
//    @Test
//    public void getBackupMove() {
//        //setup
//        when(request.queryParams("PostBackupMoveRoute is invoked.")).thenReturn(Player);
//        final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
//        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
//        // invoke the test
//        CuT.handle(request, response);
//        // check view model
//        templateEngineTester.assertViewModelExists();
//        templateEngineTester.assertViewModelIsaMap();
//        templateEngineTester.assertViewModelAttribute(RoutesAndKeys.CURRENT_PLAYER_KEY,Player);
//    }
//}