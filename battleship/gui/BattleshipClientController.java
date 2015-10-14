package gui;

import game.Game;
import game.Ship;
import game.core.Coordinate;
import game.core.Direction;
import game.core.GameMode;
import game.core.ShipType;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import net.Network;
/**
 * Created by josephbenton on 10/6/15.
 */
public class BattleshipClientController {


    @FXML
    GridPane radarPane;

    @FXML
    GridPane shipPane;
    Pane[][] handlers;

    @FXML
    Button attack;

    @FXML
    Button connect;

    @FXML
    TextField ipField;

    @FXML
    TextField chatField;

    @FXML
    TextArea out;

    Game game;
    Network net = new Network();
    boolean connected;
    Ship currentShip;
    private long FRAMES_PER_SEC = 60L;
    private long NANO_INTERVAL = 1000000000L / FRAMES_PER_SEC;

    AnimationTimer timer = new AnimationTimer() {
        long then = 0;
        @Override
        public void handle(long now) {
            if (now - then > NANO_INTERVAL) {
                if (connected && net.hasMessage()) {
                    game.receiveMessage(net.getMessage());
                    System.out.println("inHasMessage()");
                }
                if (connected && game.hasMessage()) {
                    net.send(game.getMessage());
                    System.out.println("gameHasMessage");
                }
                game.draw(radarPane, shipPane, handlers);
            }
            then = now;
        }
    };

    @FXML
    public void initialize() {
        game = new Game(out);
        game.setGameMode(GameMode.WAITING);
        int NUM_COLS = 10;
        int NUM_ROWS = 10;
        addRotateHandler();
        handlers = new Pane[10][10];
        for (int i = 0 ; i < NUM_COLS ; i++) {
            for (int j = 0; j < NUM_ROWS; j++) {
                addPaneRadar(i, j);
                addPaneShip(i, j);
            }
        }
        timer.start();
        game.print("Welcome to Battleship\nClick to place ships\nScroll the mouse to rotate before placing");
        game.setGameMode(GameMode.PLACE_SHIP);
        currentShip = new Ship(ShipType.values()[game.numShips()], Direction.EAST, 0, 0);

    }

    private void addRotateHandler() {
        shipPane.setOnScroll(e -> {
            if ( game.getMode() == GameMode.PLACE_SHIP) {
                currentShip.rotate();
            }
        });
    }


    @FXML
    public void connect() {
        if (game.getMode() != GameMode.PLACE_SHIP) {
            net.connect(ipField.getText(), 8000);
            connected = true;
            game.setTurn(true);
            ipField.clear();
            game.print("You can start playing!\nTurns are based on whoever goes first");
        } else{
            game.print("Place all ships before connecting");
        }
    }

    @FXML
    public void chat() {
        game.print("You: " + chatField.getText());
        net.send("T" + chatField.getText());
        chatField.clear();
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
            handleClickShipPane(colIndex, rowIndex);
        });
        pane.setOnMouseEntered(e -> {
            if (game.getMode() == GameMode.PLACE_SHIP) {
                if (currentShip.checkRoot(new Coordinate(colIndex, rowIndex))) {
                    currentShip.setRoot(colIndex, rowIndex);
                    game.setHoverShip(currentShip);
                }
            }
        });

        shipPane.add(pane, colIndex, rowIndex);
        handlers[colIndex][rowIndex] = pane;
    }

    private void handleClickShipPane(int colIndex, int rowIndex) {
        if (game.getMode() == GameMode.PLACE_SHIP) {
            System.out.println(currentShip.getRoot());
            currentShip.setRoot(colIndex, rowIndex);
            System.out.println(currentShip.getRoot());
            game.addShip(currentShip);
            if (game.numShips() == 5) {
                currentShip = null;
                game.setGameMode(GameMode.CONNECT);
                game.print("Enter opponent's IP address and click Connect");
                return;
            }
            currentShip = new Ship(ShipType.values()[game.numShips()], Direction.EAST, colIndex, rowIndex);
        }

    }

}
