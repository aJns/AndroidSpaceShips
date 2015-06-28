package com.android.game;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;

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
                if (!w.getReflective() || !w.enteredWave(position)) {
                    continue;
                }

                // FIXME sometimes spawns double waves
                // TODO clean this shit up

                // Left part
                Vector2 pos = w.getPosition();
                float ang = Utils.angleDifference(position.cpy().sub(w.getPosition()).angle(),
                        w.getDirection() - w.getAngle() / 2f)
                        - (float) Math.toDegrees(Math.asin(100f / (2f * w.getRadius())));
                float dir = position.cpy().sub(w.getPosition()).angle()
                        - (float) Math.toDegrees(Math.asin(100f / (2f * w.getRadius())))
                        - ang / 2f;
                float rad = w.getRadius();
                float ene = ang / w.getAngle() * w.getEnergy();
                boolean ref = true;

                go.addWave(pos, dir, ang, rad, ene, ref);

                // Right part
                pos = w.getPosition();
                ang = Utils.angleDifference(position.cpy().sub(w.getPosition()).angle(),
                        w.getDirection() + w.getAngle() / 2f)
                        - (float) Math.toDegrees(Math.asin(100f / (2f * w.getRadius())));
                dir = position.cpy().sub(w.getPosition()).angle()
                        + (float) Math.toDegrees(Math.asin(100f / (2f * w.getRadius())))
                        + ang / 2f;
                rad = w.getRadius();
                ene = ang / w.getAngle() * w.getEnergy();
                ref = true;

                go.addWave(pos, dir, ang, rad, ene, ref);

                reflectWave(w);
                // Destroys old wave
                w.setEnergy(0f);
            }
        }
    }

    void reflectWave(Wave w) {
        Vector2 pos = position;
        float dir = w.getPosition().cpy().sub(position).angle();
        float ang = 2f * (float) Math.toDegrees(
                Math.asin(100f / (2f * w.getRadius())));
        float rad = w.getRadius() - position.dst(w.getPosition());
        float ene = ang / w.getAngle() * w.getEnergy();
        boolean ref = false;

        addWave(pos, dir, ang, rad, ene, ref);
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
