import java.awt.*;
import java.util.LinkedList;

public class Vertex {

    private LinkedList<Vertex> adjacents;
    private Rectangle rectangle;

    public Vertex() {
        this.adjacents = new LinkedList<>();
        this.rectangle = new Rectangle();
    }

    public LinkedList<Vertex> getAdjacents() {
        return adjacents;
    }

    public void addAdjacents(Vertex adjacentVertex) {
        adjacents.add(adjacentVertex);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
