import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Enemy extends GameObject {

    private Maze maze;
    int vv=1;
    int yy=0;
    Coordinate start;
    LinkedList<Vertex> pathVertex;

    public Enemy(float x, float y, int width, int height, ID id, Maze maze) {
        super(x, y, width, height, id);

        this.maze = maze;
        vv = 1;
        start = maze.start;
    }

    @Override
    public void tick() {

        if (this.maze.start != start) {
            start = this.maze.start;
            yy = 0;
        }

        BFSMazeSolverVertex bfsv = new BFSMazeSolverVertex();
        pathVertex = bfsv.BFSMazeSolverVertex(maze);
    }

    @Override
    public boolean collision() {
        return false;
    }

    @Override
    public void render(Graphics g) {
        yy = yy + vv;
        if(pathVertex.size() > yy){

        this.setX((int) pathVertex.get(yy).getRectangle().getX());
        this.setY((int) pathVertex.get(yy).getRectangle().getY());
        maze.end = new Coordinate((int)this.getX(), (int)this.getY());}
        if(id == ID.Enemy) g.setColor(Color.yellow);

        int radius = 40;
        int centerX = 50;
        int centerY = 100;
        int angle = 30;

        int dx = (int) (radius * Math.cos(angle * Math.PI / 180));
        int dy = (int) (radius * Math.sin(angle * Math.PI / 180));

        g.fillArc((int) x* Game.GRID_SIZE, (int) y* Game.GRID_SIZE, width* Game.GRID_SIZE, height* Game.GRID_SIZE, angle, 360 - 2 * angle);

       // g.fillRect((int) x* Game.GRID_SIZE,(int) y* Game.GRID_SIZE,width* Game.GRID_SIZE,height* Game.GRID_SIZE);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
