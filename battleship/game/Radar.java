package game;

import game.core.Board;
import game.core.Coordinate;

import net.Message;
import net.MessageType;

import java.util.ArrayList;


/**
 * Created by reedmershon on 10/3/15.
 */
public class Radar implements Board {

    ArrayList<Coordinate> hits;

    ArrayList<Coordinate> misses;


    @Override
    public void registerHit(Coordinate location) {
        hits.add(location);
    }

    @Override
    public void registerMiss(Coordinate location) {
        misses.add(location);
    }

    public Message generateAttack(Coordinate location) {
        return new Message(MessageType.ATTACK, location);
    }

    public Coordinate highlightCoord(Coordinate location) {
        return location;
    }

}
