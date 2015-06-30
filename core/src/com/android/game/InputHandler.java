package com.android.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {
    Player player;
    OrthographicCamera camera;
    GameState state;
    Vector2 inputOrigin;
    boolean dragged = false;
    int dragTimer = 0;
    final int DRAG_LIMIT = 10;

    public InputHandler(Player player, OrthographicCamera camera, GameState state) {
        this.player = player;
        this.camera = camera;
        this.state = state;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            state.toggleState();
        }
        if (keycode == Input.Keys.BACKSPACE && state.isPlanning()) {
            player.undo();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (state.isPlanning()) {
            inputOrigin = new Vector2(screenX, screenY);
            Vector2 inputPosition = new Vector2(screenX, screenY);
            player.input(inputPosition, inputOrigin);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (state.isPlanning()) {
            Vector2 inputPosition = new Vector2(screenX, screenY);
            // The player needs to drag for a set amount of time to trigger
            // a move command. Below this time the command is parsed as a
            // ping command.
            if (!dragged) { inputPosition = null; }
            player.updateLastCommand(inputPosition);
        }
        inputOrigin = null;
        dragged = false;
        dragTimer = 0;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (state.isPlanning()) {
            dragTimer++;
            if (dragTimer > DRAG_LIMIT) { dragged = true; }
            Vector2 inputPosition = new Vector2(screenX, screenY);
            player.updateLastCommand(inputPosition);
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
