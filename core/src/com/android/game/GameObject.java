package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
    protected Vector2 position;
    protected ArrayList<Wave> waves;
    protected float diameter = 100f;

    public ControlEntity getCtrlEntity() {
        return ctrlEntity;
    }

    protected ControlEntity ctrlEntity;

    public GameObject() {
        waves = new ArrayList<Wave>();
        this.ctrlEntity = null;
    }

    public GameObject(ControlEntity ctrlEntity) {
        waves = new ArrayList<Wave>();
        this.ctrlEntity = ctrlEntity;
    }

    public void checkWaves(ArrayList<GameObject> gameObjects) {
        for (GameObject go : gameObjects) {
            if (go.equals(this)) continue;

            int length = go.getWaves().size();
            for (int i = 0; i < length; i++) {
                Wave w = go.getWaves().get(i);
                if (!w.enteredWave(position)) continue;
                // FIXME sometimes spawns double waves (rare)

                if (ctrlEntity != null) {
                    ctrlEntity.addSighting(go);
                }

                if (w.getAngle() == 360f) {
                    Vector2 pos = w.getPosition();
                    float dir = w.getPosition().cpy().sub(position).angle();
                    float ang = 360f - 2f * (float) Math.toDegrees(
                            Math.asin(getDiameter() / (2f * w.getRadius())));
                    float rad = w.getRadius();
                    float ene = ang / w.getAngle() * w.getEnergy();

                    addWave(pos, dir, ang, rad, ene, true);
                } else {
                    // Left side
                    splitWave(go, w, -1);
                    // Right side
                    splitWave(go, w, 1);
                }

                if (w.getReflective()) {
                    reflectWave(w);
                }
                w.destroy();
            }
        }
    }

    void reflectWave(Wave w) {
        Vector2 pos = position.cpy().add(position.cpy().sub(w.getPosition()));
        float dir = w.getPosition().cpy().sub(position).angle();
        float ang = 2f * (float) Math.toDegrees(
                Math.asin(getDiameter() / (2f * w.getRadius())));
        float rad = w.getRadius();
        float ene = ang / w.getAngle() * w.getEnergy();

        addWave(pos, dir, ang, rad, ene, true);
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

        go.addWave(pos, dir, ang, rad, ene, true);
    }

    public void addWave(Vector2 position, float radius, float energy,
                        boolean reflective) {
        waves.add(new Wave(position, radius, energy, reflective, this));
    }

    public void addWave(Vector2 position, float direction, float angle,
                        float radius, float energy, boolean reflective) {
        waves.add(new Wave(position, direction, angle, radius, energy,
                reflective, this));
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
