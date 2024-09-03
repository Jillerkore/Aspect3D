package io.github.jillerkore.ecs.component;

import com.badlogic.ashley.core.Component;

public class RenderComponent implements Component {

    // Model ID
    public int id = 0;

    // Position correction for rendering (if needed)
    public int paddingx = 0; // Position of render relative to x-position
    public int paddingy = 0; // Position of render relative to y-position
    public int paddingz = 0; // Position of render relative to z-position

}
