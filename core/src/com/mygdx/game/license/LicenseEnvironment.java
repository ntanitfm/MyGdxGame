package com.mygdx.game.license;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.main.MyGdxGame;

/**
 * Created by ntani on 2017/11/13.
 */

class LicenseEnvironment {
    private String TAG = LicenseEnvironment.class.getSimpleName();
    MyGdxGame game;

    LicenseEnvironment(MyGdxGame game) {
        Gdx.app.log(TAG, "Constructor");
        this.game = game;
    }
}
