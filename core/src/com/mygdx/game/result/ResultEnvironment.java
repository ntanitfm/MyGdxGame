package com.mygdx.game.result;
import com.mygdx.game.item.ResultData;

/**
 * Created by ntani on 2017/11/01.
 */

class ResultEnvironment {
    String mode;
    long time;
    ResultData rd;
    ResultEnvironment(String gameMode, long elapsedTime) {
        this.mode = gameMode;
        this.time = elapsedTime;
        rd = new ResultData(mode, time);
    }
}
