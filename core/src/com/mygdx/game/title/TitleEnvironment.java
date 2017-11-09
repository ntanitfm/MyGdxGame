package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.mygdx.game.item.Config.*;
/**
 * Created by ntani on 2017/11/01.
 */

class TitleEnvironment {
    private String TAG = TitleEnvironment.class.getSimpleName();
    String GAMEMODE;

    TitleEnvironment() {
        Gdx.app.log("@@@", "Construct in titleEnv");
        GAMEMODE = Config.NO_SLCT;
    }

    // タイトル回転牌
    Image getTitlePai() {
        Image ttlPai = new Image(new Texture("icon/normal/p1.png"));
        ttlPai.setSize(TTL_PAI_WIDTH, TTL_PAI_HEIGHT);
        ttlPai.setPosition(SCRN_WIDTH_CTR - TTL_PAI_WIDTH_CTR, SCRN_HEIGHT_CTR - TTL_PAI_HEIGHT_CTR);
        ttlPai.setOrigin(TTL_PAI_WIDTH_CTR, TTL_PAI_HEIGHT_CTR);
        ttlPai.addAction(Actions.forever(rotateBy(TTL_PAI_ROTATE_SPD)));
        return ttlPai;
    }

    // テキストボタン
    TextButton getTitleTextButton(String label, float y_from_center) {
        TextButton txtBtn = new TextButton(label, skin);
        txtBtn.setSize(TTL_TXTBTN_WIDTH, TTL_TXTBTN_HEIGHT);
        txtBtn.setPosition(SCRN_WIDTH_CTR - TTL_TXTBTN_WIDTH_CTR, SCRN_HEIGHT_CTR - y_from_center);
        setListener(txtBtn);
        return txtBtn;
    }

    // リスナー設定
    private void setListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                GAMEMODE = txtBtn.getText().toString();
                Gdx.app.log(TAG, "GAMEMODE = " + GAMEMODE);
                return true;
            }
        });
    }

}
