package com.webcheckers.appl;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;
import com.webcheckers.model.*;
import javafx.scene.control.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Appl-tier")
public class JsonUtilsTest {
    //Attributes
    private static final Gson GSON = new Gson();
    private JsonUtils jsonUtils = new JsonUtils();
    private final HashMap<String,Object> player = new HashMap<>();
    @Test
    public void test_toJson(){
        assertNotNull(jsonUtils.toJson(player));
    }
    @Test
    public void test_json(){
        jsonUtils.json();
    }
}
