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
        Message text = new Message(MessageType.TEXT, "Woot");
        Message error = new Message("JThis Will not work");
        assertTrue(hit.getCoordinate().equals(new Coordinate(0,0)));
        assertEquals(MessageType.HIT, hit.getType());
        assertEquals("H00", hit.asString());
        assertEquals(MessageType.MISS, miss.getType());
        assertEquals("M00", miss.asString());
        assertEquals(MessageType.ATTACK, attack.getType());
        assertEquals("A00", attack.asString());
        assertEquals(MessageType.TEXT, text.getType());
        assertEquals("Woot", text.getMessage());
        assertEquals(MessageType.ERROR, error.getType());


    }
}
