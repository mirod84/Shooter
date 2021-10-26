import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class Mouse extends MouseAdapter {

    boolean ifMousePressed = false;
    private int x = 0, xMove, xNoCollision, xPointerNoCollision;
    private int y = 0, yMove, yNoCollision, yPointerNoCollision ;
    private int delay = 0;
    private boolean collisionCheck = false;
    GameObject tempGameObject;
    GameObject tempGameObjectRemove,tempGameObjectMove;
    Handler handler;


    public Mouse(Handler handler) {

        this.handler = handler;

    }

    public boolean isIfMousePressed() { return ifMousePressed; }

    public void setIfMousePressed(boolean ifMousePressed) { this.ifMousePressed = ifMousePressed; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public void mousePressed(MouseEvent e) {
        setIfMousePressed(true);
       if(handler.maze.getObject(e.getX()/Game.GRID_SIZE,e.getY()/Game.GRID_SIZE) != null) {
            tempGameObject = handler.maze.getObject(e.getX()/Game.GRID_SIZE, e.getY()/Game.GRID_SIZE);
            tempGameObjectRemove = handler.maze.copyObject(tempGameObject);
            handler.maze.removeObjectTable(tempGameObjectRemove);
            PointerInfo a = MouseInfo.getPointerInfo();
            Point b = a.getLocation();
            setX((int)b.getX());
            setY((int)b.getY());

            tempGameObject.setColor(Color.BLUE);
            delay = 0;

        }

        System.out.println("test");

    }

    public void mouseMove() {

        if (ifMousePressed) {
            try {
                Thread.sleep(1);
                PointerInfo a = MouseInfo.getPointerInfo();
                Point b = a.getLocation();
                    this.xMove = this.getX() - (int) b.getX();
                    this.yMove = this.getY() - (int) b.getY();
                    this.setX((int) b.getX());
                    this.setY((int) b.getY());
                System.out.println(this.y + " # " + this.x);

                if(tempGameObject != null) {

                        this.xNoCollision = (int)tempGameObject.getX();
                        this.yNoCollision = (int)tempGameObject.getY();
                        tempGameObject.setX(tempGameObject.getX() - this.xMove/Game.GRID_SIZE);
                        tempGameObject.setY(tempGameObject.getY() - this.yMove/Game.GRID_SIZE);


                        if (tempGameObject.collision()) {
                            tempGameObject.setX(this.xNoCollision);
                            tempGameObject.setY(this.yNoCollision);
                            Robot robot = new Robot();
                            //collisionCheck = true;
                            //robot.mouseMove(xPointerNoCollision,yPointerNoCollision);
                        }
                    System.out.println(tempGameObject.toString());
                }
            } catch (InterruptedException | AWTException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public void mouseReleased(MouseEvent e){
        ifMousePressed = false;
        if(tempGameObject != null) {

            tempGameObject.setColor(Color.pink);
            handler.maze.addObjectTable(tempGameObject);
            handler.maze.regenerateGraph();
            tempGameObject = null;
        }
        System.out.println("test");
    }

    private boolean mouseOver(int x, int y, int width, int height){
        if(this.x > x && this.x < x + width){
            return this.y > y && this.y < y + height;
        } else return false;

    }
}
