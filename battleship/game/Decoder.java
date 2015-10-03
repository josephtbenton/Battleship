package game;

/**
 * Created by Eric on 10/2/2015.
 */
public class Decoder {
    private String inputType;
    private boolean messageState;
    private boolean isHit;
    private Coordinate coordinate;


    public Decoder(String input){
        coordinate = getCoordinate(input);
//        isHit = coordinate.getState;

    }

    public Coordinate getCoordinate(String input){
//         TODO change void to our method of getting the coordinate
//         We may associate a coordinate with a boolean value to check if it gets hit or not
        return new Coordinate();
    }

    public boolean getHit(String input){
        return isHit;
    }

    public void setHit(Boolean status){
        isHit = status;
    }


}
