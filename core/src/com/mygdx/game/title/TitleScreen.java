package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.item.Config;
import com.mygdx.game.license.LicenseScreen;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.play.PlayScreen;
import com.mygdx.game.ranking.RankingScreen;

/**
 * タイトル画面
 * 部品の取得や登録、描画、遷移のみを行う。
 * 部品の生成は裏方のTitleEnvironmentに任せている。
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
    TextButton rankingButton;
    ImageButton info;
    Dialog license;

    // 部品生成
    public TitleScreen(MyGdxGame game) {
        Gdx.app.log(TAG, "Constructor in Title");
        this.game = game;
        env = new TitleEnvironment();
        // 中央回転牌
        titlePai = env.getTitlePai();
        // テキストボタン
        easyButton = env.getTitleTextButton(Config.PLAY_LV1, 230);
        normalButton = env.getTitleTextButton(Config.PLAY_LV2, 300);
        rankingButton = env.getTitleTextButton(Config.RANK, 370);
        // ライセンス表示ボタン
        info = env.getInfoButton();
    }

    // 部品登録
    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        // ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
        // ウィジェット追加
        stage.addActor(titlePai);
        stage.addActor(easyButton);
        stage.addActor(normalButton);
        stage.addActor(rankingButton);
        stage.addActor(info);

        license = new Dialog("License", Config.skin) {
            @Override
            public float getPrefWidth() {
                // force dialog width
                // return Gdx.graphics.getWidth() / 2;
                return 700f;
            }

            @Override
            public float getPrefHeight() {
                // force dialog height
                // return Gdx.graphics.getWidth() / 2;
                return 400f;
            }
        };
        license.setModal(true);
        license.setMovable(false);
        license.setResizable(false);

        TextButton btn = new TextButton("OK", Config.skin);
        btn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                // Do whatever here for exit button
                license.hide();
                license.cancel();
                license.remove();
                return true;
            }
        });
        license.show(stage).setPosition(
                Config.SCRN_WIDTH_CTR - 350,
                Config.SCRN_HEIGHT_CTR - 200
        );
        stage.addActor(license);
    }

    // スクリーン遷移
    private void update() {
        if (env.GAMEMODE != Config.NO_SLCT) {
            Gdx.app.log(TAG, "change to mode :" + env.GAMEMODE);
            // ゲームモードへの遷移
            if(env.GAMEMODE.equals(Config.PLAY_LV1) || env.GAMEMODE.equals(Config.PLAY_LV2)) {
                game.setScreen(new PlayScreen(this.game, env.GAMEMODE));
            }
            // ランキング画面へ
            else if(env.GAMEMODE.equals(Config.RANK)) {
                game.setScreen(new RankingScreen(this.game));
            }
            // ライセンス画面へ
            else if(env.GAMEMODE.equals(Config.LICE)) {
//                game.setScreen(new LicenseScreen(this.game));
            }
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
        if(env.GAMEMODE.equals(Config.LICE)) {
            Gdx.app.log(TAG, "license");
        }
        Config.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}
