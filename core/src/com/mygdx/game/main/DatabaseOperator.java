package com.mygdx.game.main;

import com.mygdx.game.item.ResultData;

import java.util.List;

/**
 * DataBaseOperator
 *
 * データを保存したり取得したりするための
 * 各プラットフォームに依存しない
 * 抽象化されたインターフェイス
 * Created by ntani on 2017/11/07.
 */

public interface DatabaseOperator {
    void write(ResultData res);
    List<ResultData> read();
}
