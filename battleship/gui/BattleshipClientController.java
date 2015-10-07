package gui;

import game.Game;
import game.Radar;
import game.ShipBoard;
import game.core.Coordinate;
import game.core.Direction;
import game.core.ShipType;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import net.Network;

/**
 * Created by josephbenton on 10/6/15.
 */
public class BattleshipClientController {


    @FXML
    GridPane radarPane;

    @FXML
    GridPane shipPane;

    @FXML
    Button attack;

    @FXML
    Button connect;

    @FXML
    TextArea ipField;

    @FXML
    Text out;

    Game game;
    Network net = new Network();
    boolean connected;
    private long FRAMES_PER_SEC = 60L;
    private long NANO_INTERVAL = 1000000000L / FRAMES_PER_SEC;

    AnimationTimer timer = new AnimationTimer() {
        long then = 0;
        @Override
        public void handle(long now) {
            if (now - then > NANO_INTERVAL) {
                if (connected && net.hasMessage()) {
                    game.recieveMessage(net.getMessage());
                }
                if (connected && game.hasMessage()) {
                    net.send(game.getMessage());
                }
                game.draw(radarPane, shipPane);
            }
            then = now;
        }
    };

    @FXML
    public void initialize() {
        game = new Game(out);

        int numCols = 10;
        int numRows = 10;
        for (int i = 0 ; i < numCols ; i++) {
            for (int j = 0; j < numRows; j++) {
                addPaneRadar(i, j);
                addPaneShip(i, j);
            }
        }
        timer.start();
    }

    @FXML
    public void connect() {
        net.connect(ipField.getText(), 8000);
        connected = true;
        ipField.clear();
    }

    private void attack(int x, int y) {
        game.sendAttack(x, y);
    }

    private void addPaneRadar(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            System.out.printf("Mouse clicked radar [%d, %d]%n", colIndex, rowIndex);
            attack(colIndex, rowIndex);
        });
        radarPane.add(pane, colIndex, rowIndex);
    }

    private void addPaneShip(int colIndex, int rowIndex) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(e -> {
            System.out.printf("Mouse clicked ship board [%d, %d]%n", colIndex, rowIndex);
            game.addShip(rowIndex, colIndex);
        });
        shipPane.add(pane, colIndex, rowIndex);
    }

}
