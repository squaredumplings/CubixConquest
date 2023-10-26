package everything.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import java.io.IOException;

import javax.imageio.ImageIO;

import everything.game.Collision;
import everything.game.KeyHandler;
import everything.top.Config;

public class Player extends Entity {
    // where to draw player relative to screen

    public Player() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        worldPos = new Point(Config.TILESIZE * 6, Config.TILESIZE * 6);
        speed = 10;
        direction = "bottom"; 
        solidArea = new Rectangle(-20, 0, 40, 30);
    }

    public void update(KeyHandler keyHandler) {

        if (keyHandler.upPressed) {
            direction = "top";
            for (int i = 0; i < speed && !Collision.tileCol(worldPos.x, worldPos.y - 1, solidArea)
                && !Collision.mapCol(worldPos.x, worldPos.y - 1, solidArea); i++) {
                worldPos.y -= 1;
            }
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            for (int i = 0; i < speed && !Collision.tileCol(worldPos.x - 1, worldPos.y, solidArea) && !Collision.mapCol(worldPos.x - 1, worldPos.y, solidArea); i++) {
                worldPos.x -= 1;
            }
        }
        if (keyHandler.downPressed) {
            direction = "bottom";
            for (int i = 0; i < speed && !Collision.tileCol(worldPos.x, worldPos.y + 1, solidArea) && !Collision.mapCol(worldPos.x, worldPos.y + 1, solidArea); i++) {
                worldPos.y += 1;
            }
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            for (int i = 0; i < speed && !Collision.tileCol(worldPos.x + 1, worldPos.y, solidArea) && !Collision.mapCol(worldPos.x + 1, worldPos.y, solidArea); i++) {
                worldPos.x += 1;
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
            g2d.drawImage(image, (Config.WINDOWWIDTH - Config.TILESIZE) / 2, (Config.WINDOWHEIGHT - Config.TILESIZE) / 2, Config.TILESIZE, Config.TILESIZE, null); 

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
