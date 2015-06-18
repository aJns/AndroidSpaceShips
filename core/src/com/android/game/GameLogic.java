package com.android.game;

import java.util.ArrayList;

public class GameLogic {
	//TODO member variables
	ArrayList<Drawable> visibleObjects;
	ArrayList<Updateable> updateableObjects;
	public GameLogic(ArrayList<Drawable> visibleObjects) {
		this.visibleObjects = visibleObjects;
	}
	
	public void init() {
		SpaceShip ship = new SpaceShip();
		visibleObjects.add(ship);
		updateableObjects.add(ship);
	}

	public void update() {
		for(Updateable u : updateableObjects) {
			u.update();
		}
	}
}

