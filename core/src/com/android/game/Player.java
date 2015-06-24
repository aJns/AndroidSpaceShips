package com.android.game;

import java.util.ArrayList;
import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;

public class Player {
    ArrayList<SpaceShip> ships = new ArrayList<SpaceShip>();

    public Player(TreeMap<String, String> assetMap,
            AssetManager assMan) {
        SpaceShip ship = new SpaceShip(new Vector2(200, 200), assetMap,
                assMan);
        ship.setDestination(new Vector2(400, 200));
        this.ships.add(ship);
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }

    public void input(Vector2 pos) {
        for (SpaceShip s : ships) {
            s.setDestination(pos);
        }
    }
}
