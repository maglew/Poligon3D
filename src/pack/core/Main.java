package pack.core;

import org.lwjgl.opengl.Display;
import pack.render.DisplayManager;
import pack.render.Loader;
import pack.render.RawModel;
import pack.render.Renderer;

public class Main
{

    public static void main(String[] args)
    {
        DisplayManager.createDisplay();

        Loader loader=new Loader();
        Renderer renderer=new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0,
                -0.5f, -0.5f, 0,
                0.5f, -0.5f, 0,
                0.5f, 0.5f, 0,
        };

        int[] indices ={
                0,1,3,
                3,1,2
        };
        RawModel model = loader.loadToVAO(vertices,indices);

        while(!Display.isCloseRequested())
        {
            renderer.prepare();
            renderer.render(model);
            DisplayManager.updateDisplay();
        }
     loader.cleanUp();
    DisplayManager.closeDisplay();
    }
}
