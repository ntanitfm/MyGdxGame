package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
    TitleEnvironment env;
    Image titlePai;
    TextButton easyButton;
    TextButton normalButton;
    TextButton rankingButton;
    ImageButton info;

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
        // ウィジェット追加
        env.stage.addActor(titlePai);
        env.stage.addActor(easyButton);
        env.stage.addActor(normalButton);
        env.stage.addActor(rankingButton);
        env.stage.addActor(info);
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
        env.stage.act();
        env.stage.draw();
        Config.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}
