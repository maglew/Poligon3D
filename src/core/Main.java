package core;

import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import render.*;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();

        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture,
                gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

        RawModel model = OBJLoader.loadObjModel("tree", loader);


        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(
                loader.loadTexture("tree")));
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("grassTexture")));
        TexturedModel flower = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
                new ModelTexture(loader.loadTexture("flower")));
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
                new ModelTexture(loader.loadTexture("fern")));
        TexturedModel bobble = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader),
                new ModelTexture(loader.loadTexture("lowPolyTree")));
        grass.getTexture().setHasTransparency(true);
        grass.getTexture().setUseFakeLightning(true);
        flower.getTexture().setHasTransparency(true);
        flower.getTexture().setUseFakeLightning(true);
        fern.getTexture().setHasTransparency(true);


        List<Entity> entities = new ArrayList<Entity>();
        Random random = new Random(676452);
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap,"heightMap") ;

        for (int i = 0; i < 400; i++)
        {    if (1 % 20 == 0){
        float x = random.nextFloat() * 800 - 400;
        float z = random.nextFloat() * -600;
        float y = terrain.getHeightOfTerrain(x, z);
        entities.add(new Entity(fern, new Vector3f(x, y, z), 0, random.nextFloat() * 360,
                0, 0.9f));

            }if (i % 5 == 0){
            float x = random. nextFloat() * 800 - 400;
            float z = random. nextFloat () * -600;
            float y = terrain. getHeightOfTerrain(x, z);
            entities. add (new Entity (bobble, new Vector3f(x, y, z), 0, random. nextFloat () * 360,
                    0, random. nextFloat () * 0.1f + 0.6f) );
            x = random. nextFloat() * 800 - 400;
z= random. nextFloat () * -600;
            y = terrain. getHeightOfTerrain (x, z);
            entities. add (new Entity(staticModel, new Vector3f(x, y, z), 0, 0, 0, random
                    .nextFloat () * 1 + 4) ) ;

    }}
        Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

        MasterRenderer renderer = new MasterRenderer();



        RawModel bunnyModel = OBJLoader. loadObjModel ("person", loader);
        TexturedModel stanfordBunny = new TexturedModel (bunnyModel, new ModelTexture (
                loader. loadTexture ("playerTexture")));

        Player player = new Player(stanfordBunny, new Vector3f(100, 0, -50), 0, 180, 0, 0.6f);
        Camera camera = new Camera(player);

        while(!Display.isCloseRequested()){
            camera.move();
player.move(terrain);
renderer.processEntity(player);
            renderer.processTerrain(terrain);


            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }

}
