package test;

import static org.junit.Assert.*;

public class CoordinateTest {

    @org.junit.Test
    public void testToString() throws Exception {
        Coordinates coor = new Coordinates();
        coor.x = 3;
        coor.y = 4;
        AssertEquals("Should convert 3,4 to D5", "D5", coor.toString());
        
        coor.x = 18;
        coor.y = -4;
        AsserEquals("Coordinates 18,-4 are out of bounds, so it will not convert", "18-4", coor.toString());

    }

    @org.junit.Test
    public void testEquals() throws Exception {
        Coordinates coor1 = new Coordinates();
        Coordinates coor2 = new Coordinates();
        coor1.x = 8;
        coor1.y = 3;
        coor2.x = 7;
        coor2.y = 9;
        AsserFalse("coor1 and coor2 are not at the same location, so false should be returned", coor1.equals(coor2));
        coor2.x = 8;
        coor2.y = 3;
        AsserFalse("coor1 and coor2 are now at the same location, so true should be returned", coor1.equals(coor2));

    }

    @org.junit.Test
    public void testGetX() throws Exception {
        Coordinates coor = new Coordinates();
        coor.x = 6;
        coor.y = 2;
        AsserEquals("Should return just the int for x, which is 6", 6, coor.getX();
        coor.x = 8;
        AsserEquals("Should return just the int for x, which is 8", 8, coor.getX();
        coor.y = 7;
        AsserEquals("Should return just the int for x, which is 8", 8, coor.getX();

    }

    @org.junit.Test
    public void testGetY() throws Exception {
        Coordinates coor = new Coordinates();
        coor.x = 6;
        coor.y = 2;
        AsserEquals("Should return just the int for y, which is 2", 2, coor.getY();
        coor.y = 5;
        AsserEquals("Should return just the int for y, which is 5", 5, coor.getY();
        coor.x = 7;
        AsserEquals("Should return just the int for y, which is 5", 5, coor.getY();

    }

    @org.junit.Test
    public void testIsLegal() throws Exception {
        Coordinates coor = new Coordinates();
        coor.x = 6;
        coor.y = 2;
        AssertTrue("Should return that the coordinates are legal", coor.isLegal();
        coor.x = 1;
        coor.y = 5;
        AssertTrue("Should return that the coordinates are legal", coor.isLegal();
        coor.x = 7;
        coor.y = 13;
        AssertFalse("Should return that the coordinates are not legal", coor.isLegal();
        coor.x = -2;
        coor.y = 8
        AssertTrue("Should return that the coordinates are legal", coor.isLegal();

    }
}
