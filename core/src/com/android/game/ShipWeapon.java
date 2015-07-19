package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ShipWeapon implements Updateable, Drawable {
    private ArrayList<Projectile> bulletList;
    private ArrayList<GameObject> gameObjects;
    private GameObject owner;

    public ShipWeapon(ArrayList<GameObject> gameObjects, GameObject owner) {
        this.bulletList = new ArrayList<Projectile>();
        this.gameObjects = gameObjects;
        this.owner = owner;
    }

    public boolean shoot(Vector2 pos, Vector2 target) {
        Projectile bullet = new Projectile(pos, target, gameObjects, owner);
        bulletList.add(bullet);
        return true;
    }

    @Override
    public void update(GameState state) {
        for (Projectile bullet : bulletList) {
            if (bullet.isAlive) {
                bullet.update(state);
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        for (Projectile bullet : bulletList) {
            if (bullet.isAlive) {
                bullet.draw(batch, assHand);
            }
        }
    }

    @Override
    public void draw(ShapeRenderer renderer) {
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
