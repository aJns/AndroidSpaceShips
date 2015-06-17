package com.android.game;

import java.util.ArrayList;

public class GameLogic {
	//TODO member variables
	ArrayList<Drawable> visibleObjects;
	public GameLogic(ArrayList<Drawable> visibleObjects) {
		this.visibleObjects = visibleObjects;
	}
	
	public void init() {
		visibleObjects.add(new SpaceShip());

	}

	public void update() {

	}
}

