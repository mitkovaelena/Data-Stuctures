package pitFortress.main.models;

import pitFortress.main.interfaces.IMinion;

public class Minion implements IMinion {
    public static int count = 0;

    private Integer id;
    private Integer x;
    private int health;

    public Minion(int x) {
        this.id = ++count;
        this.x = x;
        this.health = 100;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public int compareTo(Minion o) {
        int cmp = this.x.compareTo(o.getX());
        if(cmp == 0){
            cmp =  this.id.compareTo(o.getId());
        }
        return cmp;
    }

    public void decreaseHealth(int damage) {
        this.health -=damage;
    }
}
