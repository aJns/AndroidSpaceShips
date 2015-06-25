package com.android.game;

import com.badlogic.gdx.math.Vector2;

public class Command {
	public enum CommandType {
		MOVE, ATTACK, PING
	}

	CommandType type;
	Vector2 commandCoordinates;
	boolean newCommand;

	public Command(CommandType type, Vector2 commandCoordinates) {
		this.type = type;
		this.commandCoordinates = commandCoordinates;
		this.newCommand = true;
	}

	public CommandType type() { return type; }
	public Vector2 commandCoordinates() { return commandCoordinates; }
	public boolean newCommand() { return newCommand; }

	public void setOld() { newCommand = false; };

	public boolean executeCommand(Controllable subject) {
		return false;
	}
}
