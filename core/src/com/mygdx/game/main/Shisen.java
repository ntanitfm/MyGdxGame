package com.mygdx.game.main;

import com.badlogic.gdx.Game;
import com.mygdx.game.title.TitleScreen;

public class Shisen extends Game {
	public DatabaseOperator dbo;

	public Shisen(DatabaseOperator dbo) {this.dbo = dbo;}

	@Override
	public void create () {
		setScreen(new TitleScreen(this));
	}
}
