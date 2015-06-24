package com.android.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject implements Drawable, Updateable {
    Vector2 position;
    float radius = 0;
    float speed = 5;
    float maxRadius;

    public Wave(Vector2 position, float maxRadius) {
        this.position = position.cpy();
        this.maxRadius = maxRadius;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.circle(position.x, position.y, radius);
        renderer.end();
    }

    @Override
    public void update() {
        radius += Math.min(speed, maxRadius - radius);
    }

    public boolean hasMaxRadius() {
        if (radius >= maxRadius) {
            return true;
        } else {
            return false;
        }
    }
}
