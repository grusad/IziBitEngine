package engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Display {

    private IziBitConfiguration config;
    private JFrame frame;
    private Canvas canvas;
    private BufferedImage image;
    private int[] pixels;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private IBColor clearColor;

    public Display(IziBitConfiguration config) {
        clearColor = IBColor.BLACK;
        this.config = config;
        frame = new JFrame(config.getTitle());
        canvas = new Canvas();
        Dimension dimension = new Dimension(config.getWidth(), config.getHeight());
        frame.setPreferredSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setMaximumSize(dimension);
        frame.add(canvas);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.image = new BufferedImage(config.getWidth() / config.getScale(), config.getHeight() / config.getScale(), BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        canvas.createBufferStrategy(3);
        canvas.addKeyListener(new Input());
        canvas.addMouseListener(new Input());
        canvas.addMouseMotionListener(new Input());
        canvas.requestFocus();

    }

    private void clearDisplay() {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = clearColor.getHex();
        }
    }


    public void start() {
        clearDisplay();
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void stop() {
        graphics.drawImage(image, 0, 0, config.getWidth() * config.getScale(), config.getHeight() * config.getScale(), null);
        bufferStrategy.show();
        graphics.dispose();
    }

    public int getWidth() {
        return frame.getWidth() / config.getScale();
    }

    public int getHeight() {
        return frame.getHeight() / config.getScale();
    }

    public void setClearColor(IBColor color) {
        this.clearColor = color;
    }

    public int[] getPixels() {
        return pixels;
    }

}
