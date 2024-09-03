package io.github.jillerkore.ecs.component;

import com.badlogic.ashley.core.Component;

// For now it's a collision box
public class CollisionComponent implements Component {
    public float length;
    public float breadth;
    public float height;
    public float px; // Padding along x for position correction
    public float py; // Padding along y for position correction
    public float pz; // Padding along z for position correction
}
