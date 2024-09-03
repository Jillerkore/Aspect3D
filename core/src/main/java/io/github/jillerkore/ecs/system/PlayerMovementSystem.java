package io.github.jillerkore.ecs.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import io.github.jillerkore.ecs.component.*;

public class PlayerMovementSystem extends EntitySystem {

    Entity playerEntity;
    PerspectiveCamera camera;
    protected final float degreesPerPixel = 0.05f;

    protected final Vector3 fwdHorizontal = new Vector3();
    protected final Vector3 tmp = new Vector3();
    private final Vector3 tmp2 = new Vector3();
    private final Vector3 tmp3 = new Vector3();


    public PlayerMovementSystem(PerspectiveCamera camera) {
        this.camera = camera;
        camera.lookAt(0, 4, 0);
    }


    public void addedToEngine(Engine engine) {
        playerEntity = getEngine().getEntitiesFor(Family.all(PlayerComponent.class).get()).get(0);
    }

    public void removedFromEngine(Engine engine) {
    }

    public void update(float deltaTime) {

        PositionComponent position = ComponentMappers.position.get(playerEntity);
        VelocityComponent velocity = ComponentMappers.velocity.get(playerEntity);
        InputComponent input = ComponentMappers.input.get(playerEntity);

        // Update the key booleans in the Input Component and sets camera position to player position
        updateKeyPress(input, position);

        // Key press handle
        if (input.w) {
            moveForward(deltaTime * velocity.v, position);
        }
        if (input.a)
            strafe(-deltaTime * velocity.v, position);
        if (input.s)
            moveForward(-deltaTime * velocity.v, position);
        if (input.d)
            strafe(deltaTime * velocity.v, position);

        // Mouse movement handle
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        rotateView(deltaX, deltaY);
    }

    private void updateKeyPress(InputComponent input, PositionComponent position) {

        input.w = Gdx.input.isKeyPressed(Input.Keys.W);
        input.a = Gdx.input.isKeyPressed(Input.Keys.A);
        input.s = Gdx.input.isKeyPressed(Input.Keys.S);
        input.d = Gdx.input.isKeyPressed(Input.Keys.D);

        camera.position.set(position.x, position.y + 4, position.z);

    }

    private void moveForward(float distance, PositionComponent position) {

        fwdHorizontal.set(camera.direction).y = 0;
        fwdHorizontal.nor();
        fwdHorizontal.scl(distance);

        position.x += fwdHorizontal.x;
        position.z += fwdHorizontal.z;

    }

    private void strafe(float distance, PositionComponent position) {
        fwdHorizontal.set(camera.direction).y = 0;
        fwdHorizontal.nor();
        tmp.set(fwdHorizontal).crs(camera.up).nor().scl(distance);

        position.x += tmp.x;
        position.z += tmp.z;
    }

    private void rotateView(float deltaX, float deltaY) {
        camera.direction.rotate(camera.up, deltaX);

        // avoid gimbal lock when looking straight up or down
        Vector3 oldPitchAxis = tmp.set(camera.direction).crs(camera.up).nor();
        Vector3 newDirection = tmp2.set(camera.direction).rotate(tmp, deltaY);
        Vector3 newPitchAxis = tmp3.set(tmp2).crs(camera.up);
        if (!newPitchAxis.hasOppositeDirection(oldPitchAxis))
            camera.direction.set(newDirection);
    }
}
