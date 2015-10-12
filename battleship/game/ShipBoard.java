package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.layout.GridPane;
import net.Message;
import net.MessageType;

import java.util.ArrayList;

/**
 * Created by reedmershon on 10/3/15.
 */
public class ShipBoard{

    ArrayList<Ship> shipList = new ArrayList<>();
    ArrayList<Coordinate> hits = new ArrayList<>();
    ArrayList<Coordinate> misses = new ArrayList<>();

    public void addShip(ShipType type, Direction dir, int x, int y) {
        Ship ship = new Ship(type, dir, x, y);
        for (Coordinate c : ship.getFootprint()) {
            if (!c.isLegal()) return;
            // TODO should this not throw an error?
        }
        shipList.add(ship);
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

    private void registerHit(Coordinate location) {
        hits.add(location);
    }

    private void registerMiss(Coordinate location) {
        misses.add(location);
    }

    public void draw(GridPane display) {
        for (Ship s : shipList) {
            s.draw(display);
        }
    }

    public boolean allSunk() {
        for (Ship s : shipList) {
            if (s.isAfloat() == false) return false;
        }
        return true;
    }
}
