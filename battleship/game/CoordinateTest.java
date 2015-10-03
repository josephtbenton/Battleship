package game;

import static org.junit.Assert.*;

public class CoordinateTest {

    @org.junit.Test
    public void testToString() throws Exception {
        Coordinates coor = new Coordinates(3, 4);
        AssertEquals("Should convert 3,4 to D5", "D5", coor.toString());
        Coordinates coor2 = new Coordinates(18, -4);
        AssertEquals("Coordinates 18,-4 are out of bounds, so it will not convert", "18-4", coor.toString());

    }

    @org.junit.Test
    public void testEquals() throws Exception {
        Coordinates coor1 = new Coordinates(8, 3);
        Coordinates coor2 = new Coordinates(7, 9);
        AssertFalse("coor1 and coor2 are not at the same location, so false should be returned", coor1.equals(coor2));
        Coordinates coor3 = new Coordinates(8, 3);
        AssertTrue("coor1 and coor3 are at the same location, so true should be returned", coor1.equals(coor3));

    }

    @org.junit.Test
    public void testGetX() throws Exception {
        Coordinates coor1 = new Coordinates(6, 2);
        AssertEquals("Should return just the int for x, which is 6", 6, coor1.getX();
        Coordinates coor2 = new Coordinates(8, 3);
        AssertEquals("Should return just the int for x, which is 8", 8, coor2.getX();
        Coordinates coor3 = new Coordinates(1, 7);
        AssertEquals("Should return just the int for x, which is 1", 1, coor3.getX();

    }

    @org.junit.Test
    public void testGetY() throws Exception {
        Coordinates coor1 = new Coordinates(6, 2);
        AssertEquals("Should return just the int for y, which is 2", 2, coor1.getY();
        Coordinates coor2 = new Coordinates(6, 5);
        AssertEquals("Should return just the int for y, which is 5", 5, coor2.getY();
        Coordinates coor3 = new Coordinates(7, 5);
        AssertEquals("Should return just the int for y, which is 5", 5, coor3.getY();

    }

    @org.junit.Test
    public void testIsLegal() throws Exception {
        Coordinates coor1 = new Coordinates(6, 2);
        AssertTrue("Should return that the coordinates are legal", coor1.isLegal();
        Coordinates coor2 = new Coordinates(1, 5);
        AssertTrue("Should return that the coordinates are legal", coor2.isLegal();
        Coordinates coor3 = new Coordinates(7, 13);
        AssertFalse("Should return that the coordinates are not legal", coor3.isLegal();
        Coordinates coor4 = new Coordinates(-2, 8);
        AssertFalse("Should return that the coordinates are not legal", coor4.isLegal();
        Coordinates coor5 = new Coordinates(-5, 27);
        AssertFalse("Should return that the coordinates are not legal", coor5.isLegal();
    }
}
