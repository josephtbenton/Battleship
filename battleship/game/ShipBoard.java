package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import net.Message;
import net.MessageType;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by reedmershon on 10/3/15.
 */
public class ShipBoard{

    HashSet<Ship> shipList = new HashSet<>();
    ArrayList<Coordinate> hits = new ArrayList<>();
    ArrayList<Coordinate> misses = new ArrayList<>();
    Ship tempShip;

    public void addShip(ShipType type, Direction dir, int x, int y) {
        Ship ship = new Ship(type, dir, x, y);
        for (Coordinate c : ship.getFootprint()) {
            if (!c.isLegal()) return;
            // TODO should this not throw an error?
        }
        shipList.add(ship);
    }

    public void setTempShip(Ship s) {
        tempShip = s;
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

    public void draw(GridPane display, Pane[][] handlers) {
        for (Node node : display.getChildren()) {
            node.setStyle("-fx-background-color: LIGHTBLUE");

            node.setStyle("-fx-stroke-color: BLACK");
        }
        if (tempShip != null) {
            tempShip.draw(display, handlers);
        }
        for (Ship s : shipList) {
            s.draw(display, handlers);
        }
    }

    public boolean allSunk() {
        for (Ship s : shipList) {
            if (s.isAfloat()) return false;
        }
        return true;
    }

    public int numShips() {
        return shipList.size();
    }

    public void addShip(Ship s) {
        shipList.add(s);
    }
}
