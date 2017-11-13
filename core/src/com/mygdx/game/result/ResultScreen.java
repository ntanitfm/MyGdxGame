package com.mygdx.game.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.item.Config;

/**
 * Created by ntani on 2017/11/01.
 */

public class ResultScreen extends ScreenAdapter {
    private String TAG = ResultScreen.class.getSimpleName();
    Stage stage;
    Label finishedTime;
    TextField inputName;
    TextButton goTitle;
    TextButton goRanking;
    ResultEnvironment env;

    public ResultScreen(MyGdxGame game, String gameMode, long elapsedTime) {
        Gdx.app.log(TAG, "constructor in Result");
        env = new ResultEnvironment(game, gameMode, elapsedTime);
        finishedTime = env.getTimeLabel();
//        inputName = env.getInputField();
        goTitle = env.getTitleButton();
        goRanking = env.getRankingButton();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        stage.addActor(finishedTime);
//        stage.addActor(inputName);
        stage.addActor(goTitle);
        stage.addActor(goRanking);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Config.drawRoutine();
        // 描画処理
        Config.batcher.begin();
        this.stage.act();
        this.stage.draw();
        Config.batcher.end();
    }
}
