package game;

/**
 * Created by Eric on 10/2/2015.
 */
public class Decoder {
    private Coordinate coordinate;
    private String input;

//    T,00,Message
    public Decoder(String input){
        this.input = input;
        coordinate = calculateCoordinate();
    }


    public Coordinate calculateCoordinate(){
        int x = Integer.parseInt(input.substring(1,2));
        int y = Integer.parseInt(input.substring(2,3));
        return new Coordinate(x,y);
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public boolean isAttack(){
        return input.charAt(0) == 'A';
    }

    public boolean hasMessage(){
        return input.length() >= 4;
    }

    public String getMessage(){
        if (hasMessage()) {
            return input.substring(3);
        }
        return "";
    }
}
