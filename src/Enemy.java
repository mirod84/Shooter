import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Enemy extends GameObject {


    private Maze maze;
    List<Coordinate> path;
    int vv=5;
    int yy=1;
    Coordinate start;
    LinkedList<Vertex> pathVertex;

    public Enemy(float x, float y, int width, int height, ID id, Maze maze) {
        super(x, y, width, height, id);

        this.maze = maze;
        vv = 5;
        start = maze.start;
    }

    @Override
    public void tick() {


        //BFSMazeSolver bfs = new BFSMazeSolver();
        //maze.reset();
       // path = bfs.solve(maze);
        //System.out.println("path");
        if (this.maze.start != start) {
            start = this.maze.start;
            yy = 1;
        }




        BFSMazeSolverVertex bfsv = new BFSMazeSolverVertex();
        pathVertex = bfsv.BFSMazeSolverVertex(maze);
       // System.out.println("pathVertex");




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
//            this.setX((int) path.get(yy).getX());
//            this.setY((int) path.get(yy).getY());
            maze.end = new Coordinate((int)this.getX(), (int)this.getY());}
        if(id == ID.Enemy) g.setColor(Color.red);
        g.fillRect((int) x,(int) y,width,height);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
