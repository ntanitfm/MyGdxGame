package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;

/**
 * タイトル画面
 * 部品の取得や登録、描画、遷移のみを行う。
 * 部品の生成は裏方のTitleEnvironmentに任せている。
 * Created by ntani on 2017/10/23.
 */

public class TitleScreen extends ScreenAdapter {
    String TAG = TitleScreen.class.getSimpleName();
    TitleEnvironment env;
    Image titlePai;
    TextButton easyButton;
    TextButton normalButton;
    TextButton rankingButton;
    ImageButton info;

    // 部品生成
    public TitleScreen(MyGdxGame game) {
        Gdx.app.log(TAG, "Constructor in Title");
        env = new TitleEnvironment(game);
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

    @Override
    public void render(float delta) {
        Config.drawRoutine();
        // ここに描画処理
        Config.batcher.begin();
        env.stage.act();
        env.stage.draw();
        Config.batcher.end();
    }
}
