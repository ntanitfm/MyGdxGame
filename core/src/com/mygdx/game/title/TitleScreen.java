package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.play.PlayScreen;

/**
 * タイトル画面
 * Created by ntani on 2017/10/23.
 */

public class TitleScreen extends ScreenAdapter {
    String TAG = TitleScreen.class.getSimpleName();
    MyGdxGame game;
    Stage stage;
    TitleEnvironment env;
    Image titlePai;
    TextButton easyButton;
    TextButton normalButton;

    public TitleScreen(MyGdxGame game) {
        Gdx.app.log(TAG, "Constructor in Title");
        this.game = game;
        env = new TitleEnvironment();
        // 中央回転牌
        titlePai = env.getTitlePai();
        // テキストボタン
        easyButton = env.getTitleTextButton(Config.PLAY_LV1, 250);
        normalButton = env.getTitleTextButton(Config.PLAY_LV2, 350);
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(titlePai);
        stage.addActor(easyButton);
        stage.addActor(normalButton);
    }

    private void update() {
        if (env.GAMEMODE != Config.NO_SLCT) {
            Gdx.app.log(TAG, "change to PlayScreen :" + env.GAMEMODE);
            game.setScreen(new PlayScreen(this.game, env.GAMEMODE));
        }
    }

    private void draw() {
//        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Config.camera.update();
        Config.batcher.setProjectionMatrix(Config.camera.combined);
        // ここに描画処理
        Config.batcher.begin();
        stage.act();
        stage.draw();
        Config.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
    @Override
    public void hide() {
        Gdx.app.log(TAG, "hide");
    }
    @Override
    public void dispose() {
        Gdx.app.log(TAG, "dispose");
    }
}
