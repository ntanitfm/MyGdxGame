package com.mygdx.game.license;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.ScreenAdapter;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.mygdx.game.item.Config;
        import com.mygdx.game.main.MyGdxGame;

/**
 * Created by ntani on 2017/11/10.
 */

public class LicenseScreen extends ScreenAdapter {
    String TAG = LicenseScreen.class.getSimpleName();
    MyGdxGame game;
    Stage stage;

    public LicenseScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "show");
        stage = new Stage(Config.viewport);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void update() {

    }
    private void draw() {
//        Gdx.app.log(TAG, "draw");
        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 1, 1, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Config.camera.update();
        Config.batcher.setProjectionMatrix(Config.camera.combined);
        Config.batcher.begin();
        stage.act();
        stage.draw();
        Config.batcher.end();
    }
}
