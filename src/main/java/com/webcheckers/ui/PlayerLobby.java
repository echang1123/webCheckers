package com.webcheckers.ui;

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
    private Boolean signedIn;

    public PlayerLobby (final TemplateEngine templateEngine, final HashMap<String,Object> players) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
        this.players = players;
        this.signedIn = false;

        LOG.fine("New player lobby instance created.");
    }
}
