package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        footprint = calculateFootprint();
    }

    public void rotate() {
        dir = dir.getNext();
    }

    private Coordinate[] calculateFootprint() {
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


    public void draw(GridPane display) {
        Coordinate current = root;
        for (int i = 0; i < length; i++) {
            Color color = hits[i] ? Color.RED : Color.DARKGRAY;
            Rectangle rect = new Rectangle(50, 50, color);
            rect.setOnMouseClicked(ev -> {
                System.out.println("rotating");
                this.rotate();

            });
            display.add(rect, current.getX(), current.getY());
            current = dir.nextCoord(current);
        }
    }
}
