/*
 * Tester class for Player
 *
 * @author Hongda Lin
 */

package com.webcheckers.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class PlayerTest {

    private static final String name = "Hongda";
    private static final String opponent = "Karthik";
    private static final boolean isIngame = false;

    /**
     * Tests that the constructor works without failure
     */
    @Test
    public void ctor_withArg() {
        final Player CuT = new Player(name);
        assertEquals(name, CuT.getName());
    }

    /**
     * Tests the getter for the name
     */
    @Test
    public  void getNameTest(){
        Player CuT = new Player(name);
        assertEquals(name, CuT.getName());
    }

    /**
     * Tests the getter for the opponent
     */
    @Test
    public  void getOpponentTest(){
        Player CuT = new Player(name);
        CuT.addOpponent(new Player(opponent));
        assertEquals(opponent, CuT.getOpponent().getName());
    }
    @Test
    public  void  addOpponentTest(){
        Player CuT = new Player(opponent);
        assertFalse(isIngame);
    }
}
