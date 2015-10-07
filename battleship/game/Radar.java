package game;

import game.core.Coordinate;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import net.Message;
import net.MessageType;

import java.util.ArrayList;


/**
 * Created by reedmershon on 10/3/15.
 */
public class Radar {

    ArrayList<Coordinate> hits;

    ArrayList<Coordinate> misses;

    public Radar() {
        this.hits = new ArrayList<>();
        this.misses = new ArrayList<>();
    }


    public void registerHit(Coordinate location) {
        hits.add(location);
    }


    public void registerMiss(Coordinate location) {
        misses.add(location);
    }

    public void draw(GridPane display) {
        for(Coordinate hit : hits) {
            display.add(new Circle(25, Color.RED), hit.getX(), hit.getY());
        }
        for(Coordinate miss : misses) {
            display.add(new Circle(25, Color.WHITE), miss.getX(), miss.getY());
        }
    }

    public Message generateAttack(Coordinate location) {
        return new Message(MessageType.ATTACK, location);
    }

    public Coordinate highlightCoord(Coordinate location) {
        return location;
    }

}
