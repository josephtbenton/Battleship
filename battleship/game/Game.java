package game;

import net.Message;
import net.MessageType;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Game {
    Radar radar;
    ShipBoard shipBoard;

    public Game(Radar radar, ShipBoard shipBoard) {
        this.radar = radar;
        this.shipBoard = shipBoard;
    }

    public void recieveMessage(String incoming) {
        Message message = new Message(incoming);
        if (message.getType() == MessageType.ATTACK) {
            shipBoard.checkAttack(message.getCoordinate());
        } else if (message.getType() == MessageType.HIT) {
            radar.registerHit(message.getCoordinate());
        } else if (message.getType() == MessageType.MISS) {
            radar.registerMiss(message.getCoordinate());
        } else if (message.getType() == MessageType.TEXT) {
            //print message somewhere
        }
    }
}
