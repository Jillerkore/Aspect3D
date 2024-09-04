package io.github.jillerkore.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import io.github.jillerkore.ecs.component.*;
import io.github.jillerkore.ecs.system.GeneralMovementSystem;
import io.github.jillerkore.ecs.system.PlayerMovementSystem;
import io.github.jillerkore.ecs.system.RenderSystem;
import io.github.jillerkore.screens.GameScreen;
import io.github.jillerkore.world.MainWorld;

public class EntityEngineHandler {

    private Engine engine;
    private PerspectiveCamera camera;
    private final ModelBatch batch;
    private final AssetManager assetManager;
    private final MainWorld mainWorld;


    public EntityEngineHandler(PerspectiveCamera camera, ModelBatch batch, AssetManager assetManager, MainWorld mainWorld) {
        this.camera = camera;
        this.batch = batch;
        this.assetManager = assetManager;
        this.mainWorld = mainWorld;
        initialize();
    }

    private void initialize() {

        engine = new Engine();
        engine.addSystem(new PlayerMovementSystem(camera));
        engine.addSystem(new GeneralMovementSystem());
        engine.addSystem(new RenderSystem(batch, camera, assetManager, mainWorld));

        spawnDefaultPlayer();

    }

    public void update(float delta) {
        engine.update(delta);
    }

    private void spawnDefaultPlayer() {
        Entity playerEntity = new Entity();
        playerEntity.add(new PlayerComponent());
        playerEntity.add(new PositionComponent());
        playerEntity.add(new VelocityComponent());
        playerEntity.add(new RenderComponent("models/ship/ship.g3db"));
        playerEntity.add(new IDComponent(69));
        playerEntity.add(new InputComponent());
        playerEntity.add(new CollisionComponent());
        engine.addEntity(playerEntity);
    }

}
