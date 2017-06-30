package pitFortress.main.models;

import pitFortress.main.interfaces.IPlayer;

public class Player implements IPlayer {

    private int radius;
    private String name;
    private Integer score;

    public Player(String name,int radius) {
        this.radius = radius;
        this.name = name;
        this.score = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score += 1;
    }

    public void setScore(int value) {
        this.score = value;
    }

    @Override
    public int compareTo(Player o) {
        int cmp = this.score.compareTo(o.getScore());
        if(cmp == 0){
            cmp =  this.name.compareTo(o.getName());
        }
        return cmp;
    }
}
