package com.mygdx.game.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.item.Config;

/**
 * Created by ntani on 2017/11/01.
 */

public class ResultScreen extends ScreenAdapter {
    private String TAG = "ResultScreen";
    MyGdxGame game;
    Stage stage;
    Label finishedTime;
    TextField inputName;
    TextButton goRanking;
    TextButton goTop;
    ResultEnvironment env;

    public ResultScreen(MyGdxGame game, String gameMode, long elapsedTime) {
        Gdx.app.log(TAG, "constructor");
        this.game = game;
        // 環境読み込み(経過時間からデータ生成、write、までを行う)
        env = new ResultEnvironment(gameMode, elapsedTime, game.dbo);
        finishedTime = env.getTimeLabel();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        stage.addActor(finishedTime);
        Gdx.input.setInputProcessor(stage);
    }

    private void update() {}

    private void draw() {
//        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 1, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Config.camera.update();
        Config.batcher.setProjectionMatrix(Config.camera.combined);
        // 描画処理
        Config.batcher.begin();
        this.stage.act();
        this.stage.draw();
        Config.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}
