package com.android.game;

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
    GameState gameState;
    ArtificialIntelligence ai;
    Player player;
    InputHandler input;
    OrthographicCamera camera;
    AssetHandler assHand;
    UserInterface userInterface;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        gameState = new GameState();
        assHand = new AssetHandler();

        player = new Player();
        ai = new ArtificialIntelligence();
        logic = new GameLogic(gameState, player, ai, assHand);
        logic.init();


        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(true);

        userInterface = new UserInterface(gameState, assHand, player, w, h);

        input = new InputHandler(player, camera, gameState, userInterface);
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render() {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(25 / 255f, 25 / 255f, 112 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        player.draw(batch, assHand);
        ai.draw(batch, assHand);

        // Drawing userInterface here so it's on top
        userInterface.draw(batch, assHand);

        batch.end();

        renderer.setProjectionMatrix(camera.combined);
        player.draw(renderer);
        ai.draw(renderer);

        logic.update();
    }

    @Override
    public void dispose() {
        // Called when game is closed
    }

    @Override
    public void pause() {
        // Called when game is paused
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(true);
        camera.viewportWidth = width;
        camera.viewportHeight = height;

        userInterface.resize(width, height);
    }

    @Override
    public void resume() {
        // Called when game is resumed from pause
    }
}
