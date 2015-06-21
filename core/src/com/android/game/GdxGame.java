package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGame implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	GameLogic logic;
	AssetManager assMan;
	ArrayList<Drawable> visibleObjects;
	GameState gameState;
	
	@Override
	public void create () {
		//TODO Load all assets to be used
		assMan = new AssetManager();
		batch = new SpriteBatch();
		visibleObjects = new ArrayList<Drawable>();
		gameState = new GameState();

		assMan.load("img/spaceship.jpg", Texture.class);
		//starting up the gamelogick
		logic = new GameLogic(visibleObjects);
		logic.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//TODO Render all visible objects
		for(Drawable object : visibleObjects) {
			object.draw(batch, assMan);
		}
		batch.end();

		logic.update();
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
