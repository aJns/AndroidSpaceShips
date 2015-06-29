package com.android.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable, Controllable {
    private Sprite sprite;

    private float speed;
    private float rotation;

    private ArrayList<Command> commands;

    private SpaceShip(Vector2 position) {
        super.position = position;
        speed = 2;
        rotation = 0;

        commands = new ArrayList<Command>();
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        //TODO: Remove sprite member variable and get sprite reference here using assHand and imgID
        for (Command c : commands) {
            c.draw(batch, assHand);
        }
        sprite.setRotation(rotation);
        float drawY = getPosition().y - (sprite.getHeight() / 2);
        float drawX = getPosition().x - (sprite.getWidth() / 2);
        sprite.setPosition(drawX, drawY);
        sprite.draw(batch);

        for (Wave w : getWaves()) {
            w.draw(batch, assHand);
        }

    }

    @Override
    public void draw(ShapeRenderer renderer) {
        // Limits for turning
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.RED);
        float maxAngle = maxTurnAngle();
        renderer.arc(getPosition().x, getPosition().y, 200f,
                rotation - maxAngle, 2f * maxAngle);
        renderer.end();
    }

    public float maxTurnAngle() {
        return (-180f / Utils.LIGHT_SPEED) * speed + 180f;
    }

    @Override
    public void update(GameState state) {
        if (!state.isAction()) { return; }
        parseCommands();
        for (Iterator<Wave> iterator = getWaves().iterator(); iterator.hasNext(); ) {
            Wave wave = iterator.next();
            if (wave.energyDensity() < Utils.ENERGY_DENSITY_THRESHOLD) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean ping() {
        addWave(getPosition(), 0, 1000, true);
        return true;
    }

    @Override
    public boolean move(Vector2 destination) {
        Vector2 v = destination.cpy().sub(getPosition()).nor();
        rotation = v.angle();
        v.scl(Math.min(speed, getPosition().dst(destination)));
        getPosition().add(v);
        return true;
    }

    @Override
    public boolean attack() {
        return false;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public boolean parseCommands() {
        if (commands.isEmpty()) { return false; }
        int commandIndex = 0;
        Command command = commands.get(commandIndex);
        if (command.executeCommand(this)) {
            commands.remove(commandIndex);
        }

        return true;
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void removeLastCommand() {
        if (!commands.isEmpty()) commands.remove(commands.size() - 1);
    }

    @Override
    public Vector2 getLastPosition() {
        for (int i = commands.size() - 1; i>-1; i--) {
            if (commands.get(i).type() == Command.CommandType.MOVE) {
                return commands.get(i).commandCoordinates();
            }
        }
        return getPosition();
    }

    public Rectangle getBoundingRectangle() { return sprite.getBoundingRectangle(); }
    public Circle getWaypointHitBox() {
        Circle circle = new Circle(getLastPosition(), diameter);
        return circle;
    }

    @Override
    public Command getLastCommand() {
        if (commands.isEmpty()) { return null; }
        return commands.get(commands.size() - 1);
    }

    public static class Builder {
        private String imgID = "spaceship";

        public Builder setSprite(String imgID) {
            this.imgID = imgID;
            return this;
        }
        public SpaceShip build(Vector2 position, AssetHandler assHand) {
            SpaceShip ship = new SpaceShip(position);
            Sprite sprite = assHand.getSprite(this.imgID);
            float scale = 0.25f;
            sprite.setScale(scale);
            ship.sprite = sprite;
            Rectangle box = sprite.getBoundingRectangle();
            // ship.diameter = box.perimeter();
            System.out.println(box.perimeter());
            System.out.println(box.perimeter());
            return ship;
        }
    }
}
