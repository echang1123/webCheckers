/**
 * GET "/" Route Handler
 *
 * @author Eugene Chang
 * @author Karthik Iyer
 * @author Gaurav Pant
 */


package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;


public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger( GetHomeRoute.class.getName() );

  // Keys
  static final String PLAYER_LOBBY_KEY = "playerLobby";
  static final String SIGNED_IN = "isSignedIn";
  static final String CURRENT_PLAYER = "currentPlayer";
  static final String PLAYERS = "players";
  public static final String INGAME_URL = "/board";

  // Attributes
  private final TemplateEngine templateEngine;
  private HashMap< String, Player > players;


  /**
   * Constructor for the GetHomeRoute routehandler
   * @param templateEngine the HTML template rendering engine
   */
  public GetHomeRoute( final TemplateEngine templateEngine, final HashMap< String, Player > players ) {
    // validation
    Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
    Objects.requireNonNull( templateEngine, "players must not be null" );

    this.templateEngine = templateEngine;
    this.players = players;
    LOG.config( "GetHomeRoute is initialized." );
  }


  /**
   * Render the WebCheckers Home page.
   * @param request the HTTP request
   * @param response the HTTP response
   * @return the rendered HTML for the Home page
   */
  @Override
  public Object handle( Request request, Response response ) {
    LOG.finer( "GetHomeRoute is invoked.") ;
    // Create the view model
    Map< String, Object > vm = new HashMap<>();
    vm.put( "title", "Welcome!" );
    final Session httpSession = request.session();

    if( httpSession.attribute(PLAYER_LOBBY_KEY) == null ) { // first time opening
      final PlayerLobby playerLobby = new PlayerLobby( players );
      httpSession.attribute( PLAYER_LOBBY_KEY, playerLobby );
    }

    if( ( httpSession.attribute( SIGNED_IN ) == null ) || ( httpSession.attribute( SIGNED_IN ).equals( false ) ) ) {
      // player has not signed in
      httpSession.attribute( SIGNED_IN, false );
      vm.put( SIGNED_IN, false );
      vm.put( PLAYERS, players );
    }

    else { // player is signed in
      vm.put( CURRENT_PLAYER, httpSession.attribute( CURRENT_PLAYER ) );
      Map< String, Player > otherPlayers = new HashMap<>( players );
      otherPlayers.remove( httpSession.attribute( CURRENT_PLAYER ) ); // remove the current player, so doesn't get shown
      vm.put( PLAYERS, otherPlayers );
      vm.put( SIGNED_IN, true );

//      for(Map.Entry<String, Player> e: otherPlayers.entrySet()){
//        String opponentName = e.getValue().getOpponent().getName();
//        String myName = httpSession.attribute( CURRENT_PLAYER );
//        Player me = players.get(myName);
//        if(opponentName.equals(myName)){
//          boolean isAdded = me.addOpponent(e.getValue());
//          if(isAdded){
//            return templateEngine.render(new ModelAndView(vm, "game.ftl"));
//          }
//          else{
//              String msg = "Player in game selected different player!";
//              vm.put( "message", msg); // add the error message
//          }
//        }
//      }


    }
    return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
  }

}