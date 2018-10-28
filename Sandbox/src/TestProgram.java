import engine.IBColor;
import engine.IziBitApplication;
import engine.IziBitConfiguration;
import engine.Renderer;
import engine.Vector2;


public class TestProgram extends IziBitApplication {

    private int x = 10;


    public TestProgram(){
        super(new IziBitConfiguration(640, 420, 2, "Test application"));
    }
    @Override
    public void initalize() {

    }

    @Override
    public void update() {
        x++;
    }

    @Override
    public void render(Renderer renderer) {

        renderer.drawLine(10, x, 20, 20, IBColor.BLUE);

    }

    public static void main(String[] args){
        new TestProgram();
    }
}
