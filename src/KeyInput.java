import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Optional;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    float speed = 1.5F;

    private boolean[] keyDown = new boolean[4];


    public KeyInput(Handler handler) {

        this.handler = handler;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //System.out.println(key);
        final Optional<GameObject> playerObject = handler.maze.register.get(ID.Player).stream().findFirst();
        playerObject.ifPresent(player -> {
            if (player.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    player.setVelY(-1 * speed);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    player.setVelY(speed);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    player.setVelX(-1 * speed);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    player.setVelX(speed);
                    keyDown[3] = true;
                }
                if (keyDown[1] && keyDown[2] && keyDown[3]) {
//                    System.out.println("$$$$$$$$$$$$$$$$$$$");
                }
                if (keyDown[1] && keyDown[3]) {
//                    System.out.println("@@@@@@@@@@@@@@@@@@@@@");
                }
            }
        });
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.maze.objects.size(); i++) {
            GameObject tempObject = handler.maze.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyDown[0] = false;
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown[1] = false;
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyDown[2] = false;
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyDown[3] = false;




                //vertical movement
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

                if (keyDown[2] && keyDown[1]) {
                    tempObject.setVelX(-1 * speed);
                    tempObject.setVelY(speed);
                }
                if (keyDown[3] && keyDown[1]) {
                    tempObject.setVelX(1);
                    tempObject.setVelY(speed);
                }

                if (keyDown[2] && keyDown[0]) {
                    tempObject.setVelX(-1 * speed);
                    tempObject.setVelY(-1 * speed);
                }
                if (keyDown[3] && keyDown[0]) {
                    tempObject.setVelX(speed);
                    tempObject.setVelY(-1 * speed);
                }

                //0
                if (keyDown[0] && !keyDown[1] && !keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                    tempObject.setVelY(-1 * speed);
                }
                //1
                if (!keyDown[0] && keyDown[1] && !keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(0);
                    tempObject.setVelY(speed);
                }
                //2
                if (!keyDown[0] && !keyDown[1] && keyDown[2] && !keyDown[3]) {
                    tempObject.setVelX(-1 * speed);
                    tempObject.setVelY(0);
                }
                //3
                if (!keyDown[0] && !keyDown[1] && !keyDown[2] && keyDown[3]) {
                    tempObject.setVelX(speed);
                    tempObject.setVelY(0);
                }


            }
        }
    }
}
