package everything.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import everything.game.Collision;
import everything.top.Config;

public class Enemy extends Entity {

    private Player player;
    Point drawStart; // Top left corner where map starts beeing drawn
    
    class Vector {
        int x;
        int y;

        public Vector(int xaxis, int yaxis) {
            x = xaxis;
            y = yaxis;
        }

        public int mod() {
            int xsquared = x * x;
            int ysquared = y * y;
            return ((int) Math.sqrt(xsquared + ysquared));
        }
    }

    public Enemy(int x, int y, Player p) {
        player = p;
        worldPos = new Point(x, y);
        drawStart = new Point(0, 0);
        setDefaultValues();
    }

    private void setDefaultValues() {
        speed = 5;
        maxHealth = 100;
        lastDamageMilis  = System.currentTimeMillis();
        health = maxHealth;
        direction = "bottom"; 
        solidArea = new HitBox(-20, -20, 40, 40);
    }

    public void update() {
        // calculating the distance to player
        int xdistance = player.worldPos.x - this.worldPos.x;
        int ydistance = player.worldPos.y - this.worldPos.y;
        Vector distanceToPlayer = new Vector(xdistance, ydistance);

        // creating the speed components
        int multiFactor = distanceToPlayer.mod() / speed;
        int speedx = 0;
        int speedy = 0;
        if (multiFactor != 0 && distanceToPlayer.mod() > 200) {
            speedx = xdistance / multiFactor;
            speedy = ydistance / multiFactor;
        }

        // speed after tile debuf
        Point nowSpeed = new Point(
            (int) (speedx * Collision.touchSlow(worldPos.x, worldPos.y, solidArea) / 100.0),
            (int) (speedy * Collision.touchSlow(worldPos.x, worldPos.y, solidArea) / 100.0));

        // take damage each second sitting on a bad tile
        if (System.currentTimeMillis() - lastDamageMilis > 1000) {
            this.health -= Collision.touchDamage(worldPos.x, worldPos.y, solidArea);
            lastDamageMilis = System.currentTimeMillis();
        }

        if (speedx < 0) {
            for (int i = 0; i > nowSpeed.x 
                && !Collision.tileCol(worldPos.x - 1, worldPos.y, solidArea); i--) {
                worldPos.x--;
            }
        } else {
            for (int i = 0; i < nowSpeed.x 
                && !Collision.tileCol(worldPos.x + 1, worldPos.y, solidArea); i++) {
                worldPos.x++;
            }
        }

        if (speedy < 0) {
            for (int i = 0; i > nowSpeed.y 
                && !Collision.tileCol(worldPos.x, worldPos.y - 1, solidArea); i--) {
                worldPos.y--;
            }
        } else {
            for (int i = 0; i < nowSpeed.y 
                && !Collision.tileCol(worldPos.x, worldPos.y + 1, solidArea); i++) {
                worldPos.y++;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        drawStart = new Point(Config.WINDOWWIDTH / 2 - player.worldPos.x, 
            Config.WINDOWHEIGHT / 2 - player.worldPos.y);
        Point screenPos = new Point(drawStart.x + worldPos.x, drawStart.y + worldPos.y);

        // enemy
        g2d.setColor(Color.pink);
        g2d.fillRect(screenPos.x + solidArea.x, screenPos.y + solidArea.y,
            solidArea.w, solidArea.h);

        // hitbox
        g2d.setColor(Color.black);
        g2d.drawRect(screenPos.x + solidArea.x, screenPos.y + solidArea.y, 
            solidArea.w, solidArea.h);

        // center point
        g2d.setColor(Color.red);
        g2d.drawRect(screenPos.x, screenPos.y, 1, 1);

        // healthbar
        g2d.setColor(Color.black);
        g2d.fillRect(screenPos.x - 26, screenPos.y - 41, 52, 7);
        g2d.setColor(Color.red);
        g2d.fillRect(screenPos.x - 25, screenPos.y - 40, health / 2, 5);
    }
}
