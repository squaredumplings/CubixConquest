package everything.game;

import java.awt.Point;
import java.awt.Rectangle;

import everything.top.Config;

public class CollisionChecker {
    
    public CollisionChecker() {

    }

    // needs to be generalized
    public static boolean collision(int x, int y, Rectangle box) {
        int size = Config.TILESIZE;

        Point topLeft = new Point(x + box.x, y + box.y);
        Point topRight = new Point(x + box.x + box.width, y + box.y);
        Point bottomLeft = new Point(x + box.x, y + box.y + box.height);
        Point bottomRight = new Point(x + box.x + box.width, y + box.y + box.height);

        int tileTL = Config.MAP[topLeft.x / size][topLeft.y / size];
        int tileTR = Config.MAP[topRight.x / size][topRight.y / size];
        int tileBL = Config.MAP[bottomRight.x / size][bottomRight.y / size];
        int tileBR = Config.MAP[bottomLeft.x / size][bottomLeft.y / size];

        //System.out.println(topLeft + " " + x + y);

        if (tileBL == 2 || tileBR == 2 || tileTL == 2 || tileTR == 2) {
            return true;
        }
        return false;
    }

    // public static void drawColision(int x, int y, Rectangle box, Graphics2D g2d) {
    //     Point topLeft = new Point(x, y);
    //     Point topRight = new Point(x + box.x + box.width, y + box.y);
    //     Point bottomLeft = new Point(x + box.x, y + box.y + box.height);
    //     Point bottomRight = new Point(x + box.x + box.width, y + box.y + box.height);
    //     g2d.setColor(Color.red);
    //     g2d.drawRect(topLeft.x, topLeft.y, 20, 20);
    // }

}
