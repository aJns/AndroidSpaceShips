package com.android.game;

import com.android.game.Command.CommandType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class UserInterface implements Drawable {
    GameState state;
    String planID = "planning";
    String actionID = "action";
    Sprite sprite;
    final float XPOS = 0;
    final float YPOS = 0;

    public UserInterface(GameState state) {
        this.state = state;
    }

    // Returns true if input is processed
    public boolean handleInput(Vector2 pos) {
        return false;
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        if (state.isPlanning()) {
            sprite = assHand.getSprite(planID);
        } else {
            sprite = assHand.getSprite(actionID);
        }
        // sprite.setRotation();
        sprite.flip(false, true);
        sprite.setPosition(XPOS, YPOS);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
    }
}
