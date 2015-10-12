package game;

import game.core.Coordinate;
import game.core.Direction;
import game.core.GameMode;
import game.core.ShipType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    GameMode mode;

    public Game(TextArea out) {
        this.radar = new Radar();
        this.shipBoard = new ShipBoard();
        this.outgoing = new ArrayBlockingQueue<>(20);
        this.out = out;
    }

    public Game() {
        this.radar = new Radar();
        this.shipBoard = new ShipBoard();
        this.outgoing = new ArrayBlockingQueue<>(20);
    }


    public void receiveMessage(String incoming) {
        Message message = new Message(incoming);
        if (message.getType() == MessageType.ATTACK) {
            outgoing.add(shipBoard.checkAttack(message.getCoordinate()));
            print("Attack at " + message.getCoordinate().toString());
            mode = GameMode.YOUR_TURN;
        } else if (message.getType() == MessageType.HIT) {
            radar.registerHit(message.getCoordinate());
            print("You hit at " + message.getCoordinate().toString());
        } else if (message.getType() == MessageType.MISS) {
            radar.registerMiss(message.getCoordinate());
            print("You missed at " + message.getCoordinate().toString());
        } else if (message.getType() == MessageType.TEXT) {
            print("Enemy: " + message.getMessage());
        }
    }

    public void print(String msg) {
        out.appendText(msg + "\n");
    }


    public void sendAttack(int x, int y) {
        if (mode == GameMode.YOUR_TURN) {
            outgoing.add(new Message(MessageType.ATTACK, new Coordinate(x, y)));
            mode = GameMode.ENEMY_TURN;
        }

    }

    public boolean hasMessage() {
        return outgoing.size() != 0;
    }

    public String getMessage() {
        return outgoing.poll().asString();
    }

    public void draw(GridPane radarPane, GridPane shipPane, Pane[][] handlers) {
        radar.draw(radarPane);
        shipBoard.draw(shipPane, handlers);
    }

    public void addShip(ShipType type, Direction dir, int rowIndex, int colIndex) {
        shipBoard.addShip(type
                , dir, colIndex, rowIndex);
    }

    public void addShip(Ship s) {
        shipBoard.addShip(s);
    }

    public void setTurn(boolean b) {
        mode = GameMode.YOUR_TURN;
    }


    public void setGameMode(GameMode gameMode) {
        this.mode = gameMode;
    }

    public GameMode getMode() {
        return mode;
    }

    public int numShips() {
        return shipBoard.numShips();
    }
}
