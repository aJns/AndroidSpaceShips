package com.android.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class ShipWeapon implements Updateable, Drawable {
    ArrayList<Projectile> bulletList;

    public ShipWeapon() {
        this.bulletList = new ArrayList<Projectile>();
    }

    public boolean shoot(Vector2 pos, Vector2 target) {
        Projectile bullet = new Projectile(pos, target);
        bulletList.add(bullet);
        return true;
    }

    @Override
    public void update(GameState state) {
        for (Projectile bullet : bulletList) {
            bullet.update(state);
        }
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        for (Projectile bullet : bulletList) {
            bullet.draw(batch, assHand);
        }
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        // TODO Auto-generated method stub

    }
}
