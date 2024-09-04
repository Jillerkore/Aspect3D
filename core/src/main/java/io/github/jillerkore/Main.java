package io.github.jillerkore;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import io.github.jillerkore.screens.GameScreen;
import net.mgsx.gltf.loaders.gltf.GLTFAssetLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Main extends Game implements ApplicationListener {

    private AssetManager assetManager;

    @Override
    public void create() {

        assetManager = new AssetManager();
        GameScreen gameScreen = new GameScreen(assetManager);
        setScreen(gameScreen);

        loadAssets();

    }

    private void loadAssets() {
        // Knight model
        assetManager.load("models/ship/ship.g3db", Model.class);

        // Load ground texture with mipmap as true
        TextureLoader.TextureParameter textureParameter = new TextureLoader.TextureParameter();
        textureParameter.genMipMaps = true;
        assetManager.load("textures/Stylized_Stone_Floor_005_basecolor.jpg", Texture.class, textureParameter);
    }

    @Override
    public void render() {
        if (!assetManager.update())
            return;
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        getScreen().dispose();
    }
}
