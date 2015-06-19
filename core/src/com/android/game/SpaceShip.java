package com.android.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable {
	String imgPath = "img/spaceship.jpg";
	Texture img;
    float speed = 1;
	Vector2 position = new Vector2(200, 200);
    Vector2 velocity = Vector2.Zero;
    Vector2 destination = new Vector2(400, 150);

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
