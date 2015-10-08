package game;

import com.sun.jmx.remote.internal.ArrayQueue;
import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import net.Message;
import net.MessageType;
import net.Network;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Game {
    Radar radar;
    ShipBoard shipBoard;
    ArrayBlockingQueue<Message> outgoing;
    Text out;

    public Game(Text out) {
        this.radar = new Radar();
        this.shipBoard = new ShipBoard();
        this.outgoing = new ArrayBlockingQueue<Message>(1);
        this.out = out;
    }

    public void receiveMessage(String incoming) {
        Message message = new Message(incoming);
        if (message.getType() == MessageType.ATTACK) {
            outgoing.add(shipBoard.checkAttack(message.getCoordinate()));
            print("Attack at " + message.getCoordinate().toString());
        } else if (message.getType() == MessageType.HIT) {
            radar.registerHit(message.getCoordinate());
            print("You hit at " + message.getCoordinate().toString());
        } else if (message.getType() == MessageType.MISS) {
            radar.registerMiss(message.getCoordinate());
            print("You missed at " + message.getCoordinate().toString());
        } else if (message.getType() == MessageType.TEXT) {
            print("Opponent says: " + message.asString());
        }
    }

    public void print(String msg) {
        out.setText(msg);
    }


    public void sendAttack(int x, int y) {
        outgoing.add(new Message(MessageType.ATTACK, new Coordinate(x, y)));

    }

    public boolean hasMessage() {
        return outgoing.size() != 0;
    }

    public String getMessage() {
        return outgoing.poll().asString();
    }

    public void draw(GridPane radarPane, GridPane shipPane) {
        radar.draw(radarPane);
        shipBoard.draw(shipPane);
    }

    public void addShip(int rowIndex, int colIndex) {
        shipBoard.addShip(ShipType.BATTLESHIP, Direction.NORTH, colIndex, rowIndex);
    }

    //temp. for demo only
    public void radarHit(int x, int y) {
        radar.registerHit(new Coordinate(x, y));
    }
    public void radarMiss(int x, int y) {
        radar.registerMiss(new Coordinate(x, y));
    }
}
