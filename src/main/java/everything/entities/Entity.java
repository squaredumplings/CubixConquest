package everything.entities;

import java.awt.Point;

public class Entity {
    public Point worldPos;

    public long lastDamageMilis;

    public int maxHealth;

    public int health;

    public int speed;

    public String direction;

    public HitBox solidArea;
}
