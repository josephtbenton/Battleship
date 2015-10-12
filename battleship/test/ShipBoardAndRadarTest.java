package test;

/**
 * Created by Eric on 10/9/2015.
 */

import game.Radar;
import game.ShipBoard;
import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipBoardAndRadarTest {

    @Test
    public void MakeorBreakRadar(){
        Radar r = new Radar();
        r.registerHit(new Coordinate(0,0));
        r.registerMiss(new Coordinate(0,0));
        r.highlightCoord(new Coordinate(0,0));
    }
    @Test
    public void MakeorBreakShipBoard(){
        ShipBoard s = new ShipBoard();
        s.addShip(ShipType.BATTLESHIP, Direction.WEST, 0,0);
        s.checkAttack(new Coordinate(0,0));

        assertEquals(true, s.allSunk());
        s.addShip(ShipType.BATTLESHIP, Direction.EAST, 0,0);
        assertEquals(false, s.allSunk());
        s.checkAttack(new Coordinate(0,0));
        assertEquals(false, s.allSunk());
        s.checkAttack(new Coordinate(1,0));
        assertEquals(false, s.allSunk());
        s.checkAttack(new Coordinate(2,0));
        assertEquals(false, s.allSunk());
        s.checkAttack(new Coordinate(3,0));
        assertEquals(true, s.allSunk());


    }

}
