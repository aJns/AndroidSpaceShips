package com.android.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Projectile implements Updateable, Drawable {
    String spriteID = "bullet";
    Vector2 pos;
    Vector2 target;

    private float speed = 4;
    private float rotation = 0;

    public Projectile(Vector2 pos, Vector2 target) {
        this.target = new Vector2(target);
        this.pos = new Vector2(pos);
    }

    @Override
    public void update(GameState state) {
        Vector2 v = target.cpy().sub(pos).nor();
        rotation = v.angle();
        v.scl(Math.min(speed, pos.dst(target)));
        pos.add(v);
    }

    @Override
    public void draw(SpriteBatch batch, AssetHandler assHand) {
        Sprite sprite = assHand.getSprite(spriteID);
        sprite.setRotation(rotation);
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        // TODO Auto-generated method stub

    }
}
