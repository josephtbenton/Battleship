package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;

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

    public Ship(int x, int y, ShipType type) {
        this.root = new Coordinate(x, y);
        this.length = type.getLength();
        this.type = type;
        this.dir = Direction.EAST;
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

    public void registerAttack(Coordinate coordinate) {
        for (int i = 0; i < footprint.length; i++) {
            if (coordinate.equals(footprint[i])) {
                recordHit(i);
            }
        }
    }
    private void recordHit(int index) {
        hits[index] = true;
    }
}
