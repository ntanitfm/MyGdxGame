package com.mygdx.game.ranking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;

/**
 * Created by ntani on 2017/11/08.
 */

public class RankingScreen extends ScreenAdapter {
    private String TAG = RankingScreen.class.getSimpleName();
    MyGdxGame game;
    Stage stage;
    String viewMode;
    RankingEnvironment env;

    // タイトルから
    public RankingScreen(MyGdxGame game) {
        this.game = game;
        this.viewMode = Config.PLAY_LV1;
    }
    // 結果画面から
    public RankingScreen(MyGdxGame game, String mode) {
        this.game = game;
        this.viewMode = mode;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        // 環境生成
        env = new RankingEnvironment();
        //  ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    void update() {}
    void draw() {
        //        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Config.camera.update();
        Config.batcher.setProjectionMatrix(Config.camera.combined);
        // 描画処理
        Config.batcher.begin();
        this.stage.act();
        this.stage.draw();
        Config.batcher.end();
    }
}
