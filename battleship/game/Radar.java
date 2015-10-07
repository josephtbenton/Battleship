package game;

import game.core.Board;
import game.core.Coordinate;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import net.Message;
import net.MessageType;

import java.util.ArrayList;


/**
 * Created by reedmershon on 10/3/15.
 */
public class Radar implements Board {

    ArrayList<Coordinate> hits;

    ArrayList<Coordinate> misses;



    @Override
    public void registerHit(Coordinate location) {
        hits.add(location);
    }

    @Override
    public void registerMiss(Coordinate location) {
        misses.add(location);
    }

    @Override
    public void draw(GridPane display) {
        for(Coordinate hit : hits) {
            display.add(new Rectangle(10, 10, Color.RED), hit.getX(), hit.getY());
        }
        for(Coordinate miss : misses) {
            display.add(new Rectangle(10, 10, Color.RED), miss.getX(), miss.getY());
        }
    }

    public Message generateAttack(Coordinate location) {
        return new Message(MessageType.ATTACK, location);
    }

}
