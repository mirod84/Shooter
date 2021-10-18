import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1920, HEIGHT = 1080;
    private int screenWidth;
    private int screenHeight;
    private boolean paused = false;
    private int fps,frameCount = 0;
    private boolean running = false;
    private Thread thread;
    private Mouse mouse;
    private Handler handler;



    public static void main(String[] args) {
        Game game = new Game();
    }

    public Game(){
        handler = new Handler(1080,1920);
        GameObject tempObject = new Element(30,30, 70,200,ID.Element, handler);
        GameObject tempObject2 = new Element(368,130, 70,200,ID.Element, handler);
        GameObject tempObject3 = new Element(830,430, 200,70,ID.Element, handler);
        GameObject tempObject4 = new Element(1200,430, 200,70,ID.Element, handler);
        GameObject tempObject5 = new Element(900,530, 70,70,ID.Element, handler);

        GameObject tempObject11 = new Element(30+80,30, 70,200,ID.Element, handler);
        GameObject tempObject22 = new Element(430+80+100,130+100, 70,200,ID.Element, handler);
        GameObject tempObject23 = new Element(430+80+100,130+100, 20,200,ID.Element, handler);
       // GameObject tempObject33 = new Element(830,430+80, 200,40,ID.Element, handler);
        //GameObject tempObject37 = new Element(930,530+80, 200,40,ID.Element, handler);




        handler.addObject(tempObject);
        handler.addObject(tempObject2);
        handler.addObject(tempObject3);
        handler.addObject(tempObject4);
        handler.addObject(tempObject5);

        handler.addObject(tempObject11);
        //handler.addObject(tempObject22);
        handler.addObject(tempObject23);

        handler.addObject(new Player(500, 300, 84,84, ID.Player, handler));
       // handler.addObject(tempObject33);
       // handler.addObject(tempObject37);


        this.mouse = new Mouse(handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(mouse);


        new Window (WIDTH, HEIGHT, "Shooter", this);


    }



    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }


    public synchronized  void start(int screenWidth, int screenHeight)  {
        setScreenWidth(screenWidth);
        setScreenHeight(screenHeight);

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        this.requestFocus();
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 120.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 1;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 120    ;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused)
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {
                    tick();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
                //drawGame(interpolation);
                render();
                lastRenderTime = now;
                frameCount++;

                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime)
                {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                    System.out.println("FPS: " + fps);
                    System.out.println("updateCount: " + updateCount);
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
                {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering.
                    try {Thread.sleep(1);} catch(Exception e) {}

                    now = System.nanoTime();
                }
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, screenWidth, screenHeight);

        g.setColor(Color.yellow);
        String tekst = "";

       // g.drawString(tekst + screenWidth + " # "+ screenHeight,screenWidth/2 - tekst.length()*3,screenHeight/2);

       handler.render(g);



        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if(var >= max)
            return max;
        else if(var <= min)
            return min;
        else
            return var;
    }
    private void tick() {
        this.mouse.mouseMove();
        handler.tick();
        //tempObject.x = this.mouse.getX()-130/2;
       // tempObject.y = this.mouse.getY()-130/2;
    }


}
