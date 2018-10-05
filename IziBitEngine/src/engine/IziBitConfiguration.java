package engine;

public class IziBitConfiguration {

    private int width;
    private int height;
    private int scale;
    private String title;

    public IziBitConfiguration(int width, int height, int scale, String title) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
