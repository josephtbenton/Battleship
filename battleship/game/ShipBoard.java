package game;

import game.core.Board;
import game.core.Coordinate;
import game.core.ShipType;
import javafx.scene.layout.GridPane;
import net.Message;
import net.MessageType;

import java.util.ArrayList;

/**
 * Created by reedmershon on 10/3/15.
 */
public class ShipBoard implements Board {

    ArrayList<Ship> shipList;
    ArrayList<Coordinate> hits;
    ArrayList<Coordinate> misses;

    public void generateShips() {
        int shipIndex = 0;
        for (ShipType Type : ShipType.values()) {
            Ship ship = new Ship(0, 0, Type);
            shipList.add(shipIndex, ship);
        }
    }


    public Message checkAttack(Coordinate location) {
        for (Ship s : shipList) {
            boolean hit = s.registerAttack(location);
            if (hit) {
                return new Message(MessageType.HIT, location);
            }
        }
        return new Message(MessageType.MISS, location);
    }

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
        for (Ship s : shipList) {
            s.draw(display);
        }
    }
}
