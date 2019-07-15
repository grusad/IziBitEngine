import engine.*;
import engine.Font;

import java.awt.*;


public class TestProgram extends IziBitApplication {

    private int x = 10;


    public TestProgram(){
        super(new IziBitConfiguration(1280, 720, 1, "Test application"));
    }
    @Override
    public void initialize() {

    }

    @Override
    public void update() {
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawString(0, 0, "FRAMES-" + getFrames());
        renderer.drawString(0, 10, "UPDATES-" + getUpdates());
    }

    public static void main(String[] args){
        new TestProgram();
    }
}
