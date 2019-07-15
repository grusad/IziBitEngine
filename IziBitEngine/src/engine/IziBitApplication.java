package engine;

public abstract class IziBitApplication implements Runnable{

    private Thread applicationThread;
    private boolean isRunning;
    private Display display;
    private Renderer renderer;
    private int frames;
    private int updates;

    public IziBitApplication(IziBitConfiguration config) {

        display = new Display(config);
        renderer = new Renderer(display);
        applicationThread = new Thread(this, config.getTitle() + " thread");
        isRunning = true;
        initialize();
        applicationThread.start();

    }


    @Override
    public void run() {

        long lastTime = System.nanoTime();
        double delta = 0;
        double ns = 1000000000 / 60d;
        int frames = 0;
        int updates = 0;
        long timer = System.currentTimeMillis();

        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                delta--;
                Input.poll();
                update();
                updates++;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            display.start();
            render(renderer);
            display.stop();

            frames++;


            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                this.frames = frames;
                this.updates = updates;
                updates = 0;
                frames = 0;
            }

        }

    }

    protected void setClearColor(IBColor color) {
        display.setClearColor(color);
    }

    public int getFrames() {
        return frames;
    }

    public int getUpdates() {
        return updates;
    }

    public abstract void initialize();
    public abstract void update();
    public abstract void render(Renderer renderer);


}
