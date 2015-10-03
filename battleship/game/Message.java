package game;

import java.nio.charset.MalformedInputException;

/**
 * Created by Eric on 10/2/2015.
 */
public class Message {
    private String input;

//    T,00,Message
    public Message(String input){
        this.input = input;
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
