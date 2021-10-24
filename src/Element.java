import java.awt.*;
import java.util.List;

public class Element extends GameObject {

    private Handler handler;
    private Maze maze;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private Color color;


    public Element(float x, float y, int width, int height, ID id,Maze maze) {
        super(x, y, width, height, id);
        color = Color.PINK;
        this.maze = maze;
    }

    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int) this.x,(int) this.y,this.width,this.height);

    }

    public boolean collision() {
        final List<GameObject> gameObjects = maze.register.get(ID.Element);
        for (GameObject gameObject : gameObjects) {
            if (gameObject != this) {
                if (gameObject.getId() == ID.Element) {
                    if (getBounds().intersects(gameObject.getBounds())) {
                        //collision code
                        System.out.println("collision code");
                        return true;
                    }
                }
            }

        }
        return false;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)this.x,(int)this.y, this.width+1, this.height+1);
    }
}
