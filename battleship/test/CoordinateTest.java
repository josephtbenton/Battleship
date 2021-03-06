package test;

import game.core.Coordinate;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CoordinateTest {


    @Test
    public void createCoordinates(){
        HashMap<Integer, String> dict = new HashMap<>();
        dict.put(0,"A");
        dict.put(1,"B");
        dict.put(2,"C");
        dict.put(3,"D");
        dict.put(4,"E");
        dict.put(5,"F");
        dict.put(6,"G");
        dict.put(7,"H");
        dict.put(8,"I");
        dict.put(9,"J");
        for(int x =0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                Coordinate coordinate = new Coordinate(x,y);
                Coordinate coordinate2 = new Coordinate(x,y);
                Coordinate coordinate3 = new Coordinate(0,10);
                assertEquals(x, coordinate.getX());
                assertEquals(y, coordinate.getY());
                assertTrue(coordinate.isLegal());
                assertEquals(coordinate2.equals(coordinate),Boolean.TRUE);
                assertNotEquals(coordinate3.equals(coordinate),Boolean.TRUE);
                String shouldBe = dict.get(x)+(y+1);
                assertEquals(shouldBe, coordinate.toString());
            }
        }
    }
    @Test
    public void legalTest(){
        Coordinate[] coordlist = {new Coordinate(-1,-1), new Coordinate(-1,0),
        new Coordinate(0,-1), new Coordinate(11,10), new Coordinate(10,11),
        new Coordinate(11,11)};
        for (Coordinate coordinate : coordlist){
            assertFalse(coordinate.isLegal());
        }

    }

}
