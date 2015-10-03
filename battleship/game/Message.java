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


    public Coordinate getCoordinate() throws Exception{
        if (!isMessage()){
            int x = Integer.parseInt(input.substring(1, 2));
            int y = Integer.parseInt(input.substring(2, 3));
            return new Coordinate(x, y);

        }
        else{
            throw new Exception("NotACoordinate");
        }
    }

    public boolean isAttack(){
        return input.charAt(0) == 'A';
    }

    public boolean isMessage(){
        return input.charAt(0) == 'T';
    }
    public boolean isHit(){
        return input.charAt(0) == 'H';
    }

    public boolean isMiss(){
        return input.charAt(0) == 'M';
    }


    public String getMessage() throws Exception{
        if (isMessage()){
            return input.substring(1);
        }
        else{
            throw new Exception("NotAMessage");
        }
    }
}
