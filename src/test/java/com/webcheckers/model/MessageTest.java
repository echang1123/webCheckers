package com.webcheckers.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * The unit test suite for the PostValidateMoveRoute
 *
 * @author Hongda Lin
 */
@Tag("Model-tier")
public class MessageTest {
    //Attributes
    private static String text = "Test";
    private Message.MessageType messageType =  Message.MessageType.info;
    private Message message = new Message(text,messageType);

    @Test
    public void test_Message(){
        assertNotNull(message);
    }
    @Test
    public void test_getText(){
        assertNotNull(message.getText());
        assertEquals(message.getText(),text);
    }
    @Test
    public void test_getType(){
        assertNotNull(message.getType());
        assertEquals(message.getType(),messageType);
    }
}
