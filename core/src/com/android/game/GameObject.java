package com.android.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameObject {
    protected Vector2 position;
    protected ArrayList<Wave> waves;
    protected float diameter = 100f;

    public GameObject() {
        waves = new ArrayList<Wave>();
    }

    public void checkWaves(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            if (!go.equals(this)) {
                for (Wave w : go.getWaves()) {
                    // FIXME sometimes spawns double waves
                    if (w.getReflective() && w.enteredWave(position)) {
                        float diameter = go.getDiameter();
                        addWave(position,
                                go.getPosition().cpy().sub(position).angle(),
                                (float) Math.toDegrees(diameter / w.getRadius()),
                                w.getRadius() - position.dst(w.getPosition()),
                                w.energyDensity() * diameter, false);
                    }
                }
            }
        }
    }

    public void addWave(Vector2 position, float radius, float maxRadius,
                        boolean reflective) {
        waves.add(new Wave(position, radius, maxRadius, reflective));
    }

    public void addWave(Vector2 position, float direction, float angle,
                        float radius, float maxRadius, boolean reflective) {
        waves.add(new Wave(position, direction, angle, radius, maxRadius,
                reflective));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDiameter() {
        // TODO get actual diameter of object
        return diameter;
    }
}
