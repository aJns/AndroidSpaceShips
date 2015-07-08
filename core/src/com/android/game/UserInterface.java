package com.android.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class UserInterface implements Drawable {
    GameState state;
    Player player;
    float screenWidth = 0;
    float screenHeight = 0;
    String planID = "planning";
    String actionID = "action";
    Sprite stateSprite;
    float stateXPos = 0;
    float stateYPos = 0;
    String undoID = "undo";
    Sprite undoSprite;
    float undoXPos = 0;
    float undoYPos = 0;

    public UserInterface(GameState state, AssetHandler assHand, Player player,
            float screenWidth, float screenHeight) {
        this.state = state;
        this.player = player;
        this.stateSprite = assHand.getSprite(planID);
        this.stateYPos = screenHeight - stateSprite.getHeight();

        this.undoSprite = assHand.getSprite(undoID);
        this.undoYPos = screenHeight - undoSprite.getHeight();
        this.undoXPos = screenWidth - undoSprite.getWidth();
        undoSprite.flip(false, true);

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    // Returns true if input is processed
    public boolean handleInput(Vector2 pos) {
        Rectangle box = stateSprite.getBoundingRectangle();
        if (box.contains(pos)) {
            state.toggleState();
            return true;
        }
        box = undoSprite.getBoundingRectangle();
        if (box.contains(pos)) {
            player.undo();
            return true;
        }
        return false;
    }

    public void resize(float screenWidth, float screenHeight) {
        stateYPos = screenHeight - stateSprite.getHeight();
        undoYPos = screenHeight - undoSprite.getHeight();
        undoXPos = screenWidth - undoSprite.getWidth();
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        if (state.isPlanning()) {
            stateSprite = assHand.getSprite(planID);
        } else {
            stateSprite = assHand.getSprite(actionID);
        }
        stateSprite.flip(false, true);
        stateSprite.setPosition(stateXPos, stateYPos);
        stateSprite.setScale(0.5f);
        stateSprite.draw(batch);

        undoSprite.setPosition(undoXPos, undoYPos);
        undoSprite.setScale(0.5f);
        undoSprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
    }
}
