package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class Player extends ControlEntity {
    public Player(AssetHandler assHand) {
        SpaceShip ship = new SpaceShip.Builder()
            .setSprite("spaceship3")
            .build(new Vector2(200, 200), assHand);
        addShip(ship);
    }

    public void input(Vector2 pos) {
        Command command = new Command(Command.CommandType.MOVE, pos);
        for (SpaceShip s : getShips()) {
            s.addCommand(command);
        }
    }
}
