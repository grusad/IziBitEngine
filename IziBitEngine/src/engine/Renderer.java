package engine;

public class Renderer {

    private Display display;
    private int[] pixels;
    private Font currentFont = Font.DEFAULT;

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

    public void fillRect(Vector2 vector, int width, int height, IBColor color){
        fillRect(vector.x, vector.y, width, height, color);
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

    public void drawRect(Vector2 vector, int width, int height, IBColor color){
        drawRect(vector.x, vector.y, width, height, color);
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

    public void drawCircle(Vector2 vector, int radius, IBColor color){
        drawCircle(vector.x, vector.y, radius, color);
    }

    public void drawTri(int x0, int y0, int x1, int y1, int x2, int y2, IBColor color) {

        drawLine(x0, y0, x1, y1, color);
        drawLine(x1, y1, x2, y2, color);
        drawLine(x2, y2, x0, y0, color);

    }

    public void drawTri(Vector2 vector0, Vector2 vector1, Vector2 vector2, IBColor color){
        drawTri(vector0.x, vector0.y, vector1.x, vector1.y, vector2.x, vector2.y, color);
    }

    public void drawLine(int x0, int y0, int x1, int y1, IBColor color) {

        int dx = x1 - x0;
        int dy = y1 - y0;

        for(int x = x0; x < x1; x++) {
            int y = y0 + dy * (x - x0) / dx;
            drawPixel(x, y, color);
        }
    }

    public void drawLine(Vector2 vector0, Vector2 vector1, IBColor color){
        drawLine(vector0.x, vector0.y, vector1.x, vector1.y, color);
    }

    public void drawImage(int x, int y, IBImage image) {
        for(int x0 = x; x0 < x + image.getWidth(); x0++) {
            for(int y0 = y; y0 < y + image.getHeight(); y0++) {
                drawPixel(x0, y0, image.getPixels()[(x0 - x) + (y0 - y) * image.getWidth()]);
            }
        }
    }

    public void drawImage(Vector2 vector, IBImage image){
        drawImage(vector.x, vector.y, image);
    }

    public void drawPixel(int x, int y, IBColor color) {
        if(!isOutOfBounds(x, y)) {
            pixels[x + y * display.getWidth()] = color.getHex();
        }
    }

    public void drawPixel(Vector2 vector, IBColor color){
        drawPixel(vector.x, vector.y, color);
    }

    public void drawPixel(int x, int y, int pixel) {
        if(!isOutOfBounds(x, y)) {
            pixels[x + y * display.getWidth()] = pixel;
        }
    }

    public void drawString(int x, int y, String text, Font font){
        text = text.toUpperCase();
        int spacing = font.getSpacing();
        for(int i = 0; i < text.length(); i++){

            IBImage charData = font.getData(text.charAt(i));
            if (charData != null){
                drawImage(x + (spacing * i), y, charData);
            }
        }
    }

    public void drawString(Vector2 vector, String text, Font font){
        drawString(vector.x, vector.y, text, font);
    }

    public void drawString(int x, int y, String text){
        drawString(x, y, text, currentFont);
    }

    public void drawString(Vector2 vector, String text){
        drawString(vector.x, vector.y, text);
    }

    public void setFont(Font font){
        this.currentFont = font;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= display.getWidth() || y < 0 || y >= display.getHeight();
    }
}
