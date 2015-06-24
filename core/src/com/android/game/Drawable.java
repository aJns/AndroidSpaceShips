package com.android.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable {
    public void draw(SpriteBatch batch);

    public void draw(ShapeRenderer renderer);
}
