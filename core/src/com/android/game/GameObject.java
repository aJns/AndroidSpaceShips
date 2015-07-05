package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.ai.steer.limiters.AngularLimiter;
import com.badlogic.gdx.math.Vector2;
import com.sun.javafx.geom.transform.BaseTransform;

public class GameObject {
    protected Vector2 position;
    protected ArrayList<Wave> waves;
    protected float diameter = 100f;

    public GameObject() {
        waves = new ArrayList<Wave>();
    }

    public void checkWaves(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            if (go.equals(this)) continue;

            int length = go.getWaves().size();
            for (int i = 0; i < length; i++) {
                Wave w = go.getWaves().get(i);
                if (!w.enteredWave(position)) continue;
                // FIXME sometimes spawns double waves (rare)

                if (w.getAngle() == 360f) {
                    Vector2 pos = w.getPosition();
                    float dir = w.getPosition().cpy().sub(position).angle();
                    float ang = 360f - 2f * (float) Math.toDegrees(
                            Math.asin(getDiameter() / (2f * w.getRadius())));
                    float rad = w.getRadius();
                    float ene = ang / w.getAngle() * w.getEnergy();
                    boolean ref = true;

                    addWave(pos, dir, ang, rad, ene, ref);
                } else {
                    // Left side
                    splitWave(go, w, -1);
                    // Right side
                    splitWave(go, w, 1);
                }

                if (w.getReflective()) {
                    reflectWave(w);
                }
                // Destroys old wave
                w.setEnergy(0f);
            }
        }
    }

    void reflectWave(Wave w) {
        Vector2 pos = position;
        float dir = w.getPosition().cpy().sub(position).angle();
        float ang = 2f * (float) Math.toDegrees(
                Math.asin(getDiameter() / (2f * w.getRadius())));
        float rad = w.getRadius() - position.dst(w.getPosition());
        float ene = ang / w.getAngle() * w.getEnergy();
        boolean ref = false;

        addWave(pos, dir, ang, rad, ene, ref);
    }

    void splitWave(GameObject go, Wave w, int side) {
        // Still messy
        Vector2 pos = w.getPosition();
        float a1 = position.cpy().sub(w.getPosition()).angle();
        float a2 = Utils.angleToRange(
                w.getDirection() + side * w.getAngle() / 2f);
        float ang = Utils.angleDifference(a1, a2, side)
                - (float) Math.toDegrees(Math.asin(getDiameter() / (2f * w.getRadius())));
        float dir = position.cpy().sub(w.getPosition()).angle()
                + side * (float) Math.toDegrees(Math.asin(getDiameter() / (2f * w.getRadius())))
                + side * ang / 2f;
        float rad = w.getRadius();
        float ene = ang / w.getAngle() * w.getEnergy();
        boolean ref = true;

        go.addWave(pos, dir, ang, rad, ene, ref);
    }

    public void addWave(Vector2 position, float radius, float energy,
                        boolean reflective) {
        waves.add(new Wave(position, radius, energy, reflective));
    }

    public void addWave(Vector2 position, float direction, float angle,
                        float radius, float energy, boolean reflective) {
        waves.add(new Wave(position, direction, angle, radius, energy,
                reflective));
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getDiameter() {
        return diameter;
    }
}
