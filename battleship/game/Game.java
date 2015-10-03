package game;

import net.Message;
import game.Radar;
import game.ShipBoard;
import net.MessageType;

/**
 * Created by josephbenton on 10/3/15.
 */
public class Game {
    Radar radar;
    ShipBoard shipBoard;

    public void getMessage(String incoming) {
        Message message = new Message(incoming);
        if (message.getType() == MessageType.ATTACK) {
            shipBoard.registerAttack(message.getCoordinate());
        } else if (message.getType() == MessageType.HIT) {
            radar.registerHit(message.getCoordinate());
        } else if (message.getType() == MessageType.MISS) {
            radar.registerMiss(message.getCoordinate())
        } else if (message.getType() == MessageType.TEXT) {
            //print message somewhere
        }
    }
}
