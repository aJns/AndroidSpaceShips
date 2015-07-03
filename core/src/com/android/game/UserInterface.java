package com.android.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class UserInterface implements Drawable {
    GameState state;
    Sprite planning_sprite;
    Sprite action_sprite;

    public UserInterface(GameState state, AssetHandler assHand) {
        this.state = state;
        this.planning_sprite = assHand.getSprite("planning");
        this.action_sprite = assHand.getSprite("action");
    }

    // Returns true if input is processed
    public boolean handleInput(Vector2 pos) {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(ShapeRenderer renderer) {
    }
}
