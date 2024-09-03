package io.github.jillerkore.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.jillerkore.ecs.component.ComponentMappers;
import io.github.jillerkore.ecs.component.PositionComponent;
import io.github.jillerkore.ecs.component.RenderComponent;
import io.github.jillerkore.ecs.component.VelocityComponent;

public class GeneralMovementSystem extends IteratingSystem {


    public GeneralMovementSystem(Family family) {
        super(Family.all(RenderComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionComponent position = ComponentMappers.position.get(entity);
        VelocityComponent velocity = ComponentMappers.velocity.get(entity);

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        position.z += velocity.z * deltaTime;

    }
}
