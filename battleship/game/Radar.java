package game;

/**
 * Created by reedmershon on 10/3/15.
 */
public class Radar implements Board {


    @Override
    public boolean registerHit(Coordinate location) {
        return false;
    }

    @Override
    public boolean registerMiss(Coordinate location) {
        return false;
    }


}
