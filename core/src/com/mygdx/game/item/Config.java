package com.mygdx.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;

/**
 * プログラム中に広範に用いられる変数群。
 * インスタンス化を行わずに使用する。
 * Created by ntani on 2017/10/27.
 */

public class Config {
    // 独立変数
    final static public float SCRN_WIDTH            = 1280;
    final static public float SCRN_HEIGHT           = 800;
    final static public float TTL_PAI_WIDTH         = 155;
    final static public float TTL_PAI_HEIGHT        = 222;
    final static public float TTL_PAI_ROTATE_SPD    = 2;
    final static public float TTL_TXTBTN_WIDTH      = 300;
    final static public float TTL_TXTBTN_HEIGHT     = 60;
    // 従属変数
    final static public float SCRN_WIDTH_CTR        = SCRN_WIDTH / 2;
    final static public float SCRN_HEIGHT_CTR       = SCRN_HEIGHT / 2;
    final static public float TTL_PAI_WIDTH_CTR     = TTL_PAI_WIDTH / 2;
    final static public float TTL_PAI_HEIGHT_CTR    = TTL_PAI_HEIGHT / 2;
    final static public float TTL_TXTBTN_WIDTH_CTR  = TTL_TXTBTN_WIDTH / 2;
    final static public float TTL_TXTBTN_HEIGHT_CTR = TTL_TXTBTN_HEIGHT / 2;
    // 状態名
    final static public String NO_SLCT              = "NO_SELECTED";
    final static public String TITL                 = "TITLE";
    final static public String PLAY_LV1             = "EASY";
    final static public String PLAY_LV2             = "FULL";
    final static public String RSLT                 = "RESULT";
    final static public String RANKING              = "RANK";
    // シーン
    final static public SpriteBatch batcher         = new SpriteBatch();
    final static public OrthographicCamera camera   = new OrthographicCamera();
    final static public Viewport viewport           = new FitViewport(Config.SCRN_WIDTH, Config.SCRN_HEIGHT, camera);
    final static public Skin skin                   = new Skin(Gdx.files.internal("skins/uiskin.json"));
}
