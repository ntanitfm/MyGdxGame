package com.mygdx.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.item.Config;
import com.mygdx.game.item.Pai;

import java.util.ArrayList;
import java.util.List;

/**
 * ゲームスクリーンの環境設定を行うクラス。
 * 引数(type)に応じてEasy, Normalモードの画面環境を構築し
 * そのデータをGameScreenへ渡す。
 * Created by ntani on 2017/10/30.
 */

class PlayEnvironmant {
    private String TAG = PlayEnvironmant.class.getSimpleName();
    Table table;                    // 牌を並べるテーブル
    Pai slctedPai;                  // 選択された牌
    PlayJudgement jdg;              // 条件判定クラス
    PlayConf pcnf;                  // モードごとの牌の設定
    List<Pai> paiList;              // 牌の配置
    String SCREEN_MODE;             // スクリーン遷移先

    // 環境設定
    PlayEnvironmant(String mode) {
        Gdx.app.log(TAG, mode + "Mode");
        // 難易度ごとに牌を配置
        pcnf = new PlayConf(mode);
        commonEnvConf();
    }

    // 共通設定
    private void commonEnvConf() {
        SCREEN_MODE = Config.NO_SLCT;
        // 条件判定用クラス
        jdg = new PlayJudgement(pcnf.ROWS, pcnf.COLS);
        // テーブル設置
        table = new Table(Config.skin);
        table.setFillParent(true);
        // 牌一覧・状態を格納するリスト
        paiList = new ArrayList<Pai>();
        for (int i = 0; i < pcnf.ROWS; i++) {
            table.row();
            for (int j = 0; j < pcnf.COLS; j++) {
                Pai tmpPai = new Pai(pcnf.paiTypeList.remove(0), i, j);
//                Gdx.app.log(TAG, "sat pai = " + tmpPai.position + ", type = " + tmpPai.type);
                paiList.add(tmpPai);
                setPaiListener(tmpPai);
                table.add(tmpPai.imgButton).width(pcnf.PAI_WIDTH).height(pcnf.PAI_HEIGHT);
            }
        }
    }

    // 牌選択時の動作
    private void setPaiListener(final Pai pai) {
//        Gdx.app.log(TAG, "setPaiListener");
        pai.imgButton.addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(TAG, "Pai Pressed");
                // 直近に選択された牌が無ければ追加。
                if (slctedPai == null){
                    Gdx.app.log(TAG, "no exist selected Pai");
                    slctedPai = pai;
                    Gdx.app.log(TAG, "slctedPai = " + slctedPai);
                }
                    // 同じ牌を選択したときは、直近の牌を削除
                else if (slctedPai.equals(pai)) {
                    Gdx.app.log(TAG, "equal Pai pressed");
                    slctedPai = null;
                    Gdx.app.log(TAG, "slctedPai = " + slctedPai);
                }
                    // 同じタイプの牌が選択された場合、条件判定
                else {
                    Gdx.app.log(TAG, "same Pai selected. judge Start");
                    if (slctedPai.sameType(pai) && jdg.delJudgemnt(pai, slctedPai, paiList)) {
                        // 牌の無力化
                        deletePai(paiList, pai, slctedPai);
                        // 全牌が除去されたかの判定
                        if(jdg.isAllPaiDeleted(paiList)) SCREEN_MODE = Config.RSLT;
                    }
                    // 牌の選択解除
                    pai.imgButton.toggle();
                    slctedPai.imgButton.toggle();
                    // 直前の選択牌を無効化
                    slctedPai = null;
                }
                return true;
            }
        });
    }

    // 牌の無力化
    private void deletePai(List<Pai> paiList, Pai nowPai, Pai slctedPai) {
        Gdx.app.log(TAG, "deletePai");
        // ボタン登録フラグ抹消
        paiList.get(jdg.convPos_Id(nowPai.position)).invisible = true;
        paiList.get(jdg.convPos_Id(slctedPai.position)).invisible = true;
        // ボタン不可視化
        nowPai.imgButton.setVisible(false);
        slctedPai.imgButton.setVisible(false);
    }

    // タイトルへ戻るボタン
    TextButton getTitleButton() {
        TextButton txtBtn = new TextButton(Config.TITL, Config.skin);
        txtBtn.setSize(100f, 50f);
        txtBtn.setPosition(0f, 0f);
        setBtnListener(txtBtn);
        return txtBtn;
    }
    // result画面へ強制以降(デバッグ用)
    TextButton getResultButton() {
        TextButton txtBtn = new TextButton(Config.RSLT, Config.skin);
        txtBtn.setSize(100f, 50f);
        txtBtn.setPosition(Config.SCRN_WIDTH - 100f, 0f);
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
