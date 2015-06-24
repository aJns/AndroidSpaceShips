package com.android.game;

import java.util.ArrayList;
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

    float speed = 2;
    float rotation = 0;
    Vector2 position;
    Vector2 destination;

    ArrayList<Wave> waves = new ArrayList<Wave>();

    public SpaceShip(Vector2 position, TreeMap<String, String> assetMap,
                     AssetManager assMan) {
        this.position = position;
        this.destination = this.position;

        imgPath = assetMap.get(imgPath);
        assMan.load(imgPath, Texture.class);
        while (!assMan.update()) ;
        img = assMan.get(imgPath);
        sprite = new Sprite(img);
        sprite.setCenter(sprite.getWidth() / 2, sprite.getHeight() / 2);
        sprite.setOriginCenter();
        this.assMan = assMan;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
        waves.add(new Wave(position, 500));
    }

    @Override
    public void draw(SpriteBatch batch) {
        float scale = 0.25f;
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        for (Wave w : waves) {
            w.draw(renderer);
        }
    }

    @Override
    public void update() {
        if (position.epsilonEquals(destination, 0.5f)) {
            randomizeDestination(320, 240);
        } else {
            Vector2 v = destination.cpy().sub(position).nor();
            rotation = v.angle();
            v.scl(Math.min(speed, position.dst(destination)));
            position.add(v);
        }

        for (Wave w : waves) {
            w.update();
        }
    }

    void randomizeDestination(int xMax, int yMax) {
        Random rand = new Random();
        Vector2 randVec = new Vector2(rand.nextInt(2 * xMax) - xMax,
                rand.nextInt(2 * yMax) - yMax);
        setDestination(randVec);
    }
}
