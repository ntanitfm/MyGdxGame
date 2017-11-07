package com.mygdx.game.item;

/**
 * 結果データの格納
 * Created by ntani on 2017/11/02.
 */

public class ResultData {
    public String mode;
    public long time;
    public String name;

    public ResultData() {
        // Firebase用の空のコンストラクタ
    }

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

    // Firebase用のGetter, Setter
    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
