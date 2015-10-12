package test;
import net.Network;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.fail;

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
        net.send("THi");
        net.send("HIIIII");

    }

}
