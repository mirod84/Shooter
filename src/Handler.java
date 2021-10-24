import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Handler {

    public GameObject[][] objectsTable;

    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    Maze maze;

    public Handler() {

    }

    public void tick() {
        for (GameObject tempObject : maze.objects) {
            tempObject.tick();
        }

    }

    public void render(Graphics g) {
        for (GameObject object : maze.objects) {
            object.render(g);
        }
    }






}
