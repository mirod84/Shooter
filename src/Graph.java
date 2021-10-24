import java.awt.*;
import java.util.HashMap;

public class Graph {

    private HashMap<Rectangle, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public Vertex addVertex(Rectangle rectangle) {
        Vertex vertex = new Vertex();
        vertex.setRectangle(rectangle);
        vertices.put(rectangle,vertex);
        return vertex;
    }

    public void addEdge(Rectangle vertex1, Rectangle vertex2) {
        Vertex v1 = vertices.get(vertex1);
        Vertex v2 = vertices.get(vertex2);

        v1.addAdjacents(v2);
        v2.addAdjacents(v1);
    }

    public HashMap<Rectangle, Vertex> getVertices() {
        return vertices;
    }


}
