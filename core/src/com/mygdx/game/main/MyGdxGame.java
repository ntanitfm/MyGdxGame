package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.mygdx.game.title.TitleScreen;

public class MyGdxGame extends Game {
	public DatabaseOperator dbo;

	public MyGdxGame(DatabaseOperator dbo) {this.dbo = dbo;}

	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}
}
