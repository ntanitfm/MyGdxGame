package com.mygdx.game.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.ranking.RankingScreen;
import com.mygdx.game.title.TitleScreen;


/**
 * Created by ntani on 2017/11/01.
 */

class ResultEnvironment {
    private String TAG = ResultEnvironment.class.getSimpleName();
    MyGdxGame game;
    ResultData rd;
    String mode;
    long time;

    ResultEnvironment(final MyGdxGame game, String gameMode, long elapsedTime) {
        Gdx.app.log(TAG, "constructor in ResultEnv");
        this.game = game;
        this.mode = gameMode;
        this.time = elapsedTime;
        // 名前の入力
        Gdx.input.getTextInput(new Input.TextInputListener() {
            @Override
            public void input(String text) {
                Gdx.app.log(TAG, "input");
                // 結果データ登録
                rd = new ResultData(mode, time, text);
//                game.dbo.push(rd);
                Gdx.app.log(TAG, "登録データ: " + rd);
            }

            @Override
            public void canceled() {
                // データ登録なし
                Gdx.app.log(TAG, "canceled_データ登録なし");
            }
        }, "名前の入力。", "", "省略可能");
    }

    // screenからもらってきたtimeを自動で取得
    Label getTimeLabel(){
        float width = 400f;
        float height = 100f;
        Label label = new Label(generateSec(time), Config.skin);
        label.setFontScale(5);
        label.setSize(width, height);
        label.setColor(0,0,0,1);
        label.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - height / 2);
        return label;
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
    // ミリ秒を秒に変換
    String generateSec(long time) {
        return "" + (time / 1000f) + "秒";
    }
}
