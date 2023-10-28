package everything.game;

import java.awt.Point;

import everything.entities.HitBox;
import everything.top.Config;

public class Collision {

    // corners of the hitbox
    static Point topLeft;
    static Point topRight;
    static Point bottomLeft;
    static Point bottomRight;

    // the tile on which the corners sit on
    static Tile tileTL;
    static Tile tileTR;
    static Tile tileBL;
    static Tile tileBR;

    private static void calculateCornerTiles(int x, int y, HitBox box) { 
        int size = Config.TILESIZE;

        topLeft = new Point(x + box.x, y + box.y);
        topRight = new Point(x + box.x + box.w, y + box.y);
        bottomLeft = new Point(x + box.x, y + box.y + box.h);
        bottomRight = new Point(x + box.x + box.w, y + box.y + box.h);

        tileTL = Config.tileTypes[Config.MAP[topLeft.y / size][topLeft.x / size]];
        tileTR = Config.tileTypes[Config.MAP[topRight.y / size][topRight.x / size]];
        tileBL = Config.tileTypes[Config.MAP[bottomRight.y / size][bottomRight.x / size]];
        tileBR = Config.tileTypes[Config.MAP[bottomLeft.y / size][bottomLeft.x / size]];
    }

    public static boolean tileCol(int x, int y, HitBox box) {
        calculateCornerTiles(x, y, box);

        if (tileTL.collision || tileTR.collision || tileBL.collision || tileBR.collision) {
            return true;
        }
        return false;
    }

    public static boolean mapCol(int x, int y, HitBox box) {
        if ((x + box.x < 0) || (y + box.y < 0)) {
            return true;
        }

        if ((x + box.x + box.w >= Config.MAPSIZE * Config.TILESIZE - 1) 
            || (y + box.y + box.h  >= Config.MAPSIZE * Config.TILESIZE - 1)) {
            return true;
        }

        return false;
    }

    public static int touchSlow(int x, int y, HitBox box) {
        calculateCornerTiles(x, y, box);

        int intermediaire1 = Math.min(tileTL.slowdown, tileTR.slowdown);
        int intermediaire2 = Math.min(tileBL.slowdown, tileBR.slowdown);

        return Math.min(intermediaire1, intermediaire2);
    }

    public static int touchDamage(int x, int y, HitBox box) {
        calculateCornerTiles(x, y, box);

        int intermediaire1 = Math.max(tileTL.damage, tileTR.damage);
        int intermediaire2 = Math.max(tileBL.damage, tileBR.damage);

        return Math.max(intermediaire1, intermediaire2);
    }
}
