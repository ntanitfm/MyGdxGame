package com.mygdx.game.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.item.Config;
import com.mygdx.game.license.LicenseScreen;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.play.PlayScreen;
import com.mygdx.game.ranking.RankingScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.mygdx.game.item.Config.*;
/**
 * TitleScreenの部品生成、状態管理などの
 * 裏方の仕事を行う。
 * これにより、TitleScreen側からは
 * 部品の取得、登録、描画、遷移といった抽象的な命令で
 * コーディング出来る。
 *
 * Created by ntani on 2017/11/01.
 */

class TitleEnvironment {
    private String TAG = TitleEnvironment.class.getSimpleName();
    MyGdxGame game;
    Stage stage;
    String GAMEMODE;

    TitleEnvironment(MyGdxGame game) {
        Gdx.app.log(TAG, "Construct in titleEnv");
        this.game = game;
        // ステージ生成
        stage = new Stage(Config.viewport);
        Gdx.input.setInputProcessor(stage);
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
        txtBtn.setSize(TXTBTN_WIDTH_L, TXTBTN_HEIGHT);
        txtBtn.setPosition(SCRN_WIDTH_CTR - TXTBTN_WIDTH_L_CTR, SCRN_HEIGHT_CTR - y_from_center);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // licence表示ボタン
    ImageButton getInfoButton() {
        // サイズ
        float width = 30f;
        float height = 30f;
        // テクスチャ準備
        Texture texture = new Texture(Gdx.files.internal("icon/button/info.png"));
        TextureRegion txRegion = new TextureRegion(texture);
        TextureRegionDrawable txrDrawable = new TextureRegionDrawable(txRegion);
        // イメージボタン作成
        ImageButton imgBtn = new ImageButton(txrDrawable);
        imgBtn.setBounds(10, Config.SCRN_HEIGHT - height - 10, width, height);
        // リスナー設定
        setInfoListener(imgBtn);
        return imgBtn;
    }

    // Play or Rankingスクリーン遷移用リスナー
    private void setBtnListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                String gamemode = txtBtn.getText().toString();
                Gdx.app.log(TAG, "GAMEMODE = " + gamemode);
                // ゲームモードへの遷移
                if(gamemode.equals(Config.PLAY_LV1) || gamemode.equals(Config.PLAY_LV2)) {
                    game.setScreen(new PlayScreen(game, gamemode));
                }
                // ランキング画面へ
                else if(gamemode.equals(Config.RANK)) {
                    game.setScreen(new RankingScreen(game));
                }
                return true;
            }
        });
    }
    // Licence画面遷移用リスナー
    private void setInfoListener(final ImageButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LicenseScreen(game));
                return true;
            }
        });
    }
}
