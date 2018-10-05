package engine;

public class IBColor {

    public static final IBColor BLACK = new IBColor(0, 0, 0);
    public static final IBColor WHITE = new IBColor(255, 255, 255);
    public static final IBColor RED = new IBColor(255, 0, 0);
    public static final IBColor GREEN = new IBColor(0, 255, 0);
    public static final IBColor BLUE = new IBColor(0, 0, 255);

    private int hex;

    public IBColor(int r, int g, int b) {

        hex = r;
        hex = (hex << 8) + g;
        hex = (hex << 8) + b;
    }

    public int getHex() {
        return hex;
    }

}
