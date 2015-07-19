package com.android.game;

import java.util.TreeMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AssetHandler {
    AssetManager assMan;
    TreeMap<String, String> assetMap;

    public AssetHandler() {
        assMan = new AssetManager();

        // Save all asset paths in a map
        assetMap = new TreeMap<String, String>();
        assetMap.put("spaceship1", "img/spaceship.png");
        assetMap.put("spaceship2", "img/spaceship2.png");
        assetMap.put("spaceship3", "img/spaceship3.png");
        assetMap.put("waypoint_connector", "img/waypoint_connector.png");
        assetMap.put("waypoint", "img/waypoint.png");
        assetMap.put("wave", "img/wave.png");
        assetMap.put("planning", "img/planning.png");
        assetMap.put("action", "img/action.png");
        assetMap.put("attack_order", "img/attack_order.png");
        assetMap.put("placeholder", "img/placeholder.png");
        assetMap.put("undo", "img/undo.png");
    }
    
    public <T> T getAsset(String assetID) {
        String assetPath = assetMap.get(assetID);
        if (!assMan.isLoaded(assetPath)) {
            assMan.load(assetPath, Texture.class);
            while(!assMan.update());
        }
        return assMan.get(assetPath);
    }

    public Sprite getSprite(String imgID) {
        Texture img = getAsset(imgID);
        return new Sprite(img);
    }
}
