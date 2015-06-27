package com.android.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Command implements Drawable {
	public enum CommandType {
		MOVE, ATTACK, PING
	}

	CommandType type;
	Vector2 commandCoordinates;
	Vector2 originCoordinates;
	boolean newCommand;

	public Command(CommandType type, Vector2 commandCoordinates, 
			Vector2 originCoordinates) {
		this.type = type;
		this.commandCoordinates = commandCoordinates;
		this.originCoordinates = originCoordinates;
		this.newCommand = true;
	}

	public CommandType type() { return type; }
	public Vector2 commandCoordinates() { return commandCoordinates; }
	public Vector2 originCoordinates() { return originCoordinates; }
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

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(ShapeRenderer renderer) {
		renderer.begin(ShapeRenderer.ShapeType.Line);
		renderer.setColor(Color.WHITE);
		renderer.line(originCoordinates.x, originCoordinates.y, 
				commandCoordinates.x, commandCoordinates.y);
		renderer.end();
	}
}
