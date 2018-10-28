package engine;

public class Renderer {

    private Display display;
    private int[] pixels;

    public Renderer(Display display) {
        this.display = display;
        this.pixels = display.getPixels();
    }

    public void fillRect(int x, int y, int width, int height, IBColor color) {
        for(int x0 = x; x0 < x + width; x0++) {
            for(int y0 = y; y0 < y + height; y0++) {

                drawPixel(x0, y0, color);
            }
        }
    }

    public void drawRect(int x, int y, int width, int height, IBColor color) {
        for(int x0 = x; x0 < width + x; x0++) {
            drawPixel(x0, y, color);
            drawPixel(x0, y + height - 1, color);
        }

        for(int y0 = y; y0 < height + y; y0++) {
            drawPixel(x, y0, color);
            drawPixel(x + width - 1, y0, color);
        }
    }

    public void drawCircle(int x, int y, int radius, IBColor color) {
        int xx = radius - 1;
        int yy = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius << 1);

        while(xx >= yy){

            drawPixel(x + xx, y + yy, color);
            drawPixel(x + yy, y + xx, color);
            drawPixel(x - yy, y + xx, color);
            drawPixel(x - xx, y + yy, color);
            drawPixel(x - xx, y - yy, color);
            drawPixel(x - yy, y - xx, color);
            drawPixel(x + yy, y - xx, color);
            drawPixel(x + xx, y - yy, color);

            if(err <= 0) {
                yy++;
                err += dy;
                dy += 2;
            }

            if(err > 0) {
                xx--;
                dx += 2;
                err += dx - (radius << 1);
            }

        }
    }

    public void drawTri(int x0, int y0, int x1, int y1, int x2, int y2, IBColor color) {

        drawLine(x0, y0, x1, y1, color);
        drawLine(x1, y1, x2, y2, color);
        drawLine(x2, y2, x0, y0, color);

    }

    public void drawLine(int x0, int y0, int x1, int y1, IBColor color) {

        int dx = x1 - x0;
        int dy = y1 - y0;

        for(int x = x0; x < x1; x++) {
            int y = y0 + dy * (x - x0) / dx;
            drawPixel(x, y, color);
        }
    }

    public void fillCircle(int x, int y, int radius, IBColor color) {
        //TODO: Build this.
    }

    public void drawImage(int x, int y, IBImage image) {
        for(int x0 = x; x0 < x + image.getWidth(); x0++) {
            for(int y0 = y; y0 < y + image.getHeight(); y0++) {
                drawPixel(x0, y0, image.getPixels()[(x0 - x) + (y0 - y) * image.getWidth()]);
            }
        }
    }

    public void drawPixel(int x, int y, IBColor color) {
        if(!isOutOfBounds(x, y)) {
            pixels[x + y * display.getWidth()] = color.getHex();
        }
    }

    public void drawPixel(int x, int y, int pixel) {
        if(!isOutOfBounds(x, y)) {
            pixels[x + y * display.getWidth()] = pixel;
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= display.getWidth() || y < 0 || y >= display.getHeight();
    }
}
