package com.mygdx.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.result.ResultScreen;
import com.mygdx.game.title.TitleScreen;

/**
 * ゲーム画面。
 * easy,normalモードを兼用する。
 * Created by ntani on 2017/10/26.
 */

public class PlayScreen extends ScreenAdapter {
    String TAG = "PlayScreen";
    MyGdxGame game;
    Stage stage;
    Table playTable;
    TextButton backButton;
    PlayEnvironmant env;
    long startTime;

    public PlayScreen(MyGdxGame game, String mode) {
        Gdx.app.log(TAG, "Constructor");
        this.game = game;
        // 開始時刻記録
        startTime = System.currentTimeMillis();
        // 環境設定読み込み(モード選択)
        env = new PlayEnvironmant(mode);
        // テキストボタン
        backButton = env.getPlayBackButton(Config.BACK);
        // 牌テーブル読み込み
        playTable = env.table;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(backButton);
        stage.addActor(playTable);
    }

    private void update() {
        if(env.SCREEN_MODE != Config.NO_SLCT) {
            String mode = env.SCREEN_MODE;
            Gdx.app.log(TAG, "change to PlayScreen :" + mode);
            if(mode.equals(Config.BACK)) {
                game.setScreen(new TitleScreen(this.game));
            }
            if(mode.equals(Config.RSLT)) {
                game.setScreen(new ResultScreen(this.game, env.SCREEN_MODE, System.currentTimeMillis() - startTime));
            }
        }
    }

    private void draw() {
//        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
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
//        Gdx.app.log(TAG, "render");
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

