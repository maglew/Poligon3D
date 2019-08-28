package pack.core;

import org.lwjgl.opengl.Display;
import pack.render.DisplayManager;
import pack.render.Loader;
import pack.render.RawModel;
import pack.render.Renderer;
import pack.shaders.StaticShader;
import pack.textures.ModelTexture;
import pack.textures.TexturedModel;

public class Main
{
        public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f,0.5f,0,   //V0
                -0.5f,-0.5f,0,  //V1
                0.5f,-0.5f,0,   //V2
                0.5f,0.5f,0     //V3
        };

        int[] indices = {
                0,1,3,  //Top left triangle (V0,V1,V3)
                3,1,2   //Bottom right triangle (V3,V1,V2)
        };

        float[] textureCoords={
                0,0,
                0,1,
                1,1,
                1,0
        };

            RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
            ModelTexture texture=new ModelTexture(loader.loadTexture("image"));
            TexturedModel texturedModel=new TexturedModel(model,texture);

        while(!Display.isCloseRequested()){
            //game logic
            renderer.prepare();
            shader.start();
            renderer.render(texturedModel);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
