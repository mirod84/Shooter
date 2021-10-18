import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);


        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(false);
        frame.setVisible(true);
        frame.add(game);

        /*if (gd.isFullScreenSupported()) {
            try {
                gd.setFullScreenWindow(frame);
                game.start(frame.getWidth(),frame.getHeight());
                validate();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } finally {
                gd.setFullScreenWindow(null);
                game.setScreenWidth(frame.getWidth());
                game.setScreenHeight(frame.getHeight());
            }
        } else {
            System.err.println("Full screen not supported");
            game.setScreenWidth(frame.getWidth());
            game.setScreenHeight(frame.getHeight());
        }*/

        game.start(frame.getWidth(),frame.getHeight());
        game.setScreenWidth(frame.getWidth());
        game.setScreenHeight(frame.getHeight());

    }




}
