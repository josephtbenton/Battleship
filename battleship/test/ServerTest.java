package test;
import net.Network;
import net.Server;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
/**
 * Created by Eric on 10/6/2015.
 */
public class ServerTest {
    Server server;
    Network net;

    /*@Test
    public void serverSetup() throws IOException {
        server = new Server(8000);
        server.close();
    }*/

//    @Test
//    public void createNet(){
//        net = new Network();
//    }

    @Test
    public void sendMessage(){
        net = new Network();
        net.connect("localhost",8000);
//        System.out.println(net.hasMessage());
        net.send("THi");
//        System.out.println(net.hasMessage());
        net.send("HIIIII");
//        System.out.println(net.hasMessage());
//        System.out.println(net.getMessage());
//         net.close();
    }

}
