package com.android.game;

import com.badlogic.gdx.math.Vector2;

public interface Controllable {
    public boolean ping(); // returns true if successful
    public boolean move(Vector2 destination);
    public boolean attack();

    public boolean parseCommands(); // returns false if there are no commands
    public void addCommand(Command command);       // left
    public void removeLastCommand();
}
