package everything.entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import everything.top.Config;

public class EnemyManager {
    private ArrayList<Enemy> enemies;
    Player player;

    private Point enemySpawn;
    Random rand;
    long lastSpawnTime;

    public EnemyManager(Player p) {
        enemies = new ArrayList<Enemy>();
        player = p;
        enemySpawn = new Point(50 * Config.TILESIZE, 45 * Config.TILESIZE);
        rand = new Random(39899324);
        lastSpawnTime = System.currentTimeMillis();
    }

    private void spawnEnemy() {
        int randx = rand.nextInt(4 * Config.TILESIZE);
        int randy = rand.nextInt(4 * Config.TILESIZE);

        enemies.add(new Enemy(enemySpawn.x + randx, enemySpawn.y + randy, player));
    }

    public void updateEnemies() {
        // spawn enemies
        if (System.currentTimeMillis() - lastSpawnTime > 5000 && enemies.size() < Config.MAXENT) {
            spawnEnemy();
            lastSpawnTime = System.currentTimeMillis();
        }

        // update
        for (Enemy e : enemies){
            e.update();
        }

        // delete dead enemies
        enemies.removeIf(e -> e.health<=0);
    }

    public void drawEnemies(Graphics2D g2d) {
        for (Enemy e : enemies) {
            e.draw(g2d);
        }
    }

}
