package gui;

import game.Radar;
import game.ShipBoard;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

/**
 * Created by josephbenton on 10/6/15.
 */
public class BattleshipClientController {
    @FXML
    GridPane radarPane;

    @FXML
    GridPane shipPane;

    @FXML
    public void initialize() {
        Radar radar = new Radar();
        ShipBoard shipBoard = new ShipBoard();
    }
}
