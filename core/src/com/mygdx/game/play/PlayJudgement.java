package com.mygdx.game.play;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.item.Pai;
import com.mygdx.game.item.Position;

import java.util.List;

/**
 * Environmentクラスで用いる条件判定用クラス
 * Created by ntani on 2017/10/31.
 */
class PlayJudgement {
    private String TAG = PlayJudgement.class.getSimpleName();
    int ROWS, COLS;

    PlayJudgement(int rows, int cols) {
        this.ROWS = rows;
        this.COLS = cols;
    }

    boolean delJudgemnt(Pai now, Pai dest, List<Pai> paiList) {
        int nowPos = now.posId;
        int destPos = dest.posId;
        Gdx.app.log(TAG, "delJudgemnt " + now.position);
        if(fowardRight(nowPos, destPos, paiList,2)) return true;
        Gdx.app.log(TAG, "right");
        if(fowardLeft(nowPos, destPos, paiList,2)) return true;
        Gdx.app.log(TAG, "left");
        if(fowardUp(nowPos, destPos, paiList,2)) return true;
        Gdx.app.log(TAG, "up");
        if(fowardDown(nowPos, destPos, paiList,2)) return true;
        Gdx.app.log(TAG, "down");
        return false;
    }

    // 右に前進
    private boolean fowardRight(int pos, int dest, List<Pai> paiList, int count) {
        int newPos = pos + 1;
//        Gdx.app.log(TAG, "fowardRight" + newPos);
        // カウント0未満 または論理エリア外ならば、全て除去
        if(count>=0 && isArea(newPos)) {
            if(newPos == dest) return true;
            if(judgeCanSerch(newPos, paiList)) {
                //探索続行
                if(fowardRight(newPos, dest, paiList, count)) return true;
                if(fowardUp(newPos, dest, paiList, count - 1)) return true;
                if(fowardDown(newPos, dest, paiList, count - 1)) return true;
            }
        }
        return false;
    }
    // 左に前進
    private boolean fowardLeft(int pos, int dest, List<Pai> paiList, int count) {
        int newPos = pos - 1;
//        Gdx.app.log(TAG, "fowardLeft" + newPos);
        // カウント0未満 または論理エリア外ならば、全て除去
        if(count>=0 && isArea(newPos)) {
            if(newPos == dest) return true;
            if(judgeCanSerch(newPos, paiList)) {
                //探索続行
                if(fowardLeft(newPos, dest, paiList, count)) return true;
                if(fowardUp(newPos, dest, paiList, count - 1)) return true;
                if(fowardDown(newPos, dest, paiList, count - 1)) return true;
            }
        }
        return false;
    }

    // 上に前進
    private boolean fowardUp(int pos, int dest, List<Pai> paiList, int count) {
        int newPos = pos - COLS;
//        Gdx.app.log(TAG, "fowardUp" + newPos);
        // カウント0未満 または論理エリア外ならば、全て除去
        if(count>=0 && isArea(newPos)) {
            if(newPos == dest) return true;
            if(judgeCanSerch(newPos, paiList)) {
                //探索続行
                if(fowardUp(newPos, dest, paiList, count)) return true;
                if(fowardRight(newPos, dest, paiList, count - 1)) return true;
                if(fowardLeft(newPos, dest, paiList, count - 1)) return true;
            }
        }
        return false;
    }

    // 下に前進
    private boolean fowardDown(int pos, int dest, List<Pai> paiList, int count) {
        int newPos = pos + COLS;
//        Gdx.app.log(TAG, "fowardDown" + newPos);
        // カウント0未満 または論理エリア外ならば、全て除去
        if(count>=0 && isArea(newPos)) {
            if(newPos == dest) return true;
            if(judgeCanSerch(newPos, paiList)) {
                //探索続行
                if(fowardDown(newPos, dest, paiList, count)) return true;
                if(fowardRight(newPos, dest, paiList, count - 1)) return true;
                if(fowardLeft(newPos, dest, paiList, count - 1)) return true;
            }
        }
        return false;
    }

    // 探索可能か否かの判定
    private boolean judgeCanSerch(int pos, List<Pai> paiList) {
        Gdx.app.log(TAG, "here is " + pos);
        // 実エリア内 かつ 移動先のinvisibleがtrue
        if(isRealArea(pos) && paiList.get(pos).invisible) {
            Gdx.app.log(TAG, "moving inside range");
            return true;
        }
        // エリア外
        if(!isRealArea(pos)){
            Gdx.app.log(TAG, "moving outside Range");
            return true;
        }
        Gdx.app.log(TAG, "rejected");
        return false;
    }

    // 位置 -> id
    int posToId(int y, int x) {
        return y * COLS + x;
    }

    // エリア内か判定
    private boolean isArea(int pos) {
        if(-(COLS + 3) <= pos && pos <= (ROWS + 1) * (COLS + 1) - 1)
            return true;
        return false;
    }

    // 実エリア上かの判定
    private boolean isRealArea(int pos) {
        if(0 <= pos && pos < ROWS * COLS - 1)
            return true;
        return false;
    }

    // 全ての牌が除去されたかの判定
    boolean isAllPaiDeleted(List<Pai> pList) {
        for (Pai p : pList)
            if (!p.invisible)
                return false;
        Gdx.app.log(TAG, "All Pai deleted");
        return true;
    }
}
