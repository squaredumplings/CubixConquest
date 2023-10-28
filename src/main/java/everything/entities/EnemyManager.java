package everything.entities;

import java.awt.Graphics2D;
import java.awt.Point;

import java.util.Random;

import everything.top.Config;

public class EnemyManager {
    private Enemy[] enemies = new Enemy[Config.MAXENT];
    Player player;

    private Point enemySpawn;
    private int nrOfEnemies;
    Random rand;
    long lastSpawnTime;

    public EnemyManager(Player p) {
        player = p;
        enemySpawn = new Point(20 * Config.TILESIZE, 20 * Config.TILESIZE);
        nrOfEnemies = 0;
        rand = new Random(39899324);
        lastSpawnTime = System.currentTimeMillis();
    }

    private void spawnEnemy() {
        int randx = rand.nextInt(4 * Config.TILESIZE);
        int randy = rand.nextInt(4 * Config.TILESIZE);

        enemies[nrOfEnemies] = new Enemy(enemySpawn.x + randx, enemySpawn.y + randy, player);
        nrOfEnemies++;
    }

    public void updateEnemies() {
        if (System.currentTimeMillis() - lastSpawnTime > 3000 && nrOfEnemies < Config.MAXENT) {
            spawnEnemy();
            lastSpawnTime = System.currentTimeMillis();
        }

        for (int i = 0; i < nrOfEnemies; i++) {
            enemies[i].update();
        }
    }

    public void drawEnemies(Graphics2D g2d) {
        for (int i = 0; i < nrOfEnemies; i++) {
            enemies[i].draw(g2d);
        }
    }

}
