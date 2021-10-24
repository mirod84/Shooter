import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class GraphCreator {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Graph getGraph() {
        return graph;
    }

    private Graph graph;
    public GraphCreator(Maze maze) {
        graph(maze);
    }

    public Graph graph(Maze maze){
        graph = new Graph();

        Rectangle startRectangle = new Rectangle((int)maze.enemy.getX(),(int)maze.enemy.getY(),maze.enemy.width,maze.enemy.height);
        Vertex startVert  = graph.addVertex(startRectangle);
        LinkedList<Vertex> queue = new LinkedList<>(); // LinkedList implements Queue
        HashMap<Vertex, Vertex> visited = new HashMap<>();
        Boolean validRectangle;

        visited.put(startVert, null); // this is the first vertex

        Vertex current = startVert;

        while (true) { // repeats until the end is reached

//            for (Rectangle key : graph.getVertices().keySet()) {
//                if (graph.getVertices().get(key) == current) {
//                    currentRectangle =key;
//                }
//            }
            for (int[] direction : DIRECTIONS) {
                Rectangle tempRectangle  = new Rectangle((int)current.getRectangle().getX() + direction[0],
                        (int)current.getRectangle().getY() + direction[1],
                        (int)current.getRectangle().getWidth(),
                        (int)current.getRectangle().getHeight());

                validRectangle = true;
                for (int i = tempRectangle.x; (i <= tempRectangle.x +tempRectangle.width) && (validRectangle) ; i++) {
                    for (int j = tempRectangle.y; (j <= tempRectangle.y + tempRectangle.height) && (validRectangle) ; j++) {
                        if (i<Game.WIDTH && j < Game.HEIGHT && i>0 && j > 0) {
                            if (maze.exitsElementInObjectTable(i, j)) {
                                validRectangle = false;
                            } else validRectangle = true;
                        }
                        else validRectangle = false;
                    }
                }
                if(validRectangle) {
                    if (!graph.getVertices().containsKey(tempRectangle)) {
                        graph.addVertex(tempRectangle);
                    }
                    Vertex v1 = graph.getVertices().get(tempRectangle);
                    if (!graph.getVertices().get(current.getRectangle()).getAdjacents().contains(v1)) {
                        graph.addEdge(current.getRectangle(), tempRectangle);
                    }
                }
            }

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
        return graph;
}}
