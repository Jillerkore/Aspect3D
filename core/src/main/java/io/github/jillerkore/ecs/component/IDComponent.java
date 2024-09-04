package io.github.jillerkore.ecs.component;

import com.badlogic.ashley.core.Component;

// Must be in every entity
public class IDComponent implements Component {

    // Entity id
    public int id;

    public IDComponent(int id) {
        this.id = id;
    }
}
