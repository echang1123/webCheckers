package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import spark.*;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  static final String PLAYERS_KEY = "players";
  private final TemplateEngine templateEngine;
  private HashMap<String, Object> players;

  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, final HashMap<String,Object> players) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    Objects.requireNonNull(templateEngine, "players must not be null");
    //
    this.templateEngine = templateEngine;
    this.players = players;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    final Session httpSession = request.session();
    if(httpSession.attribute(PLAYERS_KEY) == null){
      final PlayerLobby playerLobby = new PlayerLobby(templateEngine,players);
      httpSession.attribute( PLAYERS_KEY, playerLobby);
    }
    return templateEngine.render(new ModelAndView(vm , "home.ftl"));
  }

}