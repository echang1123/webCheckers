/*
 * GET "/game" Route Handler
 *
 * @author Karthik Iyer
 * @author Eugene Chang
 * @author Emily Wesson
 */


package com.webcheckers.ui;


import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.GlobalInformation;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.RoutesAndKeys;
import com.webcheckers.model.*;
import spark.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;


public class GetGameRoute implements Route {

    public enum ViewMode {PLAY, SPECTATOR, REPLAY}

    private static final Logger LOG = Logger.getLogger( GetSignInRoute.class.getName() );
    private final TemplateEngine templateEngine;
    private final GlobalInformation gi;

    /**
     * Constructor for the GetGameRoute route handler
     *
     * @param templateEngine the HTML template rendering engine
     * @param gi             the Global Information
     */
    public GetGameRoute( final TemplateEngine templateEngine, final GlobalInformation gi ) {
        // validation
        Objects.requireNonNull( templateEngine, "templateEngine must not be null" );
        Objects.requireNonNull( gi, "GI must not be null" );

        this.templateEngine = templateEngine;
        this.gi = gi;
        LOG.config( "GetGameRoute is initialized." );
    }


    /**
     * Render the signed-in WebCheckers Home page.
     *
     * @param request  the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle( Request request, Response response ) {
        LOG.finer( "GetGameRoute is invoked." );

        // construct the view model
        Map< String, Object > vm = new HashMap<>();
        vm.put( "title", "Welcome!" );

        final Session httpSession = request.session();
        final PlayerLobby playerLobby = gi.getPlayerLobby();
        HashMap< String, Player > players = playerLobby.getPlayers();

        String currentPlayerName = httpSession.attribute( RoutesAndKeys.CURRENT_PLAYER_KEY );
        if( currentPlayerName == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        Player currentPlayer = players.get( currentPlayerName );
        if( currentPlayer == null ) {
            response.redirect( RoutesAndKeys.HOME_URL );
        }

        // create map with all players, and remove the current player from it
        Map< String, Player > otherPlayers = new HashMap<>( players );
        otherPlayers.remove( currentPlayerName ); // remove the current player from being shown

        // add main info

        Board boardModel;
        BoardView board;
        GameLobby gameLobby = gi.getGameLobby();
        Player opponent;
        Game game;

        vm.put( "viewMode", ViewMode.PLAY );


        // Player is not in game, that means we are opening this board for the first time
        if( ( httpSession.attribute( RoutesAndKeys.IN_GAME_KEY ) == null )
            || ( httpSession.attribute( RoutesAndKeys.IN_GAME_KEY ).equals( false ) ) ) {
            // We have to determine if the current player is the first player
            // If it is the first player:
            // - Create a new Game with a new Board
            // - Add the Game to the GameLobby
            // - Generate a BoardView for the first player
            // - Render game.ftl
            //
            // If it is the second player:
            // - Retrieve the Game from the GameLobby
            // - Generate a BoardView for the second player
            // - Render game.ftl

            // check if you are the first player
            Boolean isFirstPlayer = false;
            String opponentName = ""; // initialize the opponent name with an empty string
            for( String playerName : players.keySet() ) { // iterate through all the players
                if( playerName.equals( currentPlayerName ) ) // skip if we run into the current player
                    continue;
                else { // otherwise, for other players...
                    String playerButton = request.queryParams( playerName ); // get the button
                    if( playerButton == null ) // not pressed
                        continue;
                    else { // has been pressed
                        opponentName = playerName; // get the opponent's name
                        if( players.get( opponentName ).getOpponent() != null ) { // the selected opponent is already in game
                            String message = "Player \"" + opponentName + "\" is already playing a game.";
                            vm.put( "message", message );
                            vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayerName );
                            vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
                            vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );
                            // render home with error message
                            return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
                        }
                        isFirstPlayer = true;
                        break;
                    }
                }
            }
            if( isFirstPlayer ) {
                boardModel = new Board();
                opponent = players.get( opponentName );
                currentPlayer.addOpponent( opponent );
                game = new Game( boardModel, currentPlayer, opponent );
                gameLobby.addGame( game );
                vm.put( "activeColor", Piece.Color.RED );
                vm.put( "redPlayer", currentPlayer );
                vm.put( "whitePlayer", opponent );
            } else { // you are the second player
                game = gameLobby.findGame( currentPlayer ); // get the game that was created by the first player
                boardModel = game.getBoard();
                opponent = game.getPlayerOne();
                currentPlayer.addOpponent( opponent );
                vm.put( "activeColor", Piece.Color.RED );
                vm.put( "redPlayer", opponent );
                vm.put( "whitePlayer", currentPlayer );
            }

            board = new BoardView( boardModel, isFirstPlayer );
            vm.put( "board", board );
            vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayer );
            httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, true );
        }


        // Player is already in game, that means we need to get the up to date version of the existing board:
        // - Retrieve the Game from the GameLobby using currentPlayer as a key for searching
        // - Based on the information in Game, set the vm attributes
        // - Determine if currentPlayer is playerOne or playerTwo, and accordingly generate a BoardView
        // - Render game.ftl
        else {
            game = gameLobby.findGame( currentPlayer ); // get the game that the player is in
            boardModel = game.getBoard(); // get the board that has been updated with all moves made
            Player playerOne = game.getPlayerOne();
            Player playerTwo = game.getPlayerTwo();
            vm.put( "redPlayer", playerOne );
            vm.put( "whitePlayer", playerTwo );

            // if your opponent is null, they resigned so: remove your opponent, remove the game from the lobby
            // set inGame to false, create message that your opponent resigned, populate vm and render home
            if( currentPlayer.equals( playerOne ) && game.isComplete() ) {
                currentPlayer.removeOpponent();

                gameLobby.removeGame( game );
                httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );

                Message message = new Message( "You won the game", Message.MessageType.info );
                vm.put( RoutesAndKeys.MESSAGE_KEY, message );
                vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayer );
                vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
                vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );

                return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
            }

            if( currentPlayer.equals( playerTwo ) && game.isComplete() ) {
                currentPlayer.removeOpponent();

                gameLobby.removeGame( game );
                httpSession.attribute( RoutesAndKeys.IN_GAME_KEY, false );

                Message message = new Message( "Player 1 resigned.", Message.MessageType.info );
                vm.put( RoutesAndKeys.MESSAGE_KEY, message );
                vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayer );
                vm.put( RoutesAndKeys.PLAYERS_KEY, otherPlayers );
                vm.put( RoutesAndKeys.SIGNED_IN_KEY, true );

                return templateEngine.render( new ModelAndView( vm, "home.ftl" ) );
            }


            int whoseTurn = game.getWhoseTurn();
            if( whoseTurn == 0 ) { // it is player one's turn (red)
                vm.put( "activeColor", Piece.Color.RED );
            } else {
                vm.put( "activeColor", Piece.Color.WHITE );
            }

            // currentPlayer will be the first player if it is the same as playerOne
            board = new BoardView( boardModel, currentPlayer.equals( playerOne ) );
            vm.put( RoutesAndKeys.CURRENT_PLAYER_KEY, currentPlayer );
            vm.put( "board", board );
        }

        // render
        return templateEngine.render( new ModelAndView( vm, "game.ftl" ) );

    }
}
