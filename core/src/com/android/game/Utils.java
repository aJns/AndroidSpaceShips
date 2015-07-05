package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class Utils {
    public static final float LIGHT_SPEED = 5f;
    public static final float ENERGY_DENSITY_THRESHOLD = 1f;

    // Returns the absolute difference between two angles (degrees)
    public static float angleDifference(float a1, float a2) {
        float dif = Math.abs(a1 - a2);
        if (dif > 180f) {
            dif -= 360f;
        }
        return Math.abs(dif);
    }

    // Returns the difference between two angles when starting from a1 and
    // going in the specified direction
    public static float angleDifference(float a1, float a2, int dir) {
        float dif = 0f;
        if (dir == 1) {
            // Clockwise
            if (a2 > a1) {
                dif = a2 - a1;
            } else if (a2 < a1) {
                dif = 360f - (a1 - a2);
            }
        } else if (dir == -1) {
            // CCW
            if (a2 > a1) {
                dif = 360f - (a2 - a1);
            } else if (a2 < a1) {
                dif = a1 - a2;
            }
        }
        return dif;
    }

    // Returns the position vector of a point on the circle edge
    public static Vector2 circlePoint(Vector2 c, float r, float a) {
        Vector2 u = new Vector2(1, 0).setAngle(a);
        return c.cpy().add(u.scl(r));
    }

    public static float angleToRange(float angle) {
        while (angle > 360f) {
            angle -= 360f;
        }
        while (angle < 0f) {
            angle += 360f;
        }
        return angle;
    }
}
