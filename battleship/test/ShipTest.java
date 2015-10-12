package test;

/**
 * Created by Eric on 10/8/2015.
 */
import game.Ship;
import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import org.junit.Test;
import static org.junit.Assert.*;
public class ShipTest {
    private int xRoot = 4;
    private int yRoot = 4;


    @Test
    public void buildShip(){
        for (ShipType shipType : ShipType.values()) {
            Ship ship = new Ship(shipType, Direction.EAST,xRoot,yRoot);
            EastTest(ship);
            ship.rotate();
            SouthTest(ship);
            ship.rotate();
            WestTest(ship);
            ship.rotate();
            NorthTest(ship);
        }
    }

    @Test
    public void hitShip(){
        Ship ship = new Ship(ShipType.DESTROYER, Direction.SOUTH, xRoot, yRoot);
        assertTrue(ship.isAfloat());
        ship.registerAttack(new Coordinate(xRoot,yRoot));
        assertTrue(ship.isAfloat());
        ship.registerAttack((new Coordinate(xRoot,yRoot+1)));
        assertFalse(ship.isAfloat());

    }

    public void SouthTest(Ship ship){
        Coordinate[] coors = {new Coordinate(xRoot,yRoot), new Coordinate(xRoot,yRoot+1), new Coordinate(xRoot,yRoot+2),
                new Coordinate(xRoot,yRoot+3), new Coordinate(xRoot,yRoot+4)};
        for (int i = 0; i < ship.getFootprint().length; i++){
            assertTrue(coors[i].equals(ship.getFootprint()[i]));
        }
    }
    public void NorthTest(Ship ship){
        Coordinate[] coors = {new Coordinate(xRoot,yRoot), new Coordinate(xRoot,yRoot-1), new Coordinate(xRoot, yRoot-2)
        , new Coordinate(xRoot, yRoot-3), new Coordinate(xRoot,yRoot-4)};
        for (int i = 0; i < ship.getFootprint().length; i++){
            assertTrue(coors[i].equals(ship.getFootprint()[i]));
        }

    }
    public void WestTest(Ship ship){
        Coordinate[] coors = {new Coordinate(xRoot,yRoot), new Coordinate(xRoot-1,yRoot), new Coordinate(xRoot-2, yRoot)
                , new Coordinate(xRoot-3, yRoot), new Coordinate(xRoot-4,yRoot)};
        for (int i = 0; i < ship.getFootprint().length; i++){
            assertTrue(coors[i].equals(ship.getFootprint()[i]));
        }
    }
    public void EastTest(Ship ship){
        Coordinate[] coors = {new Coordinate(xRoot,yRoot), new Coordinate(xRoot+1,yRoot),
                new Coordinate(xRoot+2,yRoot), new Coordinate(xRoot+3,yRoot),new Coordinate(xRoot+4,yRoot)};
        for (int i = 0; i < ship.getFootprint().length; i++){
            assertTrue(coors[i].equals(ship.getFootprint()[i]));
        }
    }

}
