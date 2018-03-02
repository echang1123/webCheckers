package com.webcheckers.appl;

import com.webcheckers.model.Player;
import spark.TemplateEngine;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by Eugene on 3/1/2018.
 */
public class PlayerLobby {
    private static final Logger LOG = Logger.getLogger(PlayerLobby.class.getName());
    private final TemplateEngine templateEngine;
    private HashMap<String, Object> players = new HashMap<>();
//    private Boolean signedIn;

    public PlayerLobby (final TemplateEngine templateEngine, final HashMap<String,Object> players) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.players = players;
        LOG.fine("New player lobby instance created.");
    }

    public void addPlayer(Player p){
        players.put(p.getName(),null);
    }
    public String removePlayer(Player p){
        if (players.containsKey(p.getName())) {
            players.remove(p.getName());
        }
        return p.getName();
    }
}
