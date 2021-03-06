package net;

import game.core.Coordinate;
import net.MessageType;

import java.util.HashMap;

/**
 * Created by Eric on 10/2/2015.
 */
public class Message {
    private String input;
    public Message(String input){
        this.input = input;
    }

    public Message(MessageType type, Coordinate coord){
        this.input = type.getCharacter() + coord.getX() + coord.getY();
    }



    // Do we even need this constructor? -E.H.
    public Message(MessageType type, String text){
        this.input = type.getCharacter() + text;
    }


    public Coordinate getCoordinate() {
        int x = Integer.parseInt(input.substring(1, 2));
        int y = Integer.parseInt(input.substring(2, 3));
        return new Coordinate(x, y);
    }

    public MessageType getType() {
        if (input != null && input.length() > 0) {
            char firstchar = input.charAt(0);
            // Do we need to change this into a dictionary call?
            if (firstchar == 'A') {
                return MessageType.ATTACK;
            } else if (firstchar == 'H') {
                return MessageType.HIT;
            } else if (firstchar == 'M') {
                return MessageType.MISS;
            } else if (firstchar == 'T') {
                return MessageType.TEXT;
            }
        }
        return MessageType.ERROR;
    }

    public String asString(){
        return input;
    }
    public String getMessage() {
        return input.substring(1);
    }
}
