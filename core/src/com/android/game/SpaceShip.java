package com.android.game;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable {
    String imgPath = "spaceship";
    Sprite sprite;
    Texture img;
    AssetManager assMan;

    float speed;
    float rotation;
    Vector2 destination;

    public SpaceShip(Vector2 position, TreeMap<String, String> assetMap,
                     AssetManager assMan) {
        super();
        super.position = position;
        this.destination = super.position;
        speed = 2;
        rotation = 0;

        imgPath = assetMap.get(imgPath);
        assMan.load(imgPath, Texture.class);
        while (!assMan.update()) ;
        img = assMan.get(imgPath);
        sprite = new Sprite(img);
        this.assMan = assMan;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
        addWave(super.position, 0, 500, true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        float scale = 0.25f;
        sprite.setRotation(rotation);
        sprite.setScale(scale);
		float drawY = super.position.y - (sprite.getHeight() / 2);
		float drawX = super.position.x - (sprite.getWidth() / 2);
        sprite.setPosition(drawX, drawY);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        for (Wave w : super.waves) {
            w.draw(renderer);
        }
    }

    @Override
    public void update() {
        if (super.position.epsilonEquals(destination, 0.5f)) {
            // randomizeDestination(320, 240);
        } else {
            Vector2 v = destination.cpy().sub(super.position).nor();
            rotation = v.angle();
            v.scl(Math.min(speed, super.position.dst(destination)));
            super.position.add(v);
        }

        for (Wave w : super.waves) {
            w.update();
        }
        for (Iterator<Wave> iterator = super.waves.iterator(); iterator.hasNext(); ) {
            Wave wave = iterator.next();
            if (wave.hasMaxRadius()) {
                iterator.remove();
            }
        }
    }

    void randomizeDestination(int xMax, int yMax) {
        Random rand = new Random();
        Vector2 randVec = new Vector2(rand.nextInt(2 * xMax) - xMax,
                rand.nextInt(2 * yMax) - yMax);
        setDestination(randVec);
    }
}
