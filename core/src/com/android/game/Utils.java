package com.android.game;

public class Utils {
    public static final float LIGHT_SPEED = 5f;

    public static float angleDifference(float a1, float a2) {
        float diff = Math.abs(a1 - a2);
        if (diff > 180f) {
            diff -= 360f;
        }
        return Math.abs(diff);
    }
}
