package com.mygdx.game.item;

/**
 * 結果データの格納
 * Created by ntani on 2017/11/02.
 */

public class ResultData {
    public String mode;
    public long time;
    public String name;

    public ResultData(String mode, long time) {
        this.mode = mode;
        this.name = "NameLess";
        this.time = time;
    }

    public ResultData(String mode, long time, String name) {
        this.mode = mode;
        this.name = name;
        this.time = time;
    }
}
