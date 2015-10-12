package test;
import net.Network;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Eric on 10/6/2015.
 */
public class ServerTest {
    Network net;
    @Test
    public void sendMessage(){
        net = new Network();
        net.connect("localhost",8000);
        while(!net.hasMessage()){}
        assertEquals("TConnection Successful\n", net.getMessage());
        net.send("THi");
        while(!net.hasMessage()){}
        assertEquals("THi\n", net.getMessage());
        net.send("TBye");
        while(!net.hasMessage()){}
        assertEquals("TBye\n", net.getMessage());

    }

}
