package io.github.jillerkore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.*;
import io.github.jillerkore.ecs.EntityEngineHandler;
import io.github.jillerkore.world.MainWorld;

public class GameScreen implements Screen {

    // Asset Manager
    private final AssetManager assetManager;
    private MainWorld world;

    // Camera declarations
    private PerspectiveCamera camera;

    // Model and Environment declarations
    private ModelBatch modelBatch;

    // Test Code

    private boolean initialized = false;

    private EntityEngineHandler entityEngineHandler;

    public GameScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void init() {
        if (!initialized) {
            initialized = true;

            modelBatch = new ModelBatch();

            camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.position.set(0, 4, 0);
            camera.lookAt(0, 4, 0);
            camera.near = 1f;
            camera.far = 300f;
            camera.update();

            world = new MainWorld(assetManager);
            entityEngineHandler = new EntityEngineHandler(camera, modelBatch, assetManager, world);


        }
    }

    @Override
    public void show() {

        // Initialization
        init();

        // hide the mouse cursor and fix it to screen centre, so it doesn't go out the window canvas
        Gdx.input.setCursorCatched(true);
        Gdx.input.setCursorPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

    }

    @Override
    public void render(float v) {

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);

        world.render(v, modelBatch, camera);
        entityEngineHandler.update(v);

        modelBatch.end();
        camera.update();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    // Called when disposing
    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
    }

}
