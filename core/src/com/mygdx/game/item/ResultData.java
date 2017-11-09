package com.mygdx.game.item;

/**
 * 結果データの格納
 * Created by ntani on 2017/11/02.
 */

public class ResultData {
    public String mode;
    public long time;
    public String name;

    // Firebase用の空のコンストラクタ
    public ResultData() {}

    public ResultData(String mode, long time) {
        this.mode = mode;
        this.name = " ***** ";
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

    @Override
    public String toString() {
        return "mode = " + mode + ", time = " + time + ", name = " + name;
    }

    public String generateSec() {
        return "" + (time / 1000f) + "秒";
    }
}
