package io.github.jillerkore.ecs;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import io.github.jillerkore.ecs.component.*;
import io.github.jillerkore.ecs.system.PlayerMovementSystem;
import io.github.jillerkore.ecs.system.RenderSystem;
import io.github.jillerkore.screens.GameScreen;

public class EntityEngineHandler {

    private GameScreen gameScreen;
    private Engine engine;
    private PerspectiveCamera camera;
    private ModelBatch batch;

    public EntityEngineHandler(GameScreen gameScreen, PerspectiveCamera camera, ModelBatch batch) {
        this.gameScreen = gameScreen;
        this.camera = camera;
        this.batch = batch;
        initialize();
    }

    private void initialize() {

        engine = new Engine();
        Entity playerEntity = new Entity();
        playerEntity.add(new PlayerComponent());
        playerEntity.add(new PositionComponent());
        playerEntity.add(new VelocityComponent());
        playerEntity.add(new RenderComponent());
        playerEntity.add(new InputComponent());

        engine.addEntity(playerEntity);

        engine.addSystem(new PlayerMovementSystem(camera));
        engine.addSystem(new RenderSystem(batch, camera));

    }

    public void update(float delta) {
        engine.update(delta);
    }

}
