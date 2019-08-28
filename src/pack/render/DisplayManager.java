package pack.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager {
    private static final int WIDTH=1280;
    private static final int HEIGHT=720;
    private static final int FPS_CAP=120;
    public static void createDisplay()
    {
ContextAttribs attribs = new ContextAttribs(3,2);
attribs.withForwardCompatible(true);
attribs.withProfileCore(true);

        try {
            org.lwjgl.opengl.Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            org.lwjgl.opengl.Display.create(new PixelFormat(),attribs);
            org.lwjgl.opengl.Display.setTitle("Poligon3d");

        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        GL11.glViewport(0,0,WIDTH,HEIGHT);
    }

    public static void updateDisplay()
    {
org.lwjgl.opengl.Display.sync(FPS_CAP);
org.lwjgl.opengl.Display.update();

    }
    public static void closeDisplay()
    {
org.lwjgl.opengl.Display.destroy();

    }
}
