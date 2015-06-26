package com.android.game;

import java.util.ArrayList;

// A super-class for decision making entities in the game eg. player and ai
public abstract class ControlEntity {
    // ArrayList<Controllable> subjects; <-- IMO this makes more sense
    private ArrayList<SpaceShip> ships;

    public ControlEntity() {
        ships = new ArrayList<SpaceShip>();
    }

    public void addShip(SpaceShip ship) {
        ships.add(ship);
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }
}
