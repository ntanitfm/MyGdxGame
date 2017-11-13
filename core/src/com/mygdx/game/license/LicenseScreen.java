package com.mygdx.game.license;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;

/**
 * Created by ntani on 2017/11/10.
 */

public class LicenseScreen extends ScreenAdapter {
    String TAG = LicenseScreen.class.getSimpleName();
    MyGdxGame game;
    Stage stage;

    public LicenseScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        // ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
        // ウィジェット追加
    }

    @Override
    public void render(float delta) {
        Config.drawRoutine();
        Config.batcher.begin();
        stage.act();
        stage.draw();
        Config.batcher.end();
    }
}
