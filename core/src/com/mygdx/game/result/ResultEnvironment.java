package com.mygdx.game.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.ranking.RankingScreen;
import com.mygdx.game.title.TitleScreen;


/**
 * Created by ntani on 2017/11/01.
 */

class ResultEnvironment {
    private String TAG = ResultEnvironment.class.getSimpleName();
    MyGdxGame game;
    DatabaseOperator dbo;
    ResultData rd;

    ResultEnvironment(MyGdxGame game, String gameMode, long elapsedTime) {
        this.game = game;
        rd = new ResultData(gameMode, elapsedTime);
        this.dbo = game.dbo;
    }

    // screenからもらってきたtimeを自動で取得
    Label getTimeLabel(){
        float width = 400f;
        float height = 100f;
        Label label = new Label(rd.generateSec(), Config.skin);
        label.setFontScale(5);
        label.setSize(width, height);
        label.setColor(0,0,0,1);
        label.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - height / 2);
        return label;
    }

    // 名前入力用フィールド
    TextField getInputField() {
        float width = 300f;
        float height = 80f;
        TextField txf = new TextField("名前を入力", Config.skin);
        txf.setScale(4);
        txf.setSize(width, height);
        txf.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - 200);
        return txf;
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

    // ランキング画面ボタン
    TextButton getRankingButton() {
        float width = Config.TXTBTN_WIDTH_M;
        float height = Config.TXTBTN_HEIGHT;
        TextButton txtBtn = new TextButton(Config.RANK, Config.skin);
        txtBtn.setSize(width, height);
        txtBtn.setPosition(Config.SCRN_WIDTH - width, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // 画面遷移用リスナー
    private void setBtnListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            String mode = txtBtn.getText().toString();
            Gdx.app.log(TAG, "change to Screen :" + mode);
            if(mode.equals(Config.TITL)) {
                game.setScreen(new TitleScreen(game));
            }
            if(mode.equals(Config.RANK)) {
                game.setScreen(new RankingScreen(game));
            }
            return true;
            }
        });
    }
}
