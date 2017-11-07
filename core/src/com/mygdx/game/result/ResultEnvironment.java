package com.mygdx.game.result;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

/**
 * Created by ntani on 2017/11/01.
 */

class ResultEnvironment {
    String mode;
    long time;
    DatabaseOperator dbo;
    ResultData rd;
    ResultEnvironment(String gameMode, long elapsedTime, DatabaseOperator dbo) {
        this.mode = gameMode;
        this.time = elapsedTime;
        this.dbo = dbo;
    }
}
