package com.android.game;

import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable {
	String imgPath = "spaceship";
	Texture img;
    float speed = 1;
	Vector2 position;
    float rotation = 0;
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
            float scale = 0.5f;
            // Spaceship in image should be facing right
			batch.draw(img, position.x, position.y, img.getWidth() / 2, img.getHeight() / 2,
                    img.getWidth(), img.getHeight(), scale, scale, rotation, 0, 0,
                    img.getWidth(), img.getHeight(), false, false);
		}
	}

	@Override
	public void update() {
        if(!position.epsilonEquals(destination, 0.5f)) {
            Vector2 dir = destination.cpy().sub(position).nor();
            rotation = dir.angle();
            velocity.set(dir.scl(speed));

        } else {
            Random rand = new Random();
            Vector2 randVec = new Vector2(rand.nextInt(400), rand.nextInt(200));
            setDestination(randVec);
        }

        position.add(velocity);
	}

    public void setDestination(Vector2 destination) {
        // Input pls
        this.destination = destination;
    }

    public void setSpeed(float speed) { this.speed = speed; }
}
