package com.android.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Wave extends GameObject implements Drawable, Updateable {
    private float direction;
    private float angle;
    private float radius;
    private float maxRadius;
    private boolean reflective;
    private float speed;
    private float segLength;

    // Circular wave
    public Wave(Vector2 position, float radius, float maxRadius,
                boolean reflective) {

        super.position = position.cpy();
        this.angle = 360f;
        this.radius = radius;
        this.maxRadius = maxRadius;
        this.reflective = reflective;
        speed = Utils.LIGHT_SPEED;
        segLength = 10f;
    }

    // Arc wave
    public Wave(Vector2 position, float direction, float angle, float radius,
                float maxRadius, boolean reflective) {

        super.position = position.cpy();
        this.direction = direction;
        this.angle = angle;
        this.radius = radius;
        this.maxRadius = maxRadius;
        this.reflective = reflective;
        speed = Utils.LIGHT_SPEED;
        segLength = 10f;
    }

    public float getRadius() {
        return radius;
    }
    public boolean getReflective() {
        return reflective;
    }
    public float getMaxRadius() {
        return maxRadius;
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        Sprite sprite = assHand.getSprite("wave");
        if (radius == 0f) {
            return;
        }

        float length = (float) Math.toRadians(angle) * radius;
        int segments = (int) (length / segLength);
        float startAngle = direction - angle / 2f;
        for (int i = 1; i <= segments; i++) {
            float segAngle = angle / (float) segments;
            Vector2 pos = Utils.circlePoint(getPosition(),
                    radius - sprite.getWidth() / 2f,
                    startAngle + segAngle * i);
            sprite.setCenter(4f, 0f);
            sprite.setOriginCenter();
            sprite.setRotation(startAngle + segAngle * i);
            sprite.setScale(1f, segLength);
            float drawX = pos.x - sprite.getWidth() / 2f;
            float drawY = pos.y;
            sprite.setPosition(drawX, drawY);
            sprite.draw(batch);
        }
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        if (radius == 0f) {
            return;
        }

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        float length = (float) Math.toRadians(angle) * radius;
        int segments = (int) (length / segLength);
        float startAngle = direction - angle / 2f;
        for (int i = 1; i <= segments; i++) {
            float segAngle = angle / (float) segments;
            Vector2 point1 = Utils.circlePoint(getPosition(), radius,
                    startAngle + segAngle * (i - 1));
            Vector2 point2 = Utils.circlePoint(getPosition(), radius,
                    startAngle + segAngle * i);
            renderer.line(point1.x, point1.y, point2.x, point2.y);
        }
        renderer.end();
    }

    @Override
    public void update(GameState state) {
        if (state.isAction()) {
            radius += Math.min(speed, maxRadius - radius);
        }
    }

    public boolean enteredWave(Vector2 pos) {
        float tol = speed / 2f;
        if (MathUtils.isEqual(radius, pos.dst(getPosition()) + tol, tol)) {
            float objAngle = pos.cpy().sub(getPosition()).angle();
            if (Utils.angleDifference(direction, objAngle) <= angle / 2f) {
                return true;
            }
        }

        return false;
    }
}
