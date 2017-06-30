package pitFortress.main.models;

import pitFortress.main.interfaces.IMine;

public class Mine implements IMine {
    public static int count = 0;

    private Integer id;
    private Integer delay;
    private int x;
    private Player player;
    private int damage;

    public Mine(int x, int delay, int damage, Player player) {
        this.id = ++count;
        this.delay = delay;
        this.x = x;
        this.player = player;
        this.damage = damage;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int value) {
        this.delay = value;
    }

    public void updateDelay() {
        this.delay -= 1;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Mine o) {
        int cmp = this.delay.compareTo(o.getDelay());
        if(cmp == 0){
            cmp =  this.id.compareTo(o.getId());
        }
        return cmp;
    }
}
