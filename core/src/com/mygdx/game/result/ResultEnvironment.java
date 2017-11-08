package com.mygdx.game.result;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

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
        Label label = new Label(rd.time, Config.skin);
        float width = 400f;
        float height = 100f;
        label.setFontScale(5);
        label.setSize(width, height);
        label.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT_CTR - height / 2);
        return label;
    }
}
