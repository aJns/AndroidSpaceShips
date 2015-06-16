package com.android.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		//TODO Load all assets to be used
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//TODO Render all visible objects
		batch.draw(img, 0, 0);
		batch.end();

		//TODO Update game logic
	}

	@Override
	public void dispose() {
		// Called when game is closed
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// Called when game is paused
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// Called when window is resized
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// Called when game is resumed from pause
		// TODO Auto-generated method stub

	}
}
