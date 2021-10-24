import java.util.*;

public class Maze {

    private GameObject[][] maze;
    private boolean[][] visited;
    public Coordinate start;
    public Coordinate end;

    LinkedList<GameObject> objects = new LinkedList<>();
    Map<ID, List<GameObject>> register = new HashMap<>();
    GameObject player;
    GameObject enemy;
    Handler handler;
    Graph graph;


    public Maze() {
        this.maze = new GameObject[Game.WIDTH][Game.HEIGHT];
        this.visited = new boolean[Game.WIDTH][Game.HEIGHT];
        initializeMaze();
    }

    private void initializeMaze() {

       // addObject(new Element(5 , 7 , 40, 40, ID.Element, this));
                addObject(new Element(40 , 80 , 40, 40, ID.Element, this));

        addObject(new Element(10 , 20 , 40, 40, ID.Element, this));
        addObject(new Element(70 , 60 , 40, 40, ID.Element, this));

       // addObject(new Element(100 , 70 , 40, 40, ID.Element, this));

        addObject(new Element(130 , 90 , 40, 40, ID.Element, this));
        addObject(new Element(220 , 120 , 40, 40, ID.Element, this));
        addObject(new Element(260 , 120 , 40, 40, ID.Element, this));

        addObject(new Element(300 , 240 , 40, 40, ID.Element, this));
        addObject(new Element(359 , 140 , 40, 40, ID.Element, this));
//        addObject(new Element(368,130, 70,200,ID.Element,this));
//        addObject(new Element(830,430, 200,70,ID.Element,this));
//        addObject(new Element(1200,430, 200,70,ID.Element,this));
//        addObject(new Element(900,530, 70,70,ID.Element,this));
//        addObject(new Element(30+80,30, 70,200,ID.Element,this));
//        addObject(new Element(430+80+100,130+100, 20,200,ID.Element,this));
      addObject(new Player(130, 20, 20,20, ID.Player,this));

        addObject(new Enemy(100, 100, 20,20, ID.Enemy,this));
        if (graph == null) {
            GraphCreator graphCreator = new GraphCreator(this);
            graph = graphCreator.getGraph();
        }
    }
    public void regenerateGraph(){
        GraphCreator graphCreator = new GraphCreator(this);
        graph = graphCreator.getGraph();
    }

    public GameObject copyObject(GameObject other) {
        return new Element(other.getX(), other.getY(), other.getWidth(), other.getHeight(), other.id,this);
    }

    public GameObject getObject(int x, int y) {
        return maze[x][y];
    }

    public void addObject(GameObject object) {
        this.objects.add(object);

        if (object.getId() == ID.Player) {
            player = object;
            start = new Coordinate((int)player.getX(), (int)player.getY());
        }

        if (object.getId() == ID.Enemy) {
            enemy = object;
            end = new Coordinate((int)enemy.getX(), (int)enemy.getY());
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
                maze[x][y] = object;
            }
        }
    }

    public void removeObjectTable(GameObject object) {
        for (int y = (int) object.getY(); y <= (int) object.getY() + object.getHeight(); y++) {
            for (int x = (int) object.getX(); x <= (int) object.getX() + object.getWidth(); x++) {
                maze[x][y] = null;
            }
        }
    }

    public boolean exitsElementInObjectTable(float x, float y) {
        GameObject tempObject = maze[(int) x][(int) y];
        if (tempObject != null) {
            return tempObject.getId() == ID.Element;
        }
        return false;
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public boolean isValidLocation(int row, int col) {
        if (row < 0 || row >= getWidth() || col < 0 || col >= getHeight()) {
            return false;
        }
        return true;
    }

    public boolean isWall(int row, int col) {

        return exitsElementInObjectTable(row,col);


    }


    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isExit(int x, int y) {
        return x == enemy.getX() && y == enemy.getY();
    }

    public Coordinate getEntry() {
        return start;
    }
    public void reset() {
        for (int i = 0; i < visited.length; i++)
            Arrays.fill(visited[i], false);
    }

}
