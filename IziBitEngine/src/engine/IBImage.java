package engine;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IBImage {

    private int[] pixels;
    private int width;
    private int height;

    public IBImage(String path) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(path));
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.pixels = new int[width * height];
            this.pixels = image.getRGB(0, 0, width, height, null, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load image from path [" + path + "].");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

}
