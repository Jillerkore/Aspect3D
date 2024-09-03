package io.github.jillerkore;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import io.github.jillerkore.screens.GameScreen;

public class Main extends Game implements ApplicationListener {

    private AssetManager assetManager;

    @Override
    public void create() {

        assetManager = new AssetManager();
        GameScreen gameScreen = new GameScreen(assetManager);
        setScreen(gameScreen);


    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
    }
}
