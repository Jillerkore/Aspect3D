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

        /*
        * The velocity is supposed to be updated every tick for all entities.
        * After movement, the velocity is nullified. Then, in the next iteration, if the player
        * is supposed to move then the PlayerMovementSystem will assign values to x, y and z
        * components of the velocity then this system will use the velocities and again nullify
        * it.
        * Note for future: For players, the velocity value is assigned every tick by
        * PlayerMovementSystem, but for other entities, another System has to be created.
        */

        velocity.x = 0;
        velocity.y = 0;
        velocity.z = 0;
    }
}
