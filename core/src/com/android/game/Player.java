package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class Player extends ControlEntity {
    //TODO: Add list of selected ships that's used for issuing commands
    public Player(AssetHandler assHand) {
        SpaceShip ship = new SpaceShip.Builder()
            .setSprite("spaceship3")
            .build(new Vector2(200, 200), assHand);
        addShip(ship);
    }

    public void input(Vector2 pos) {
        for (SpaceShip s : getShips()) {
            Vector2 origin = s.getLastPosition();
        Command command = new Command(Command.CommandType.MOVE, pos, origin);
            s.addCommand(command);
        }
    }
    public void undo() {
        for (SpaceShip s : getShips()) {
            s.removeLastCommand();
        }
    }
}
