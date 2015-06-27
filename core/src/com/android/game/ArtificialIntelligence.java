package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class ArtificialIntelligence extends ControlEntity {
    public ArtificialIntelligence(AssetHandler assHand) {
        SpaceShip ship = new SpaceShip.Builder()
            .build(new Vector2(400, 200), assHand);
        addShip(ship);
    }
}
