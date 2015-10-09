package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import net.Message;
import net.MessageType;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Game {
    Radar radar;
    ShipBoard shipBoard;
    ArrayBlockingQueue<Message> outgoing;
    TextArea out;
    boolean isTurn;

    public Game(TextArea out) {
        this.radar = new Radar();
        this.shipBoard = new ShipBoard();
        this.outgoing = new ArrayBlockingQueue<>(20);
        this.out = out;
    }

    public void receiveMessage(String incoming) {
        Message message = new Message(incoming);
        if (message.getType() == MessageType.ATTACK) {
            outgoing.add(shipBoard.checkAttack(message.getCoordinate()));
            print("Attack at " + message.getCoordinate().toString());
            isTurn = true;
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
        out.appendText(msg + "\n");
    }


    public void sendAttack(int x, int y) {
        if (isTurn()) {
            outgoing.add(new Message(MessageType.ATTACK, new Coordinate(x, y)));
            isTurn = false;
        }

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

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean b) {
        isTurn = true;
    }
}
