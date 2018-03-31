/*
 * Test class for PostSignInRoute
 *
 * @author Karthik Iyer
 *//*



package com.webcheckers.ui;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;


@Tag("UI-tier")
public class PostSignInRouteTest {

	private static final String VALID_USERNAME = "Karthik";
	private static final String QUOTES_USERNAME = "\"Karthik\"";
	private static final String EMPTY_USERNAME = "";

	// Component under test
	private PostSignInRoute CuT;

	// Friendly objects
	private PlayerLobby playerLobby; // we need this to be real

	// Mock objects
	private Session session;
	private Request request;
	private Response response;
	private TemplateEngine templateEngine;


	*/
/**
	 * Create all the mock objects before running the tests
	 *//*

	@BeforeEach
	public void setup() {
		request = mock(Request.class);
		response = mock(Response.class);
		session = mock(Session.class);
		when(request.session()).thenReturn(session);
		templateEngine = mock(TemplateEngine.class);
		playerLobby = new PlayerLobby();
		CuT = new PostSignInRoute(templateEngine, playerLobby);
	}


	*/
/**
	 * Test empty username sign-in attempt
	 *//*

	@Test
	public void empty_username() {
		// setup
		when(request.queryParams("name")).thenReturn(EMPTY_USERNAME);
		final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
		when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
		// invoke the test
		CuT.handle(request, response);
		// check view model
		templateEngineTester.assertViewModelExists();
		templateEngineTester.assertViewModelIsaMap();
		// check data in view model
		templateEngineTester.assertViewModelAttribute("title", "Sign In");
		templateEngineTester.assertViewModelAttribute("message", "You must enter a username.");
		// assert view name
		templateEngineTester.assertViewName("signin.ftl");
	}


	*/
/**
	 * Test quotes username sign-in attempt
	 *//*

	@Test
	public void quotes_username() {
		// setup
		when(request.queryParams("name")).thenReturn(QUOTES_USERNAME);
		final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
		when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
		// invoke the test
		CuT.handle(request, response);
		// check view model
		templateEngineTester.assertViewModelExists();
		templateEngineTester.assertViewModelIsaMap();
		// check data in view model
		templateEngineTester.assertViewModelAttribute("title", "Sign In");
		templateEngineTester.assertViewModelAttribute("message","Username cannot contain quotes");
		// assert view name
		templateEngineTester.assertViewName("signin.ftl");
	}


	*/
/**
	 * Tests when a sign-in with a valid username is tried
	 *//*

	@Test
	public void valid_username() {
		// setup
		when(request.queryParams("name")).thenReturn(VALID_USERNAME);
		final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
		when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
		// invoke the test
		CuT.handle(request, response);
		// check view model
		templateEngineTester.assertViewModelNotExists();
	}


	*/
/**
	 * Tests sign-in attempt with a username that is taken
	 *//*

	@Test
	public void same_username() {
		// setup
		when(request.queryParams("name")).thenReturn(VALID_USERNAME);
		final TemplateEngineTester templateEngineTester = new TemplateEngineTester();
		when(templateEngine.render(any(ModelAndView.class))).thenAnswer(templateEngineTester.makeAnswer());
		playerLobby.addPlayer(new Player(VALID_USERNAME), null); // add a valid username to the lobby
		// invoke the test
		CuT.handle(request,response);
		// check view model
		templateEngineTester.assertViewModelExists();
		templateEngineTester.assertViewModelIsaMap();
		// check data in view model
		templateEngineTester.assertViewModelAttribute("title", "Sign In");
		templateEngineTester.assertViewModelAttribute("message",
				"The username Karthik is already taken");
		// assert view name
		templateEngineTester.assertViewName("signin.ftl");

	}
}
*/
