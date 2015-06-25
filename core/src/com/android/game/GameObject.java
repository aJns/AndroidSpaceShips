package com.android.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameObject {
    protected Vector2 position;
    protected ArrayList<Wave> waves;

    public GameObject() {
        waves = new ArrayList<Wave>();
    }

    public void checkWaves(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            if (!go.equals(this)) {
                for (Wave w : go.getWaves()) {
                    if (w.getReflective() && MathUtils.isEqual(w.getRadius(), position.dst(w.getPosition()) + 5, 5)) {
                        addWave(position, w.getRadius() - position.dst(w.getPosition()), w.getMaxRadius(), false);
                    }
                }
            }
        }
    }

    public void addWave(Vector2 position, float radius, float maxRadius, boolean reflective) {
        waves.add(new Wave(position, radius, maxRadius, reflective));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public Vector2 getPosition() {
        return position;
    }
}
