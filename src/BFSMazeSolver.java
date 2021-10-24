import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BFSMazeSolver {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public List<Coordinate> solve(Maze maze) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>();
        Coordinate start = maze.getEntry();
        nextToVisit.add(start);
        Boolean validRectangle;
        for (int i = 0; i < Game.WIDTH ; i++) {
            for (int j = 0; j <Game.HEIGHT ; j++) {
                GameObject tempObject = maze.getObject(i,j);
                if ((tempObject != null) && (tempObject.getId() == ID.Element)) {
                    maze.setVisited(i,j,true);
                }

            }
        }

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();


            if (!maze.isValidLocation(cur.getX(), cur.getY()) || maze.isExplored(cur.getX(), cur.getY())) {
                continue;
            }

            if (maze.isWall(cur.getX(), cur.getY())) {
                maze.setVisited(cur.getX(), cur.getY(), true);
                continue;
            }

            if (maze.isExit(cur.getX(), cur.getY())) {
                return backtrackPath(cur);
            }

            for (int[] direction : DIRECTIONS) {
                Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);

                validRectangle = true;
                for (int i = cur.getX(); (i <= cur.getX() + 20) && (validRectangle) ; i++) {
                    for (int j = cur.getY(); (j <= cur.getY() + 20) && (validRectangle) ; j++) {
                        if (i<Game.WIDTH && j < Game.HEIGHT && i>0 && j > 0) {
                            if (maze.exitsElementInObjectTable(i, j)) {
                                validRectangle = false;
                            } else validRectangle = true;
                        }
                        else validRectangle = false;
                    }
                }

                if(validRectangle) {
                    nextToVisit.add(coordinate);
                }
                maze.setVisited(cur.getX(), cur.getY(), true);
            }
        }
        return Collections.emptyList();
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>();
        Coordinate iter = cur;

        while (iter != null) {
            path.add(iter);
            iter = iter.parent;
        }

        return path;
    }
}