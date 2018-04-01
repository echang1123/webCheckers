/*
 * Class that represents the text of the message from the server
 *
 * @author Karthik Iyer
 * @author Emily Wesson
 * @author Hongda Lin
 */


package com.webcheckers.model;


import java.io.Serializable;


public class Message implements Serializable {

    // Enums
    public enum MessageType {
        info, error
    } // the type of the message

    // Attributes
    private String text; // the text of the message
    private MessageType type;


    /**
     * Constructor for the Message class
     *
     * @param text the text of the message
     * @param type the type of the message
     */
    public Message( String text, MessageType type ) {
        this.text = text;
        this.type = type;
    }


    /**
     * Getter for the message text
     *
     * @return the text of the message
     */
    public String getText() {
        return this.text;
    }


    /**
     * Getter for the message type
     *
     * @return the type of the message
     */
    public MessageType getType() {
        return this.type;
    }

}
