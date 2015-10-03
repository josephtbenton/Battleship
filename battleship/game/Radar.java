package game;

import game.core.Board;
import game.core.Coordinate;
import net.Message;
import net.MessageType;

/**
 * Created by reedmershon on 10/3/15.
 */
public class Radar implements Board {


    @Override
    public boolean registerHit(Coordinate location) {
        return false;
    }

    @Override
    public boolean registerMiss(Coordinate location) {
        return false;
    }

    public Message generateAttack(Coordinate location) {
        return new Message(MessageType.ATTACK, location);
    }

}
