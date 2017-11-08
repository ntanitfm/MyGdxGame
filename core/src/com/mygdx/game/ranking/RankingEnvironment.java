package com.mygdx.game.ranking;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.ResultData;
import com.mygdx.game.main.DatabaseOperator;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.item.Config.skin;

/**
 * Created by ntani on 2017/11/08.
 */

class RankingEnvironment {
    private String TAG = RankingEnvironment.class.getSimpleName();
    List<ResultData> resultList;
    List<ResultData> showList;
    String viewMode;
    String SCREEN_MODE;
    Table table;

    RankingEnvironment(DatabaseOperator dbo) {
        Gdx.app.log(TAG, "constractor");
        resultList = dbo.read();
//        table = setTable();
    }

    private Table setTable() {
        Table tbl = new Table(skin);
        tbl.setWidth(1500f);
        tbl.setHeight(900f);
        tbl.setColor(0.5f,0.5f,0.8f,1);
        Table rankTable = new Table();
        rankTable.pack();
        ScrollPane scrollPane = new ScrollPane(rankTable);
        tbl.add(scrollPane).fill().expand();
        tbl.pack();
        tbl.setColor(0f,0.5f,0.8f,1);
        return tbl;
    }

    // キーによるリストの絞り込み
    private List<ResultData> getByKey(String key) {
        List<ResultData> retList = new ArrayList<ResultData>();
        for(ResultData rd : resultList) {
            if(key.equals(viewMode)) retList.add(rd);
        }
        return retList;
    }


    // ランキングモード表示用ラベル
    Label getModeLabel(String mode){
        float width = 500f;
        float height = 100f;
        Label label = new Label(mode + " mode Ranking", skin);
        label.setFontScale(2);
        label.setSize(width, height);
        label.setPosition(Config.SCRN_WIDTH_CTR - width / 2, Config.SCRN_HEIGHT - height);
        label.setColor(0,0,0,1);
        label.setAlignment(Align.center);
        return label;
    }


    // タイトルへ戻るボタン
    TextButton getTitleButton(String label) {
        TextButton txtBtn = new TextButton(label, skin);
        txtBtn.setSize(100f, 50f);
        txtBtn.setPosition(0f, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }

    // ボタン名をそのままSCREEN_MODEへ渡すリスナー
    private void setBtnListener(final TextButton txtBtn) {
        txtBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                SCREEN_MODE = txtBtn.getText().toString();
                return true;
            }
        });
    }

}
