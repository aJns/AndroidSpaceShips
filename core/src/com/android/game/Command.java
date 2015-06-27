package com.android.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
				originCoordinates = subject.getPosition();
			}
		}
		return false;
	}

	@Override
	public void draw(SpriteBatch batch, AssetHandler assHand) {
		Sprite sprite = assHand.getSprite("waypoint");
		sprite.setScale(0.25f);
		float drawX = commandCoordinates.x - (sprite.getWidth() / 2);
		float drawY = commandCoordinates.y - (sprite.getHeight() / 2);
		sprite.setPosition(drawX, drawY);
		sprite.draw(batch);

		double x = commandCoordinates.x - originCoordinates.x;
		double y = commandCoordinates.y - originCoordinates.y;
		float length = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		sprite = assHand.getSprite("waypoint_connector");
		sprite.setPosition(originCoordinates.x + ((float) x/2), 
				originCoordinates.y + ((float) y/2));
		sprite.setRotation( (float) Math.toDegrees(Math.atan(y/x)));
		sprite.setScale(length, 1f);
		sprite.draw(batch);

	}

	@Override
	public void draw(ShapeRenderer renderer) {
	}
}
