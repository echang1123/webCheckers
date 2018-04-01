/*
 * Class that contains all the relevant routes and map keys for easy access
 *
 * @author Karthik Iyer
 */


package com.webcheckers.appl;

public class RoutesAndKeys {

    // ROUTES

    public static final String HOME_URL = "/";
    public static final String SIGN_IN_URL = "/signin";
    public static final String SIGN_OUT_URL = "/signout";
    public static final String GAME_URL = "/game";
    public static final String VALIDATE_MOVE_URL = "/validateMove";
    public static final String CHECK_TURN_URL = "/checkTurn";
    public static final String BACKUP_MOVE_URL = "/backupMove";
    public static final String SUBMIT_TURN_URL = "/submitTurn";
    public static final String RESIGN_GAME_URL = "/resignGame";

    // KEYS

    public static final String SIGNED_IN_KEY = "isSignedIn";
    public static final String CURRENT_PLAYER_KEY = "currentPlayer";
    public static final String PLAYERS_KEY = "players";
    public static final String PLAYER_LOBBY_KEY = "playerLobby";
    public static final String GAME_LOBBY_KEY = "gameLobby";
    public static final String GLOBAL_INFORMATION_KEY = "globalInformation";
    public static final String IN_GAME_KEY = "inGame";
    public static final String MESSAGE_KEY = "message";
}
