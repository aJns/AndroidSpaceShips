package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GdxGame implements ApplicationListener {
    SpriteBatch batch;
    ShapeRenderer renderer;
    Texture img;
    GameLogic logic;
    ArrayList<Drawable> visibleObjects;
    GameState gameState;
    ArtificialIntelligence ai;
    Player player;
    InputHandler input;
    OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        visibleObjects = new ArrayList<Drawable>();
        gameState = new GameState();

        //starting up the gamelogick
        AssetHandler assHand = new AssetHandler();
        player = new Player(assHand);
        ai = new ArtificialIntelligence(assHand);
        logic = new GameLogic(visibleObjects, gameState, player, ai);
        logic.init();


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(true);

        input = new InputHandler(player, camera, gameState);
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(25 / 255f, 25 / 255f, 112 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //TODO Render all visible objects
        for (Drawable object : visibleObjects) {
            object.draw(batch);
        }
        batch.end();

        renderer.setProjectionMatrix(camera.combined);
        for (Drawable object : visibleObjects) {
            object.draw(renderer);
        }

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
        camera.setToOrtho(true);
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void resume() {
        // Called when game is resumed from pause
        // TODO Auto-generated method stub

    }
}
