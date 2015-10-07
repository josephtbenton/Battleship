package game.core;

import game.core.Coordinate;

/**
 * Created by reedmershon on 10/3/15.
 */
public interface Board {
    public void registerHit(Coordinate location);
    public void registerMiss(Coordinate location);
}
