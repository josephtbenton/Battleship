package test;

import game.core.Coordinate;
import net.Message;
import net.MessageType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eric on 10/11/2015.
 */

public class MessageTest {
    @Test
    public void createMessages(){
        Message hit = new Message(MessageType.HIT,new Coordinate(0,0));
        Message miss = new Message(MessageType.MISS, new Coordinate(0,0));
        Message attack = new Message(MessageType.ATTACK, new Coordinate(0,0));
        assertTrue(hit.getCoordinate().equals(new Coordinate(0,0)));
        assertEquals(MessageType.HIT, hit.getType());
        assertEquals(MessageType.MISS, miss.getType());
        assertEquals(MessageType.ATTACK, attack.getType());
    }
}
