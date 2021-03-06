package com.android.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class SpaceShip extends GameObject implements Drawable, Updateable, Controllable {
    private Sprite sprite;
    private Sprite destroyedSprite;

    private float speed;
    private float rotation;

    private ArrayList<Command> commands;
    private ShipWeapon gun;
    // private ArrayList<GameObject> gameObjects;

    private SpaceShip(Vector2 position, ControlEntity ctrlEntity,
            ArrayList<GameObject> gameObjects) {
        super(position, ctrlEntity);
        speed = 2;
        rotation = 0;

        commands = new ArrayList<Command>();

        gun = new ShipWeapon(gameObjects, this);
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

        gun.draw(batch, assHand);

        for (Wave w : getWaves()) {
            w.draw(batch, assHand);
        }
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        // Limits for turning
//        renderer.begin(ShapeRenderer.ShapeType.Line);
//        renderer.setColor(Color.RED);
//        float maxAngle = maxTurnAngle();
//        renderer.arc(getPosition().x, getPosition().y, 200f,
//                rotation - maxAngle, 2f * maxAngle);
//        renderer.end();

//        for (Wave w : getWaves()) {
//            w.draw(renderer);
//        }
    }

    public float maxTurnAngle() {
        return (-180f / Utils.LIGHT_SPEED) * speed + 180f;
    }

    @Override
    public void update(GameState state) {
        if (!state.isAction()) { return; }
        parseCommands();
        gun.update(state);
        for (Iterator<Wave> iterator = getWaves().iterator(); iterator.hasNext(); ) {
            Wave wave = iterator.next();
            if (wave.energyDensity() < Utils.ENERGY_DENSITY_THRESHOLD) {
                iterator.remove();
            }
        }
    }

    @Override
    public boolean ping() {
        addWave(getPosition(), 0f, 10000f, true);
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
    public boolean attack(Vector2 target) {
        return gun.shoot(getPosition(), target);
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

    @Override
    protected void takeHit() {
        sprite = destroyedSprite;
        sprite.setScale(0.25f);
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    public static class Builder {
        private String imgID = null;

        public Builder setSprite(String imgID) {
            this.imgID = imgID;
            return this;
        }
        public SpaceShip build(Vector2 position, ControlEntity ctrlEntity, 
                AssetHandler assHand, ArrayList<GameObject> gameObjects) {
            if (imgID == null) { 
                int id = Utils.randomInt(1, 3);
                StringBuilder strBld = new StringBuilder("spaceship");
                strBld.append(id);
                imgID = strBld.toString();
                System.out.println(imgID);
            }

            SpaceShip ship = new SpaceShip(position, ctrlEntity, gameObjects);

            Sprite sprite = assHand.getSprite(this.imgID);
            float scale = 0.25f;
            sprite.setScale(scale);
            ship.sprite = sprite;
            ship.destroyedSprite = assHand.getSprite("explosion");
            return ship;
        }
    }
}
