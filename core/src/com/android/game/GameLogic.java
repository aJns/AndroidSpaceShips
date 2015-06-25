package com.android.game;

import java.util.ArrayList;

public class GameLogic {
    //TODO member variables
    ArrayList<GameObject> gameObjects;
    ArrayList<Drawable> visibleObjects;
    ArrayList<Updateable> updateableObjects;
    Player player;
    ArtificialIntelligence ai;
    GameState gameState;

    public GameLogic(ArrayList<Drawable> visibleObjects, GameState state,
            Player player, ArtificialIntelligence ai) {
        this.gameObjects = new ArrayList<GameObject>();
        this.visibleObjects = visibleObjects;
        this.updateableObjects = new ArrayList<Updateable>();
        this.gameState = state;
        this.player = player;
        this.ai = ai;
    }

    public void init() {
        for (SpaceShip s : player.getShips()) {
            gameObjects.add(s);
            visibleObjects.add(s);
            updateableObjects.add(s);
        }
        for (SpaceShip s : ai.getShips()) {
            gameObjects.add(s);
            visibleObjects.add(s);
            updateableObjects.add(s);
        }
        gameState.plan();
    }

    public void update() {
        for (GameObject go : gameObjects) {
            for (Wave w : go.getWaves()) {
                w.update();
            }
            go.checkWaves(gameObjects);
        }
        for (Updateable u : updateableObjects) {
            u.update();
        }
    }
}
