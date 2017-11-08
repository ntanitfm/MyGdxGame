package com.mygdx.game.item;

/**
 * 結果データの格納
 * Created by ntani on 2017/11/02.
 */

public class ResultData {
    public String mode;
    public String time;
    public String name;

    // Firebase用の空のコンストラクタ
    public ResultData() {}

    public ResultData(String mode, long time) {
        this.mode = mode;
        this.name = " - ";
        this.time = "" + (time / 1000f) + "秒";
    }

    // Firebase用のGetter, Setter
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "mode = " + mode + ", time = " + time + ", name = " + name;
    }
}
