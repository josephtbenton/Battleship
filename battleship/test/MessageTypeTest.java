package test;

/**
 * Created by Eric on 10/11/2015.
 */
import net.MessageType;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTypeTest {
    @Test
    public void MakeMessages(){
        assertEquals("A", MessageType.ATTACK.getCharacter());
        assertEquals("H", MessageType.HIT.getCharacter());
        assertEquals("M", MessageType.MISS.getCharacter());
        assertEquals("T", MessageType.TEXT.getCharacter());
    }
}
