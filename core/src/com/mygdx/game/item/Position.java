package com.mygdx.game.item;

import com.badlogic.gdx.Gdx;

/**
 * 牌の位置情報クラス
 * Created by ntani on 2017/10/27.
 */

public class Position {
    private String TAG = "Position";
    public int x;
    public int y;

    public Position(int y, int x) {
        this.y = y;
        this.x = x;
    }

    // 牌の位置が同じなら真
    public boolean samePlace(Position pos) {
        if(this.x == pos.x) {
            if(this.y == pos.y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.y + ", " + this.x + ") ";
    }

}
