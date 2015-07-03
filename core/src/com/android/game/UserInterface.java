package com.android.game;

import com.android.game.Command.CommandType;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class UserInterface implements Drawable {
    GameState state;
    String planID = "planning";
    String actionID = "action";
    Sprite sprite;
    float xPos = 0;
    float yPos = 0;

    public UserInterface(GameState state, AssetHandler assHand, 
            float screenWidth, float screenHeight) {
        this.state = state;
        sprite = assHand.getSprite(planID);
        yPos = screenHeight - sprite.getHeight();
    }

    // Returns true if input is processed
    public boolean handleInput(Vector2 pos) {
        Rectangle box = sprite.getBoundingRectangle();
        if (box.contains(pos)) {
            state.toggleState();
            return true;
        }
        return false;
    }

    public void resize(float screenWidth, float screenHeight) {
        yPos = screenHeight - sprite.getHeight();
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        if (state.isPlanning()) {
            sprite = assHand.getSprite(planID);
        } else {
            sprite = assHand.getSprite(actionID);
        }
        sprite.flip(false, true);
        sprite.setPosition(xPos, yPos);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
    }
}
