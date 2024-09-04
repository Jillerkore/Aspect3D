package io.github.jillerkore.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;

public class MainWorld {

    private Environment environment;
    private Model modelGround;
    private Texture textureGround;
    private Array<ModelInstance> instances;
    private final AssetManager assetManager;
    private boolean initialized = false;

    public MainWorld(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public Environment getEnvironment() {
        return environment;
    }

    private void init() {

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.6f, 0.6f, 0.6f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        textureGround = assetManager.get("textures/Stylized_Stone_Floor_005_basecolor.jpg", Texture.class);
        textureGround.setFilter(Texture.TextureFilter.MipMapLinearLinear, Texture.TextureFilter.Linear);
        textureGround.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        TextureRegion textureRegion = new TextureRegion(textureGround);
        int repeats = 10;
        textureRegion.setRegion(0,0,textureGround.getWidth()*repeats, textureGround.getHeight()*repeats );

        ModelBuilder modelBuilder = new ModelBuilder();
        modelGround = modelBuilder.createBox(100f, 1f, 100f,
            new Material(TextureAttribute.createDiffuse(textureRegion)),
            VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        instances = new Array<>();
        instances.add(new ModelInstance(modelGround, 0, -1, 0));

    }

    public void render(float delta, ModelBatch batch, PerspectiveCamera camera) {

        // To make sure that the initialization occurs AFTER the texture has been loaded into assetManager
        // This is because the render function is only called after the texture is loaded.
        if (!initialized)
            init();

        // Screen already cleared, only render world here
        batch.render(instances, environment);
    }

}
