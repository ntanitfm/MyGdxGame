package com.mygdx.game;

import com.mygdx.game.item.ResultData;

import java.util.List;

/**
 * Created by ntani on 2017/11/06.
 */

public interface DbOperator {
    void post(ResultData res);
    List<ResultData> get();
}
