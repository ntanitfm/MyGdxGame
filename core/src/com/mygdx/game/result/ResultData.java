package com.mygdx.game.result;

/**
 * 結果データの格納
 * Created by ntani on 2017/11/02.
 */

class ResultData {
    public String mode;
    public long time;
    public String name;

    ResultData(String mode, long time) {
        this.mode = mode;
        this.name = "NameLess";
        this.time = time;
    }

    ResultData(String mode, long time, String name) {
        this.mode = mode;
        this.name = name;
        this.time = time;
    }
}
