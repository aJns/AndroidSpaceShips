package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class Utils {
    public static final float LIGHT_SPEED = 5f;

    // Returns the absolute difference between two angles (degrees)
    public static float angleDifference(float a1, float a2) {
        float diff = Math.abs(a1 - a2);
        if (diff > 180f) {
            diff -= 360f;
        }
        return Math.abs(diff);
    }

    // Returns the position vector of a point on the circle edge
    public static Vector2 circlePoint(Vector2 c, float r, float a) {
        Vector2 u = new Vector2(1, 0).setAngle(a);
        return c.cpy().add(u.scl(r));
    }
}
