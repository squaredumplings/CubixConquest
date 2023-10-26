package everything.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import java.io.IOException;

import javax.imageio.ImageIO;

import everything.game.Collision;
import everything.game.KeyHandler;
import everything.top.Config;

public class Player extends Entity {
    // where to draw player relative to screen
    public final int screenX = Config.WINDOWWIDTH / 2 - Config.TILESIZE / 2; 
    public final int screenY = Config.WINDOWHEIGHT / 2 - Config.TILESIZE / 2;

    public Player() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = Config.TILESIZE * 6 + 43;
        worldY = Config.TILESIZE * 6 + 43;
        speed = 5;
        direction = "bottom"; 
        solidArea = new Rectangle(-20, 0, 40, 30);
    }

    public void update(KeyHandler keyHandler) {

        if (keyHandler.upPressed) {
            direction = "top";
            for (int i = 0; i < speed && !Collision.coll(worldX, worldY - 1, solidArea); i++) {
                worldY -= 1;
            }
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            for (int i = 0; i < speed && !Collision.coll(worldX - 1, worldY, solidArea); i++) {
                worldX -= 1;
            }
        }
        if (keyHandler.downPressed) {
            direction = "bottom";
            for (int i = 0; i < speed && !Collision.coll(worldX, worldY + 1, solidArea); i++) {
                worldY += 1;
            }
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            for (int i = 0; i < speed && !Collision.coll(worldX + 1, worldY, solidArea); i++) {
                worldX += 1;
            }
        }

        if (keyHandler.upPressed && keyHandler.leftPressed) {
            direction = "topleft";
        }
        if (keyHandler.leftPressed && keyHandler.downPressed) {
            direction = "bottomleft";
        }
        if (keyHandler.downPressed && keyHandler.rightPressed) {
            direction = "bottomright";
        }
        if (keyHandler.rightPressed && keyHandler.upPressed) {
            direction = "topright";
        }
    }

    public void draw(Graphics2D g2d) {
        try {
            BufferedImage image = null;
            image = ImageIO.read(getClass().getResourceAsStream("/Player.png"));
            g2d.drawImage(image, screenX, screenY, Config.TILESIZE, Config.TILESIZE, null); 

            int centerx = Config.WINDOWWIDTH / 2;
            int centery = Config.WINDOWHEIGHT / 2;
            g2d.drawRect(centerx + solidArea.x, centery + solidArea.y, 
                solidArea.width, solidArea.height);
            g2d.setColor(Color.red);
            g2d.drawRect(centerx, centery, 1, 1);
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
