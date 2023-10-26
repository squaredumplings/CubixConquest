package everything.game;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import everything.top.Config;

public class TileManager {
    
    GamePanel gamePanel;
    private Tile[] tile;

    public TileManager(GamePanel gp) {
        gamePanel = gp;
        tile = new Tile[10];
        
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Stone.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Water.png"));
            tile[3].collision = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d) {
        int msize = Config.MAPSIZE;
        int tsize = Config.TILESIZE;

        int drawStartX = -gamePanel.player.worldX + Config.WINDOWWIDTH / 2;
        int drawStartY = -gamePanel.player.worldY + Config.WINDOWHEIGHT / 2;
        
        for (int i = 0; i < msize; i++) {
            for (int j = 0; j < msize; j++) {
                int type = Config.MAP[i][j];
                g2d.drawImage(tile[type].image, drawStartX + j * tsize, drawStartY + i * tsize, 
                    tsize, tsize, null);
            }
        }
    }
}
