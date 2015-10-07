package game.core;

import game.core.Coordinate;
import javafx.scene.layout.GridPane;

/**
 * Created by reedmershon on 10/3/15.
 */
public interface Board {
    public void registerHit(Coordinate location);
    public void registerMiss(Coordinate location);
    public void draw(GridPane display);
}
