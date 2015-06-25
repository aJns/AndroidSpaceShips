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

	// returns true if command was finished
	public boolean executeCommand(Controllable subject) {
		if (this.type == CommandType.MOVE) {
			if (this.newCommand) {
				subject.addWave(subject.getPosition(), 0, 500, true);
				this.setOld();
			}

			Vector2 destination = this.commandCoordinates();
			if (subject.getPosition().epsilonEquals(destination, 0.5f)) {
				return true;
			} else {
				subject.move(this.commandCoordinates());
			}
		}
		return false;
	}
}
