package everything.game;

import java.awt.Point;
import java.awt.Rectangle;

import everything.top.Config;
import everything.top.Debug;

public class Collision {

    private static int frame10;
    
    public Collision() {
        frame10 = 0;
    }

    // needs to be generalized
    public static boolean coll(int x, int y, Rectangle box) {
        int size = Config.TILESIZE;
        frame10++;

        Point topLeft = new Point(x + box.x, y + box.y);
        Point topRight = new Point(x + box.x + box.width, y + box.y);
        Point bottomLeft = new Point(x + box.x, y + box.y + box.height);
        Point bottomRight = new Point(x + box.x + box.width, y + box.y + box.height);

        int tileTL = Config.MAP[topLeft.y / size][topLeft.x / size];
        int tileTR = Config.MAP[topRight.y / size][topRight.x / size];
        int tileBL = Config.MAP[bottomRight.y / size][bottomRight.x / size];
        int tileBR = Config.MAP[bottomLeft.y / size][bottomLeft.x / size];

        if (frame10 % 10 == 0) {
            Debug.log(tileTL + " " + tileTR + " " + tileBL + " " + tileBR);
        }

        if (tileBL == 2 || tileBR == 2 || tileTL == 2 || tileTR == 2) {
            
            return true;
        }
        return false;
    }
}
