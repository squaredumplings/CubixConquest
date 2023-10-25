package everything.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import java.io.IOException;

import javax.imageio.ImageIO;

import everything.game.CollisionChecker;
import everything.game.KeyHandler;
import everything.top.Config;
import everything.top.Debug;

public class Player extends Entity {
    // where to draw player relative to screen (center)
    public final int screenX = Config.WINDOWWIDTH / 2 - Config.TILESIZE / 2; 
    public final int screenY = Config.WINDOWHEIGHT / 2 - Config.TILESIZE / 2;

    public Player() {
        setDefaultValues();
    }

    public void setDefaultValues() {
        worldX = Config.TILESIZE * 6;
        worldY = Config.TILESIZE * 6;
        speed = 20;
        direction = "bottom"; 
        solidArea = new Rectangle(-20, 0, 40, 30);
    }

    public void update(KeyHandler keyHandler) {
        String olddirection = direction;

        if (keyHandler.upPressed) {
            direction = "top";
            if (!CollisionChecker.collision(worldX, worldY - speed, solidArea)) {
                worldY -= speed;
            }
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            if (!CollisionChecker.collision(worldX - speed, worldY, solidArea)) {
                worldX -= speed;
            }
        }
        if (keyHandler.downPressed) {
            direction = "bottom";
            if (!CollisionChecker.collision(worldX, worldY + speed, solidArea)) {
                worldY += speed;
            }
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            if (!CollisionChecker.collision(worldX + speed, worldY, solidArea)) {
                worldX += speed;
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

        if (!olddirection.equals(direction)) {
            Debug.log(direction);
        }
    }

    public void draw(Graphics2D g2d) {
        try {
            BufferedImage image = null;
            image = ImageIO.read(getClass().getResourceAsStream("/Player.png"));
            g2d.drawImage(image, screenX, screenY, Config.TILESIZE, Config.TILESIZE, null); 
            
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
