package com.android.game;

import com.badlogic.gdx.math.Vector2;

public interface Controllable {
    public boolean ping(); // returns true if successful
    public boolean move(Vector2 destination);
    public boolean attack(Vector2 target);
    public Vector2 getPosition();
    public float getRotation();
    public Vector2 getLastPosition();   // Gets the last waypoint position
    public Command getLastCommand();
    public void addWave(Vector2 position, float radius, float maxRadius, boolean reflective);
    public void addWave(Vector2 position, float direction, float angle, float radius, float maxRadius, boolean reflective);

    public boolean parseCommands(); // returns false if there are no commands left
    public void addCommand(Command command);
    public void removeLastCommand();
}
