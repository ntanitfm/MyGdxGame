package com.mygdx.game.main;

import com.mygdx.game.item.ResultData;

import java.util.List;

/**
 * データを保存したり取得したりする
 * プラットフォ無に依存しない
 * 抽象化されたインターフェイス
 * Created by ntani on 2017/11/07.
 */

public interface Dbo {
    void write(ResultData res);
    List<ResultData> read();
}
