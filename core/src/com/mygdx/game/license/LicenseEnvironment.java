package com.mygdx.game.license;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.title.TitleScreen;

/**
 * Created by ntani on 2017/11/13.
 */

class LicenseEnvironment {
    private String TAG = LicenseEnvironment.class.getSimpleName();
    MyGdxGame game;

    LicenseEnvironment(MyGdxGame game) {
        Gdx.app.log(TAG, "Constructor");
        this.game = game;
    }

    // タイトルへ戻るボタン
    TextButton getTitleButton() {
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
