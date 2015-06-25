package com.android.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject implements Drawable, Updateable {
    float radius;
    float speed;
    float maxRadius;
    boolean reflective;

    public Wave(Vector2 position, float radius, float maxRadius, boolean reflective) {
        super.position = position.cpy();
        this.radius = radius;
        this.maxRadius = maxRadius;
        this.reflective = reflective;
        speed = Utils.LIGHT_SPEED;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void draw(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.circle(super.position.x, super.position.y, radius);
        renderer.end();
    }

    @Override
    public void update() {
        radius += Math.min(speed, maxRadius - radius);
    }

    public float getRadius() {
        return  radius;
    }

    public boolean getReflective() {
        return reflective;
    }

    public float getMaxRadius() {
        return maxRadius;
    }
}
