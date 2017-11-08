package com.mygdx.game.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * 牌の総合情報クラス
 * Created by ntani on 2017/10/26.
 */

public class Pai {
    private String TAG = Pai.class.getSimpleName();
    public Position position;
    public String type;
    public boolean invisible;    // 牌が除去されているか
    public ImageButton imgButton;  // イメージボタン

    public Pai(String type, int y, int x) {
        this.position = new Position(y, x);
        this.type = type;
        this.invisible = false;
        this.imgButton = generateImageButton(type);
    }

    // 同じ牌であるかの判定
    public boolean sameType(Pai pai) {
        if(this.type.equals(pai.type)) return true;
        return false;
    }

    // イメージボタンの作成
    private ImageButton generateImageButton(String type) {
//        Gdx.app.log(TAG, "generateImageButton");
        Gdx.app.log(TAG, "type = " + type);
        String path_up = "icon/med-normal/tile" + type + ".png";
        String path_down = "icon/med-gray/tile" + type + ".png";
        String path_checked = "icon/med-highlight/tile" + type + ".png";
        Drawable up = new TextureRegionDrawable(new TextureRegion(new Texture(path_up)));
        Drawable down = new TextureRegionDrawable(new TextureRegion(new Texture(path_down)));
        Drawable checked = new TextureRegionDrawable(new TextureRegion(new Texture(path_checked)));
        return new ImageButton(up, down, checked);
    }
}
