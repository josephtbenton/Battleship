package game;

import game.core.Board;
import game.core.Coordinate;
import game.core.ShipType;

import java.util.ArrayList;

/**
 * Created by reedmershon on 10/3/15.
 */
public class ShipBoard implements Board{

    ArrayList<Ship> shipList;

    public void generateShips() {
        int shipIndex = 0;
        for (ShipType Type : ShipType.values()) {
            Ship ship = new Ship(0, 0, Type);
            shipList.add(shipIndex, ship);
        }
    }


    public void registerAttack(Coordinate location) {
        for (int i = 0; i < shipList.size(); i++) {
            shipList.get(i).registerAttack(location);
        }
    }

    @Override
    public boolean registerHit(Coordinate location) {
        return false;
    }

    @Override
    public boolean registerMiss(Coordinate location) {
        return false;
    }
}
