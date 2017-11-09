package com.mygdx.game.result;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.MyGdxGame;
import com.mygdx.game.item.Config;
import com.mygdx.game.ranking.RankingScreen;
import com.mygdx.game.title.TitleScreen;

import java.util.List;

/**
 * Created by ntani on 2017/11/01.
 */

public class ResultScreen extends ScreenAdapter {
    private String TAG = ResultScreen.class.getSimpleName();
    MyGdxGame game;
    Stage stage;
    Label finishedTime;
    TextField inputName;
    TextButton goTitle;
    TextButton goRanking;
    ResultEnvironment env;

    public ResultScreen(MyGdxGame game, String gameMode, long elapsedTime) {
        Gdx.app.log("@@@", "constructor in Result");
        this.game = game;
        env = new ResultEnvironment(gameMode, elapsedTime, game.dbo);
        finishedTime = env.getTimeLabel();
//        inputName = env.getInputField();
        goTitle = env.getTitleButton();
        goRanking = env.getRankingButton();
    }

    @Override
    public void show() {
        Gdx.app.log("@@@", "show in result");
        //  ステージ生成
        stage = new Stage(Config.viewport);
        stage.addActor(finishedTime);
//        stage.addActor(inputName);
        stage.addActor(goTitle);
        stage.addActor(goRanking);
        Gdx.input.setInputProcessor(stage);
    }

    private void update() {
        if(env.SCREEN_MODE != Config.NO_SLCT) {
            String mode = env.SCREEN_MODE;
            Gdx.app.log(TAG, "change to Screen :" + mode);
            if(mode.equals(Config.TITL)) {
                game.setScreen(new TitleScreen(this.game));
            }
            if(mode.equals(Config.RANK)) {
                game.setScreen(new RankingScreen(this.game));
            }
        }
    }

    private void draw() {
//        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Config.camera.update();
        Config.batcher.setProjectionMatrix(Config.camera.combined);
        // 描画処理
        Config.batcher.begin();
        this.stage.act();
        this.stage.draw();
        Config.batcher.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }
}
