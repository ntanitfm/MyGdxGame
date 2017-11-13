package com.mygdx.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.Shisen;

/**
 * ゲーム画面。
 * easy,normalモードを兼用する。
 * Created by ntani on 2017/10/26.
 */

public class PlayScreen extends ScreenAdapter {
    String TAG = PlayScreen.class.getSimpleName();
    Stage stage;
    Table playTable;
    TextButton titleButton;
    TextButton resultButton;
    PlayEnvironment env;

    public PlayScreen(Shisen game, String mode) {
        Gdx.app.log(TAG, "Constructor in Play");
        // 環境設定読み込み(モード選択)
        env = new PlayEnvironment(game, mode);
        // テキストボタン
        titleButton = env.getTitleButton();
        resultButton = env.getResultButton();
        // 牌テーブル読み込み
        playTable = env.table;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(titleButton);
        stage.addActor(resultButton);
        stage.addActor(playTable);
    }

    @Override
    public void render(float delta) {
        Config.drawRoutine();
        // 描画処理
        Config.batcher.begin();
        this.stage.act();
        this.stage.draw();
        Config.batcher.end();
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

