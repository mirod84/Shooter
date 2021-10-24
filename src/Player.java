import java.awt.*;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    Maze maze;



    public Player(float x, float y, int width, int height,  ID id, Maze maze) {
        super(x, y, width, height, id);
        this.maze = maze;
    }

    @Override
    public void tick() {
        //gameObjectMovementInWallCollision
        if     (velX<0 && velY==0) playerMoveLeft();
        else if(velX>0 && velY==0) playerMoveRight();
        else if(velY<0 && velX==0) playerMoveUp();
        else if(velY>0 && velX==0) playerMoveDown();
        else if(velX>0 && velY<0)  playerMoveUpRight();
        else if(velX>0 && velY>0)  playerMoveDownRight();
        else if(velX<0 && velY>0)  playerMoveDownLeft();
        else if(velX<0 && velY<0)  playerMoveUpLeft();
        else { x += velX;y += velY; }
        //x += velX;y += velY;
        x = Game.clamp((int)x, 10, Game.WIDTH  - 2*this.width);
        y = Game.clamp((int)y, 10, Game.HEIGHT - 2*this.height  );
        maze.start = new Coordinate((int)this.getX(), (int)this.getY());
        //handler.addObject(new Trail(x,y, ID.Trail, Color.white, 32,32,  0.05f, handler));

        //collision();
    }

    private void playerMoveUpLeft() {
        if(velX<0 && velY<0) {

            boolean wallExistsLeft = wallExistsLeft();
            boolean wallExistsUp = wallExistsUp();

            if(!wallExistsUp && wallExistsLeft) {
                y += velY;
            }
            else if(wallExistsUp && !wallExistsLeft) {
                x += velX;
            }
            else if(!wallExistsUp){
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveDownLeft(){
        if(velX<0 && velY>0) {

            boolean wallExistsLeft = wallExistsLeft();
            boolean wallExistsDown = wallExistsDown();

            if(!wallExistsDown && wallExistsLeft) {
                y += velY;
            }
            else if(wallExistsDown && !wallExistsLeft) {
                x += velX;
            }
            else if(!wallExistsDown){
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveDownRight() {
        if(velX>0 && velY>0) {

            boolean wallExistsRight = wallExistsRight();
            boolean wallExistsDown = wallExistsDown();

            if(!wallExistsDown && wallExistsRight) {
                y += velY;
            }
            else if(wallExistsDown && !wallExistsRight) {
                x += velX;
            }
            else if(!wallExistsDown){
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveUpRight(){
        if(velX>0 && velY<0) {

            boolean wallExistsRight = wallExistsRight();
            boolean wallExistsUp = wallExistsUp();

            if(!wallExistsUp && wallExistsRight) {
                y += velY;
            }
            else if(wallExistsUp && !wallExistsRight) {
                x += velX;
            }
            else if(!wallExistsUp){
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveDown(){
        if(velY>0 && velX==0) {
            if(!wallExistsDown()) {
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveUp(){
        if(velY<0 && velX==0) {
                if(!wallExistsUp()) {
                    x += velX;
                    y += velY;
                }
            }
        }
    private void playerMoveRight() {
        if(velX>0 && velY==0) {
            if(!wallExistsRight()) {
                x += velX;
                y += velY;
            }
        }
    }
    private void playerMoveLeft(){
        if(velX<0 && velY==0) {
            if(!wallExistsLeft()) {
                x += velX;
                y += velY;
            }
        }
    }

    private boolean wallExistsLeft() {
        boolean wallExists = false;
        for (float i = x ; (i >= x + velX) && !wallExists; i--) {
            for (float j = y + 1; (j <= y + height ) && !wallExists; j++) {
                if(maze.exitsElementInObjectTable(i,j)) {
                    playerCloseWallPositionXAdjustment(i + 1);
                    wallExists = true;
                }
            }
        }
        return wallExists;
    }

    private boolean wallExistsRight() {
        boolean wallExists = false;
        for (float i = x + width ; (i <= x + width + velX) && !wallExists; i++) {
            for (float j = y ; (j <= y + height  ) && !wallExists; j++) {
                if(maze.exitsElementInObjectTable(i,j)) {
                    playerCloseWallPositionXAdjustment(i - width - 1 );
                    wallExists = true;
                }
            }
        }
        return wallExists;
    }

    private boolean wallExistsUp() {
        boolean wallExists = false;
        for (float j = y ; (j >= y + velY) && !wallExists ; j--) {
            for (float i = x ; (i <= x + width ) && !wallExists; i++) {
                if(maze.exitsElementInObjectTable(i,j)) {
                    playerCloseWallPositionYAdjustment(j + 1);
                    wallExists = true;
                }
            }
        }
        return wallExists;
    }

    private boolean wallExistsDown() {
        boolean wallExists = false;
        for (float j = y + height; (j <= y + height + velY) && !wallExists ; j++) {
            for (float i = x ; (i <= x + width) && !wallExists; i++) {
                if(maze.exitsElementInObjectTable(i,j)) {
                    playerCloseWallPositionYAdjustment(j - height - 1);
                    wallExists = true;
                }
            }
        }
        return wallExists;
    }

    private void playerCloseWallPositionYAdjustment(float inY) { y = inY;}
    private void playerCloseWallPositionXAdjustment(float inX) { x = inX;}


    @Override
    public boolean collision() {
        return false;
    }

    @Override
    public void render(Graphics g) {
        if(id == ID.Player) g.setColor(Color.white);
        Rectangle rec = new Rectangle((int) x, (int) y, this.width, this.height);
        g.fillRect(rec.x,rec.y, rec.width, rec.height);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
