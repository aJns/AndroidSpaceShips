package com.android.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Player extends ControlEntity {
    //TODO: Add list of selected ships that's used for issuing commands
    public Player(AssetHandler assHand) {
        SpaceShip ship = new SpaceShip.Builder()
            .setSprite("spaceship3")
            .build(new Vector2(200, 200), assHand);
        addShip(ship);
    }

    public void input(Vector2 pos, Vector2 origin) {
        for (SpaceShip s : getShips()) {
            Vector2 shipOrigin = s.getLastPosition();
            Circle circle = s.getWaypointHitBox();
            if (circle.contains(origin) && pos != null) {
                Command command = new Command(Command.CommandType.MOVE, pos, 
                        shipOrigin);
                s.addCommand(command);
                break;
            }
            if (circle.contains(origin) && pos == null) {
                Command command = new Command(Command.CommandType.PING, pos, 
                        shipOrigin);
                s.addCommand(command);
                break;
            }
            if(!circle.contains(origin)) {
                Command command = new Command(Command.CommandType.ATTACK, pos, 
                        shipOrigin);
                s.addCommand(command);
                break;
            }
        }
    }

    public void updateLastCommand(Vector2 pos) {
        for (SpaceShip s : getShips()) {
            Command command = s.getLastCommand();
            if (!command.setCommandCoords(pos)) {
                s.removeLastCommand();
            }
        }
    }

    public void undo() {
        for (SpaceShip s : getShips()) {
            s.removeLastCommand();
        }
    }
}
