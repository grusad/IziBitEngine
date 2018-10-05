import engine.IBColor;
import engine.IziBitApplication;
import engine.IziBitConfiguration;
import engine.Renderer;

public class TestProgram extends IziBitApplication {

    public TestProgram(){
        super(new IziBitConfiguration(1280, 720, 3, "Test application"));
    }
    @Override
    public void initalize() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Renderer renderer) {

        renderer.drawCircle(32, 32, 32, IBColor.GREEN);
    }

    public static void main(String[] args){
        new TestProgram();
    }
}
