package com.android.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void draw(ShapeRenderer renderer) {
        if (radius == 0f) {
            return;
        }

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        if (angle == 0f) {
            float length = 2 * (float) Math.PI * radius;
            int segments = (int) (length / segLength);
            renderer.circle(getPosition().x, getPosition().y, radius,
                    segments);
        } else {
            float length = (float) Math.toRadians(angle) * radius;
            int segments = (int) (length / segLength);
            for (int i = 1; i < segments; i++) {
                float sgmntAngle = angle / (float) segments;
                Vector2 point1 = curvePoint(sgmntAngle * (i - 1));
                Vector2 point2 = curvePoint(sgmntAngle * i);
                renderer.line(point1.x, point1.y, point2.x, point2.y);
            }
        }
        renderer.end();
    }

    Vector2 curvePoint(float ang) {
        Vector2 uv = new Vector2(1, 0).setAngle(direction - angle / 2f + ang);
        return position.cpy().add(uv.scl(radius));
    }

    @Override
    public void update(GameState state) {
        if (state.isAction()) {
            radius += Math.min(speed, maxRadius - radius);
        }
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
}
