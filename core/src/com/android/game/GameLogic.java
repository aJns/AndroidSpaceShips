package com.android.game;

import java.util.ArrayList;

public class GameLogic {
    ArrayList<GameObject> gameObjects;
    Player player;
    ArtificialIntelligence ai;
    GameState gameState;

    public GameLogic(GameState state,
            Player player, ArtificialIntelligence ai) {
        this.gameObjects = new ArrayList<GameObject>();
        this.gameState = state;
        this.player = player;
        this.ai = ai;
    }

    public void init() {
        for (SpaceShip s : player.getShips()) {
            gameObjects.add(s);
        }
        for (SpaceShip s : ai.getShips()) {
            gameObjects.add(s);
        }
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
