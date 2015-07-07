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
    private float energy;
    private boolean reflective;
    private float speed;
    private float segLength;
    private GameObject source;

    // Circular wave
    public Wave(Vector2 position, float radius, float energy,
                boolean reflective, GameObject source) {
        super.position = position.cpy();
        this.angle = 360f;
        this.radius = radius;
        this.energy = energy;
        this.reflective = reflective;
        speed = Utils.LIGHT_SPEED;
        segLength = 10f;
        this.source = source;
    }

    // Arc wave
    public Wave(Vector2 position, float direction, float angle, float radius,
                float energy, boolean reflective, GameObject source) {
        super.position = position.cpy();
        this.direction = direction;
        this.angle = angle;
        this.radius = radius;
        this.energy = energy;
        this.reflective = reflective;
        speed = Utils.LIGHT_SPEED;
        segLength = 10f;
        this.source = source;
    }

    public float getRadius() {
        return radius;
    }
    public boolean getReflective() {
        return reflective;
    }
    public float getEnergy() {
        return energy;
    }
    public float getDirection() {
        return direction;
    }
    public float getAngle() {
        return angle;
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
            float maxRadius = energy / (float) Math.toRadians(angle);
            sprite.setAlpha(1f - radius / maxRadius);
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
        for (int i = 0; i < segments; i++) {
            float segAngle = angle / (float) segments;
            Vector2 point1 = Utils.circlePoint(getPosition(), radius,
                    startAngle + segAngle * i);
            Vector2 point2 = Utils.circlePoint(getPosition(), radius,
                    startAngle + segAngle * (i + 1));
            renderer.line(point1.x, point1.y, point2.x, point2.y);
        }
        renderer.end();
    }

    @Override
    public void update(GameState state) {
        if (state.isAction()) {
            radius += speed;
        }
    }

    // Actually linear energy density (J / m)
    public float energyDensity() {
        float arcLength = (float) Math.toRadians(angle) * radius;
        return energy / arcLength;
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

    public void destroy() {
        energy = 0f;
    }
}
