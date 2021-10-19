import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Handler {

    public GameObject[][] objectsTable;
    LinkedList<GameObject> objects = new LinkedList<>();
    Map<ID, List<GameObject>> register = new HashMap<>();
    GameObject player;
    GameObject enemy;

    public Handler(int y, int x) {
        this.objectsTable = new GameObject[y][x];
    }

    public void tick() {
        for (GameObject tempObject : objects) {
            tempObject.tick();
        }

    }

    public GameObject copyObject(GameObject other) {
        return new Element(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.id, this);
    }

    public void render(Graphics g) {
        for (GameObject object : objects) {
            object.render(g);
        }
    }

    public GameObject getObject(int y, int x) {
        return objectsTable[y][x];
    }

    public void addObject(GameObject object) {
        this.objects.add(object);

        if (object.getId() == ID.Player) {
            player = object;
        }

        register.merge(object.getId(), List.of(object), (list1, list2) -> {
            List<GameObject> newList = new ArrayList<>();
            newList.addAll(list1);
            newList.addAll(list2);
            return newList;
        });
        addObjectTable(object);

    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
        register.remove(object.getId());
    }

    public void addObjectTable(GameObject object) {
        for (int y = (int) object.getY(); y <= (int) object.getY() + object.getHeight(); y++) {
            for (int x = (int) object.getX(); x <= (int) object.getX() + object.getWidth(); x++) {
                objectsTable[y][x] = object;
            }
        }
    }

    public void removeObjectTable(GameObject object) {
        for (int y = (int) object.getY(); y <= (int) object.getY() + object.getHeight(); y++) {
            for (int x = (int) object.getX(); x <= (int) object.getX() + object.getWidth(); x++) {
                objectsTable[y][x] = null;
            }
        }
    }

    public boolean exitsElementInObjectTable(float y, float x) {
        GameObject tempObject = objectsTable[(int) y][(int) x];
        if (tempObject != null) {
            return tempObject.getId() == ID.Element;
        }
        return false;
    }


}
