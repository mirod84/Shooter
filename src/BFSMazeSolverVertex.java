import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BFSMazeSolverVertex  {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    Maze maze;
    Graph graph;
    public LinkedList<Vertex> BFSMazeSolverVertex(Maze maze) {

        this.maze = maze;
        this.graph = maze.graph;
        Rectangle startRectangle = new Rectangle((int)maze.enemy.getX(),(int)maze.enemy.getY(),maze.enemy.width,maze.enemy.height);
        Rectangle endRectangle = new Rectangle((int)maze.player.getX(),(int)maze.player.getY(),maze.player.width,maze.player.height);

        Vertex startVert  = graph.getVertices().get(startRectangle);
        Vertex endVert = graph.getVertices().get(endRectangle);

        Boolean validRectangle = true;

        LinkedList<Vertex> queue = new LinkedList<>(); // LinkedList implements Queue
        HashMap<Vertex, Vertex> visited = new HashMap<>();

        visited.put(startVert, null); // this is the first vertex

        Vertex current = startVert;

        while (current != endVert ) { // repeats until the end is reached

            LinkedList<Vertex> adjacents = current.getAdjacents(); // get adjacents

            for (Vertex v: adjacents) { // add all the adjacents
                if (!visited.containsKey(v)) { // but only if it hasn't already been traversed
                    visited.put(v, current);
                    queue.add(v);
                }
            }

            if (queue.size()>0) {
                current = queue.remove(); // goes to the next vertex
            }
            else {
                break;
            }

        }

        // create the path
        LinkedList<Vertex> path = new LinkedList<>();
        path.addFirst(current);
        while (current != startVert) {
            current = visited.get(current);
            path.addFirst(current); // addFirst is used to get the correct order
        }

        return path;
    }







}
