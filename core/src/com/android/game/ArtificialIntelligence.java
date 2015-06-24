package com.android.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.TreeMap;

public class ArtificialIntelligence {
	ArrayList<SpaceShip> ships;

    public ArtificialIntelligence(TreeMap<String, String> assetMap,
                  AssetManager assMan) {
        ships = new ArrayList<SpaceShip>();
        SpaceShip ship = new SpaceShip(new Vector2(400, 200), assetMap,
                assMan);
        this.ships.add(ship);
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }
}
