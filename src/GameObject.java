import java.awt.*;

public abstract class GameObject {

    protected float x,y;
    protected int width,height;



    private Color color;



    protected ID id;
    protected float velX, velY;

    public GameObject(float x, float y, int width, int height, ID id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        System.out.print(this.y);
        System.out.println(" # " + this.x);
    }



    public abstract void tick();
    public abstract boolean collision();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setId(ID id){
        this.id = id;
    }
    public ID getId() { return id; }

    public void setVelX(int velX) {
        this.velX = velX;
    }
    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setVelXDirection(int velX) {
        this.velX = this.velX * velX;
    }
    public void setVelYDirection(int velY) {
        this.velY = this.velY * velY;
    }

    public float getVelX() { return velX; }
    public float getVelY() {
        return velY;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
