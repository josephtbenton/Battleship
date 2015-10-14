package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Ship {
    boolean afloat;
    int length;
    ShipType type;
    Coordinate root;
    Direction dir;
    boolean[] hits;
    Coordinate[] footprint;

    public Ship(ShipType type, Direction dir, int x, int y) {
        this.type = type;
        this.dir = dir;
        this.root = new Coordinate(x, y);
        this.length = type.getLength();
        hits = new boolean[length];
        footprint = calculateFootprint(root);
    }

    public void rotate() {
        dir = dir.getNext();
        footprint = calculateFootprint(root);
    }

    private Coordinate[] calculateFootprint(Coordinate root) {
        Coordinate[] coords = new Coordinate[length];
        Coordinate cur = root;
        for (int i = 0; i < coords.length; i++) {
            coords[i] = cur;
            cur = dir.nextCoord(cur);
        }
        return coords;
    }

    public Coordinate[] getFootprint() {
        return footprint;
    }

    public boolean isAfloat() {
        for (boolean hit : hits) {
            if (!hit) return true;
        }
        return false;
    }

    public boolean registerAttack(Coordinate coordinate) {
        for (int i = 0; i < footprint.length; i++) {
            if (coordinate.equals(footprint[i])) {
                recordHit(i);
                return true;
            }
        }
        return false;
    }

    private void recordHit(int index) {
        hits[index] = true;
    }


    public void draw(GridPane display, Pane[][] handlers) {
        Coordinate current = root;

        if (isLegalFootprint(footprint)) {
            for (int i = 0; i < length; i++) {
                if (current.isLegal()) {
                    String color = hits[i] ? "RED" : "DARKGREY";
                    Pane p = handlers[current.getX()][current.getY()];
                    p.setStyle("-fx-background-color: " + color);
                    current = dir.nextCoord(current);
                }
            }
        }
    }

    public boolean isLegalFootprint(Coordinate[] footprint) {
        for (Coordinate c : footprint) {
            if (!c.isLegal()) return false;
        }
        return true;
    }
    public boolean hasLegalFootprint() {
        for (Coordinate c : footprint) {
            if (!c.isLegal()) return false;
        }
        return true;
    }


    public void setRoot(int colIndex, int rowIndex) {
        root = new Coordinate(colIndex, rowIndex);
        footprint = calculateFootprint(root);
    }

    public boolean checkRoot(Coordinate root) {
        return isLegalFootprint(calculateFootprint(root));
    }

    public Coordinate getRoot() {
        return root;
    }
}
