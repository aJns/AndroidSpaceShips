package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class GameLogic {
    ArrayList<GameObject> gameObjects;
    ArrayList<Projectile> bulletList;
    Player player;
    ArtificialIntelligence ai;
    GameState gameState;
    AssetHandler assHand;

    public GameLogic(GameState state,
            Player player, ArtificialIntelligence ai, AssetHandler assHand) {
        this.gameObjects = new ArrayList<GameObject>();
        this.bulletList = new ArrayList<Projectile>();
        this.gameState = state;
        this.player = player;
        this.ai = ai;
        this.assHand = assHand;
    }

    public void init() {
        player.init(gameObjects);
        ai.init(gameObjects);

        
        SpaceShip ship = new SpaceShip.Builder()
            .setSprite("spaceship3")
            .build(new Vector2(200, 200), player, assHand, gameObjects);
        player.addShip(ship);
        gameObjects.add(ship);

        ship = new SpaceShip.Builder()
            .build(new Vector2(400, 200), ai, assHand, gameObjects);
        ai.addShip(ship);
        gameObjects.add(ship);

        gameState.plan();
    }

    public void update() {
        for (GameObject go : gameObjects) {
            for (Wave w : go.getWaves()) {
                w.update(gameState);
            }
            go.checkWaves(gameObjects);
        }

        player.update(gameState);
        ai.update(gameState);
    }
}
