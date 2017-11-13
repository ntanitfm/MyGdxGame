package com.mygdx.game.license;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.Shisen;
import com.mygdx.game.title.TitleScreen;

/**
 * Created by ntani on 2017/11/13.
 */

class LicenseEnvironment {
    private String TAG = LicenseEnvironment.class.getSimpleName();
    Shisen game;

    LicenseEnvironment(Shisen game) {
        Gdx.app.log(TAG, "Constructor");
        this.game = game;
    }

    // ライセンス表示用テーブルの作成
    Table getTable() {
        Gdx.app.log(TAG, "getTable called");
        String somescript = LicenceScripts.libgdxLicense;   // ここは引数でscriptの内容を変更できるようにする
        Label script = new Label(somescript, Config.skin);
        script.setFontScale(1.1f);
        Table container = new Table();
        container.padLeft(100f).padRight(100f);
        container.setFillParent(true);
        ScrollPane scrollPane = new ScrollPane(script, Config.skin);
        container.add(scrollPane);
//        container.debugAll();
        return container;
    }

    // タイトルへ戻るボタン
    TextButton getTitleButton() {
        Gdx.app.log(TAG, "getTitleButton called");
        float width = Config.TXTBTN_WIDTH_S;
        float height = Config.TXTBTN_HEIGHT;
        TextButton txtBtn = new TextButton(Config.TITL, Config.skin);
        txtBtn.setSize(width, height);
        txtBtn.setPosition(0f, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // スクリーン遷移用リスナー
    private void setBtnListener(final TextButton txtBtn) {
        Gdx.app.log(TAG, "setBtnListener called");
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                String gamemode = txtBtn.getText().toString();
                Gdx.app.log(TAG, "gameMode = " + gamemode);
                // ランキング画面へ
                if(gamemode.equals(Config.TITL)) {
                    game.setScreen(new TitleScreen(game));
                }
                return true;
            }
        });
    }
}
