package com.android.game;

import java.util.ArrayList;

public class GameLogic {
	//TODO member variables
	ArrayList<Drawable> visibleObjects;
	ArrayList<Updateable> updateableObjects;
	Player player;
	ArtificialIntelligence ai;
	GameState gameState;

	public GameLogic(ArrayList<Drawable> visibleObjects, GameState state,
			Player player, ArtificialIntelligence ai) {
		this.visibleObjects = visibleObjects;
		this.updateableObjects = new ArrayList<Updateable>();
		this.gameState = state;
		this.player = player;
		this.ai = ai;
	}
	
	public void init() {
		SpaceShip ship = new SpaceShip();
		visibleObjects.add(ship);
		updateableObjects.add(ship);
		gameState.plan();
	}

	public void update() {
		for(Updateable u : updateableObjects) {
			u.update();
		}
	}
}

