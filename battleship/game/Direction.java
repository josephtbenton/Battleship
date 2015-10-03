package game;

/**
 * Created by josephbenton on 10/3/15.
 */
public enum Direction {
    NORTH {
        @Override
        public Coordinate nextCoord(Coordinate current) {
            return new Coordinate(current.getX(), current.getY() - 1);        }
    },
    SOUTH {
        @Override
        public Coordinate nextCoord(Coordinate current) {
            return new Coordinate(current.getX(), current.getY() + 1);        }
    },
    EAST {
        @Override
        public Coordinate nextCoord(Coordinate current) {
            return new Coordinate(current.getX() + 1, current.getY());        }
    },
    WEST {
        @Override
        public Coordinate nextCoord(Coordinate current) {
            return new Coordinate(current.getX() - 1, current.getY() - 1);        }
    };

    private Direction next;
    static {
        NORTH.next = EAST;
        EAST.next = SOUTH;
        SOUTH.next = WEST;
        WEST.next = NORTH;
    }
    public Direction getNext() {
        return next;
    }
    public abstract Coordinate nextCoord(Coordinate current);
}
