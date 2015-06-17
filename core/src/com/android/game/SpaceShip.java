package com.android.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceShip extends GameObject implements Drawable {
	String imgPath = "img/spaceship.jpg";
	Texture img;
	int xPos = 200;
	int yPos = 200;

	@Override
	public void draw(SpriteBatch batch, AssetManager assMan) {
		if(assMan.update()) {
			img = assMan.get(imgPath);
			batch.draw(img, xPos, yPos);
		}
	}
}
