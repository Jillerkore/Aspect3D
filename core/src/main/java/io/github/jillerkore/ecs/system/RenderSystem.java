package io.github.jillerkore.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import io.github.jillerkore.ecs.component.ComponentMappers;
import io.github.jillerkore.ecs.component.RenderComponent;

public class RenderSystem extends IteratingSystem {

    private final ModelBatch batch;
    private final PerspectiveCamera camera;

    public RenderSystem(ModelBatch batch, PerspectiveCamera camera) {
        super(Family.all(RenderComponent.class).get());
        this.batch = batch;
        this.camera = camera;
    }

     // Called for each entity automatically
    @Override
    protected void processEntity(Entity entity, /* Delta Time */ float v) {

        RenderComponent rc = ComponentMappers.render.get(entity);
        // Rendering System


    }

}
