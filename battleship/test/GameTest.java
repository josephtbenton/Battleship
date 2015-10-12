package test;

import game.Game;
import game.core.Coordinate;
import javafx.scene.control.TextArea;
import net.Message;
import net.MessageType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Eric on 10/11/2015.
 */
public class GameTest {
    Game g;

    @Test
    public void makeNewGame(){
        g = new Game();

    }
    @Test
    public void checkGameProperties(){
        g = new Game();
        assertEquals(false,g.isTurn());
        g.setTurn(true);
        assertEquals(true,g.isTurn());
        g.sendAttack(0,0);
        assertTrue(g.hasMessage());
        assertEquals("A00", g.getMessage());
    }


}
