package engine;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;

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

            System.out.println("Could not load image from path [" + path + "].");
        }
    }

    public IBImage(int[] pixels, int width, int height){
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public static IBImage[] split(int cols, int rows, IBImage image){

        int width = image.getWidth() / cols;
        int height = image.getHeight() / rows;

        IBImage[] split = new IBImage[cols * rows];

        int current = 0;
        int[] imagePixels = image.getPixels();

        for (int y = 0; y < rows; y++){
            for(int x = 0; x < cols; x++){

                int[] pixels = new int[width * height];

                for(int yy = 0; yy < height; yy++){
                    for(int xx = 0; xx < width; xx++){
                        pixels[xx + yy * width] = imagePixels[(xx + width * x) + (yy + height * y) * image.width];
                    }
                }
                split[current++] = new IBImage(pixels, width, height);
            }
        }
        return split;
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
