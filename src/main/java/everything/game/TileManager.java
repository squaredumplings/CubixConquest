package everything.game;

import java.awt.Graphics2D;
import java.awt.Point;

import everything.top.Config;

public class TileManager {

    

    public TileManager() {
        
    }

    // Draws tiles
    public void draw(Graphics2D g2d, Point playerPos) {

        // Top left corner of map where it starts beeing drawn relative to screen
        Point drawStart = new Point(Config.WINDOWWIDTH / 2 -playerPos.x, Config.WINDOWHEIGHT / 2 -playerPos.y);
        
        // Rendering limits for the tiles wich are on screen
        int screenLeftTiles = (playerPos.x - Config.WINDOWWIDTH / 2) / Config.TILESIZE;
        int screenRightTiles = (playerPos.x + Config.WINDOWWIDTH / 2) / Config.TILESIZE + 1;
        int screenDownTiles = (playerPos.y + Config.WINDOWHEIGHT / 2) / Config.TILESIZE + 1;
        int screenUpTiles = (playerPos.y - Config.WINDOWHEIGHT / 2) / Config.TILESIZE;

        // Making sure limits don't go out of bounds
        screenLeftTiles = Math.max(0, screenLeftTiles);
        screenUpTiles = Math.max(0, screenUpTiles);
        screenRightTiles = Math.min(screenRightTiles, Config.MAPSIZE);
        screenDownTiles = Math.min(screenDownTiles, Config.MAPSIZE);
        
        // Draw only the tiles wich are on screen
        for (int i = screenUpTiles; i < screenDownTiles; i++) {
            for (int j = screenLeftTiles; j < screenRightTiles; j++) {
                int type = Config.MAP[i][j];
                g2d.drawImage(Config.tiles[type].image, drawStart.x + j * Config.TILESIZE, 
                drawStart.y + i * Config.TILESIZE, Config.TILESIZE, Config.TILESIZE, null);
            }
        }
    }
}
