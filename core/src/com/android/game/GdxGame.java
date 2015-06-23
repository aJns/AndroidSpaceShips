package com.android.game;

import java.util.ArrayList;
import java.util.TreeMap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;

public class GdxGame implements ApplicationListener {
	SpriteBatch batch;
	Texture img;
	GameLogic logic;
	AssetManager assMan;
	ArrayList<Drawable> visibleObjects;
	GameState gameState;
	ArtificialIntelligence ai;
	Player player;
	InputHandler input;
	TreeMap<String, String> assetMap;
	
	@Override
	public void create () {
		//TODO Load all assets to be used
		assMan = new AssetManager();
		batch = new SpriteBatch();
		visibleObjects = new ArrayList<Drawable>();
		gameState = new GameState();
		ai = new ArtificialIntelligence();
		input = new InputHandler(player);

		// Save all asset paths in a map 
		assetMap = new TreeMap<String, String>();
		assetMap.put("spaceship", "img/spaceship.png");

		// assMan.load moved to SpaceShip constructor
		
		//starting up the gamelogick
		player = new Player(assetMap, assMan);
		logic = new GameLogic(visibleObjects, gameState, player, ai);
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
