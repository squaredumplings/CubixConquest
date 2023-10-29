package everything.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

import everything.game.Collision;
import everything.game.KeyHandler;
import everything.top.Config;

public class Player extends Entity {

    public Player() {
        worldPos = new Point(50 * Config.TILESIZE, 45 * Config.TILESIZE);
        lastDamageMilis  = System.currentTimeMillis();
        speed = 8;
        health = 100;
        direction = "bottom"; 
        solidArea = new HitBox(-25, -10, 50, 30);
    }

    public void update(KeyHandler keyHandler) {
        if (health <= 0){
            System.exit(3);
        }

        // speed after tile debuf
        int nowSpeed = 
            (int) (speed * Collision.touchSlow(worldPos.x, worldPos.y, solidArea) / 100.0);

        // take damage each second sitting on a bad tile
        if (System.currentTimeMillis() - lastDamageMilis > 200) {
            this.health -= Collision.touchDamage(worldPos.x, worldPos.y, solidArea);
            lastDamageMilis = System.currentTimeMillis();
        }

        if (keyHandler.upPressed) {
            direction = "top";
            for (int i = 0; i < nowSpeed
                && !Collision.tileCol(worldPos.x, worldPos.y - 1, solidArea)
                && !Collision.mapCol(worldPos.x, worldPos.y - 1, solidArea); i++) {
                worldPos.y -= 1;
            }
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            for (int i = 0; i < nowSpeed
                && !Collision.tileCol(worldPos.x - 1, worldPos.y, solidArea) 
                && !Collision.mapCol(worldPos.x - 1, worldPos.y, solidArea); i++) {
                worldPos.x -= 1;
            }
        }
        if (keyHandler.downPressed) {
            direction = "bottom";
            for (int i = 0; i < nowSpeed 
                && !Collision.tileCol(worldPos.x, worldPos.y + 1, solidArea) 
                && !Collision.mapCol(worldPos.x, worldPos.y + 1, solidArea); i++) {
                worldPos.y += 1;
            }
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            for (int i = 0; i < nowSpeed
                && !Collision.tileCol(worldPos.x + 1, worldPos.y, solidArea) 
                && !Collision.mapCol(worldPos.x + 1, worldPos.y, solidArea); i++) {
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
        Point screenPos = new Point(Config.WINDOWWIDTH / 2, Config.WINDOWHEIGHT / 2);

        try {
            BufferedImage image = null;

            switch (direction) {
                case "top": image = ImageIO.read(getClass().getResourceAsStream("/playerT.png"));
                    break;
                case "topright": image = ImageIO.read(getClass().getResourceAsStream("/playerTR.png"));
                    break;
                case "right": image = ImageIO.read(getClass().getResourceAsStream("/playerBR.png"));
                    break;
                case "bottomright": image = ImageIO.read(getClass().getResourceAsStream("/playerBR.png"));
                    break;
                case "bottom": image = ImageIO.read(getClass().getResourceAsStream("/playerB.png"));
                    break;
                case "bottomleft": image = ImageIO.read(getClass().getResourceAsStream("/playerBL.png"));
                    break;
                case "left": image = ImageIO.read(getClass().getResourceAsStream("/playerBL.png"));
                    break;
                case "topleft": image = ImageIO.read(getClass().getResourceAsStream("/playerTL.png"));
                    break;
                default:
                    break;
            }
            
            g2d.drawImage(image, (Config.WINDOWWIDTH - Config.TILESIZE) / 2, 
                (Config.WINDOWHEIGHT - Config.TILESIZE) / 2, 
                Config.TILESIZE, Config.TILESIZE, null); 
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // hitbox
        // g2d.setColor(Color.black);
        // g2d.drawRect(screenPos.x + solidArea.x, screenPos.y + solidArea.y, 
        //     solidArea.w, solidArea.h);

        // center point
        // g2d.setColor(Color.red);
        // g2d.drawRect(screenPos.x, screenPos.y, 1, 1);

        // healthbar
        g2d.setColor(Color.black);
        g2d.fillRect(screenPos.x - 36, screenPos.y - 51, 72, 10);
        g2d.setColor(Color.red);
        g2d.fillRect(screenPos.x - 35, screenPos.y - 50, health * 7 / 10, 8);
    }
}
