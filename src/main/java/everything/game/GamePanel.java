package everything.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import everything.entities.Player;
import everything.top.Config;

public class GamePanel extends JPanel implements Runnable {  

    TileManager tileManager = new TileManager();
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player();
    Collision colChecker = new Collision();
    

    int playerSpeed = 4;

    public GamePanel() {
        //pane settings
        this.setSize(Config.WINDOWWIDTH, Config.WINDOWHEIGHT);
        this.setBackground(new Color(100, 50, 50));
        this.setLayout(null);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setVisible(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();;
    }

    @Override
    public void run() {

        double drawInterval = 1e9 / Config.FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1e6;

                // to avoid negatives
                remainingTime = Math.max(0, remainingTime);

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (keyHandler.endGamePressed) {
            System.exit(43);
        }

        player.update(keyHandler);      
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.draw(g2d, player.worldPos);

        player.draw(g2d);

        g2d.dispose();
    }
}
