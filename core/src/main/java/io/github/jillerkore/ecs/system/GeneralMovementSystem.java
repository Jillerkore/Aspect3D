package io.github.jillerkore.ecs.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.jillerkore.ecs.component.ComponentMappers;
import io.github.jillerkore.ecs.component.PositionComponent;
import io.github.jillerkore.ecs.component.VelocityComponent;

public class GeneralMovementSystem extends IteratingSystem {


    public GeneralMovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionComponent position = ComponentMappers.position.get(entity);
        VelocityComponent velocity = ComponentMappers.velocity.get(entity);

        if (velocity.x == 0 && velocity.y == 0 && velocity.z == 0)
            return;

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        position.z += velocity.z * deltaTime;

        // Since the velocity is supposed to be updated at every tick.
        velocity.x = 0;
        velocity.y = 0;
        velocity.z = 0;
    }
}
