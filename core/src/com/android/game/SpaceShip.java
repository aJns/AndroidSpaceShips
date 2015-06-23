package com.android.game;

import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable {
	String imgPath = "spaceship";
	Texture img;
    float speed = 1;
	Vector2 position;
    Vector2 velocity = Vector2.Zero;
    Vector2 destination;

    public SpaceShip(Vector2 position, TreeMap<String, String> assetMap, 
	    AssetManager assMan) {
        this.position = position;
        this.destination = this.position;

	// Use assetMap sensibly. I was lazy and just wanted to test it.
	imgPath = assetMap.get(imgPath);
	assMan.load(imgPath, Texture.class);
    }

	@Override
	public void draw(SpriteBatch batch, AssetManager assMan) {
		if(assMan.update()) {
			img = assMan.get(imgPath);
			batch.draw(img, position.x, position.y, 100, 100);
		}
	}

	@Override
	public void update() {
        if(!position.epsilonEquals(destination, 0.5f)) {
            Vector2 dir = destination.cpy().sub(position).nor();
            velocity.set(dir.scl(speed));
        } else {
            velocity.setZero();
        }

        position.add(velocity);
	}

    public void setDestination(Vector2 destination) {
        // Input pls
        this.destination = destination;
    }
}
