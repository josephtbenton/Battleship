package game;

import net.MessageType;

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

    public Message(MessageType type, String text){
        this.input = type.getCharacter() + text;
    }


    public Coordinate getCoordinate() {
        int x = Integer.parseInt(input.substring(1, 2));
        int y = Integer.parseInt(input.substring(2, 3));
        return new Coordinate(x, y);
    }

    public MessageType getType(){
        char firstchar = input.charAt(0);
        if (firstchar == 'A'){
            return MessageType.ATTACK;
        }
        else if (firstchar == 'H'){
            return MessageType.HIT;
        }
        else if (firstchar == 'M'){
            return MessageType.MISS;
        }
        else{
            return MessageType.TEXT;
        }
    }
    public String getMessage() {
        return input.substring(1);
    }
}
