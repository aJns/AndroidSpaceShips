package com.android.game;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player {
	ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>();

	public Player() {
        SpaceShip ship = new SpaceShip(new Vector2(200, 200));
        ship.setDestination(new Vector2(400, 200));
        this.ships.add(ship);
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }

    public void input(Vector2 pos) {
        for(SpaceShip s : ships) {
            s.setDestination(pos);
        }
    }
}
