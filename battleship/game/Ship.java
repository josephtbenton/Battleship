package game;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Ship {
    boolean afloat;
    int length;
    ShipType type;
    Coordinate root;
    Direction dir;
    public Ship(int x, int y, ShipType type) {
        this.root = new Coordinate(x, y);
        this.length = type.getLength();
        this.type = type;
        this.dir = Direction.EAST;
    }

    public void rotate() {
        dir = dir.getNext();
    }

    public Coordinate[] getFootprint() {
        Coordinate[] coords = new Coordinate[length];
        Coordinate cur = root;
        for (int i = 0; i < coords.length; i++) {
            coords[i] = cur;
            cur = dir.nextCoord(cur);
        }
        return coords;
    }
}
