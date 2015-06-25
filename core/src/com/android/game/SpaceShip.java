package com.android.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable, Controllable {
    String imgID = "spaceship";
    Sprite sprite;
    Texture img;
    AssetManager assMan;

    float speed;
    float rotation;

    ArrayList<Command> commands;

    public SpaceShip(Vector2 position, TreeMap<String, String> assetMap,
            AssetManager assMan) {
        super();
        super.position = position;
        speed = 2;
        rotation = 0;

        imgID = assetMap.get(imgID);
        assMan.load(imgID, Texture.class);
        while (!assMan.update()) ;
        img = assMan.get(imgID);
        sprite = new Sprite(img);
        this.assMan = assMan;

        commands = new ArrayList<Command>();
    }

    public void setDestination(Vector2 destination) {
        addWave(super.position, 0, 500, true);
    }

    @Override
    public void draw(SpriteBatch batch) {
        float scale = 0.25f;
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        float drawY = super.position.y - (sprite.getHeight() / 2);
        float drawX = super.position.x - (sprite.getWidth() / 2);
        sprite.setPosition(drawX, drawY);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        for (Wave w : super.waves) {
            w.draw(renderer);
        }
    }

    @Override
    public void update() {
        parseCommands();

        for (Wave w : super.waves) {
            w.update();
        }
        for (Iterator<Wave> iterator = super.waves.iterator(); iterator.hasNext(); ) {
            Wave wave = iterator.next();
            if (wave.getRadius() >= wave.getMaxRadius()) {
                iterator.remove();
            }
        }
    }

    void randomizeDestination(int xMax, int yMax) {
        Random rand = new Random();
        Vector2 randVec = new Vector2(rand.nextInt(2 * xMax) - xMax,
                rand.nextInt(2 * yMax) - yMax);
        setDestination(randVec);
    }

    @Override
    public boolean ping() {
        addWave(super.position, 0, 1000, true);
        return true;
    }

    @Override
    public boolean move(Vector2 destination) {
        Vector2 v = destination.cpy().sub(super.position).nor();
        rotation = v.angle();
        v.scl(Math.min(speed, super.position.dst(destination)));
        super.position.add(v);
        return true;
    }

    @Override
    public boolean attack() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean parseCommands() {
        if (commands.isEmpty()) { return false; }
        int commandIndex = 0;
        Command command = commands.get(commandIndex);

        if (command.type == Command.CommandType.MOVE) {
            if (command.newCommand) {
                addWave(super.position, 0, 500, false);
                command.setOld();
            }

            Vector2 destination = command.commandCoordinates();
            if (super.position.epsilonEquals(destination, 0.5f)) {
                commands.remove(commandIndex);
            } else {
                this.move(command.commandCoordinates());
            }
        }
        return true;
    }

    @Override
    public void addCommand(Command command) {
        commands.add(command);
    }

    @Override
    public void removeLastCommand() {
        commands.remove(commands.size() - 1);
    }
}
