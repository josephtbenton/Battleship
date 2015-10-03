package game;

/**
 * Created by reedmershon on 10/3/15.
 */
public interface Board {
    public boolean registerHit(Coordinate location);
    public boolean registerMiss(Coordinate location);
}
