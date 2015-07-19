package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

// A super-class for decision making entities in the game eg. player and ai
// Draws and updates its subjects
public abstract class ControlEntity implements Drawable, Updateable {
    // ArrayList<Controllable> subjects; <-- IMO this makes more sense
    private ArrayList<SpaceShip> ships;
    private ArrayList<GameObject> sightings;
    protected ArrayList<GameObject> globalGameObjects;

    public ControlEntity() {
        ships = new ArrayList<SpaceShip>();
        sightings = new ArrayList<GameObject>();
    }

    public void addSighting(GameObject go) {
        if (sightings.contains(go)) {
            sightings.remove(go);
        }
        sightings.add(go);
    }

    public ArrayList<GameObject> getSightings() {
        return sightings;
    }

    public void addShip(SpaceShip ship) {
        ships.add(ship);
    }

    public ArrayList<SpaceShip> getShips() {
        return ships;
    }

    public void init(ArrayList<GameObject> globalGameObjects) {
        this.globalGameObjects = globalGameObjects;
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        //TODO: draw sightings
        for (SpaceShip s : ships) {
            s.draw(batch, assHand);
        }
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(GameState state) {
        //TODO: update sightings
        for (SpaceShip s : ships) {
            s.update(state);
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
