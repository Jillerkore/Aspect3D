package io.github.jillerkore.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import io.github.jillerkore.ecs.component.ComponentMappers;
import io.github.jillerkore.ecs.component.IDComponent;
import io.github.jillerkore.ecs.component.PositionComponent;
import io.github.jillerkore.ecs.component.RenderComponent;
import io.github.jillerkore.world.MainWorld;

import java.util.HashMap;

public class RenderSystem extends IteratingSystem {

    private final ModelBatch batch;
    private final PerspectiveCamera camera;
    private final AssetManager assetManager;
    private ModelInstance instance;
    private HashMap<IDComponent, ModelInstance> instances;
    private MainWorld mainWorld;

    private Vector3 tmp = new Vector3();

    public RenderSystem(ModelBatch batch, PerspectiveCamera camera, AssetManager assetManager, MainWorld mainWorld) {
        super(Family.all(RenderComponent.class, PositionComponent.class).get());
        this.batch = batch;
        this.camera = camera;
        this.assetManager = assetManager;
        this.mainWorld = mainWorld;
        instances = new HashMap<>();
    }

     // Called for each entity automatically AFTER the batch has begun
    @Override
    protected void processEntity(Entity entity, /* Delta Time */ float v) {

        RenderComponent rc = ComponentMappers.render.get(entity);
        IDComponent id = ComponentMappers.id.get(entity);

        initializeModelInstance(rc, id);

        PositionComponent pc = ComponentMappers.position.get(entity);

        instance = instances.get(id);
        instance.transform.set(camera.position.x + 5, camera.position.y, camera.position.z + 5, camera.direction.x, camera.direction.y, camera.direction.z, 0);
        batch.render(instance, mainWorld.getEnvironment());
    }

    private void initializeModelInstance(RenderComponent rc, IDComponent id) {
        if (!instances.containsKey(id)) {
            instances.put(id, new ModelInstance(assetManager.get(rc.path, Model.class)));
        }
    }

}
