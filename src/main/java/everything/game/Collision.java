package everything.game;

import java.awt.Point;
import java.awt.Rectangle;

import everything.top.Config;

public class Collision {

    // needs to be generalized
    public static boolean tileCol(int x, int y, Rectangle box) {
        int size = Config.TILESIZE;
        
        Point topLeft = new Point(x + box.x, y + box.y);
        Point topRight = new Point(x + box.x + box.width, y + box.y);
        Point bottomLeft = new Point(x + box.x, y + box.y + box.height);
        Point bottomRight = new Point(x + box.x + box.width, y + box.y + box.height);

        int tileTL = Config.MAP[topLeft.y / size][topLeft.x / size];
        int tileTR = Config.MAP[topRight.y / size][topRight.x / size];
        int tileBL = Config.MAP[bottomRight.y / size][bottomRight.x / size];
        int tileBR = Config.MAP[bottomLeft.y / size][bottomLeft.x / size];

        if (false) {
            return true;
        }

        return false;
    }

    public static boolean mapCol(int x, int y, Rectangle box) {

        if ((x + box.x < 0) || (y + box.y < 0)) {
            return true;
        }

        if ((x + box.x + box.width >= Config.MAPSIZE * Config.TILESIZE - 1) 
            || (y + box.y + box.height  >= Config.MAPSIZE * Config.TILESIZE - 1)) {
            return true;
        }

        return false;
    }
}
