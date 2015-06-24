package com.android.game;

public class GameState {
    private enum State {
        PLANNING, ACTION, PRESTART
    }

    State state;

    public GameState() {
        state = State.PRESTART;
    }

    public boolean isPlanning() {
        if (state == State.PLANNING) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAction() {
        if (state == State.ACTION) {
            return true;
        } else {
            return false;
        }
    }

    public void action() {
        state = State.ACTION;
    }

    public void plan() {
        state = State.PLANNING;
    }
}
