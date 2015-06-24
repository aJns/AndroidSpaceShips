package com.android.game;

import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable {
    String imgPath = "spaceship";
    Sprite sprite;
    Texture img;
    float speed = 1;
    Vector2 position;
    float rotation = 0;
    Vector2 velocity = Vector2.Zero;
    Vector2 destination;
    AssetManager assMan;

    public SpaceShip(Vector2 position, TreeMap<String, String> assetMap,
            AssetManager assMan) {
        this.position = position;
        this.destination = this.position;

        imgPath = assetMap.get(imgPath);
        assMan.load(imgPath, Texture.class);
        while (!assMan.update());
        img = assMan.get(imgPath);
        sprite = new Sprite(img);
        sprite.setCenter(sprite.getWidth()/2, sprite.getHeight()/2);
        sprite.setOriginCenter();
        this.assMan = assMan;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDestination(Vector2 destination) {
        this.destination = destination;
    }

    @Override
    public void draw(SpriteBatch batch) {
        float scale = 0.5f;
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        sprite.draw(batch);
    }

    @Override
    public void update() {
        if (!position.epsilonEquals(destination, 0.5f)) {
            Vector2 dir = destination.cpy().sub(position).nor();
            rotation = dir.angle();
            velocity.set(dir.scl(speed));

        } else {
            Random rand = new Random();
            Vector2 randVec
                = new Vector2(rand.nextInt(400), rand.nextInt(200));
            setDestination(randVec);
        }

        position.add(velocity);
    }
}
