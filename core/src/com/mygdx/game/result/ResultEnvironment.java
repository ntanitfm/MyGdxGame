package com.mygdx.game.result;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

import static com.mygdx.game.item.Config.skin;

/**
 * Created by ntani on 2017/11/01.
 */

class ResultEnvironment {
    DatabaseOperator dbo;
    ResultData rd;
    String SCREEN_MODE;

    ResultEnvironment(String gameMode, long elapsedTime, DatabaseOperator dbo) {
        rd = new ResultData(gameMode, elapsedTime);
        this.dbo = dbo;
        this.SCREEN_MODE = Config.NO_SLCT;
    }

    // screenからもらってきたtimeを自動で取得
    Label getTimeLabel(){
        float width = 400f;
        float height = 100f;
        Label label = new Label(rd.time, skin);
        label.setFontScale(5);
        label.setSize(width, height);
        label.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - height / 2);
        return label;
    }

    TextField getInputField() {
        float width = 300f;
        float height = 80f;
        TextField txf = new TextField("名前を入力", skin);
        txf.setScale(4);
        txf.setSize(width, height);
        txf.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - 200);
        return txf;
    }

    // タイトルへ戻るボタン
    TextButton getTitleButton(String label) {
        TextButton txtBtn = new TextButton(label, skin);
        txtBtn.setSize(100f, 50f);
        txtBtn.setPosition(0f, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }
    // ランキング画面へ
    TextButton getRankingButton(String label) {
        TextButton txtBtn = new TextButton(label, skin);
        txtBtn.setSize(140f, 50f);
        txtBtn.setPosition(Config.SCRN_WIDTH - 140f, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // ボタン名をそのままSCREEN_MODEへ渡すリスナー
    private void setBtnListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                SCREEN_MODE = txtBtn.getText().toString();
                return true;
            }
        });
    }
}
