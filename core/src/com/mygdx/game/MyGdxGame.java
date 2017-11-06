package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.title.TitleScreen;

public class MyGdxGame extends Game {
	DbOperator db;

	public MyGdxGame() {
		this.db = null;
	}

	public MyGdxGame(DbOperator db) {
		this.db = db;
	}
	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}
}
