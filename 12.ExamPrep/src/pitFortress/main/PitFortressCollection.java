package pitFortress.main;

import pitFortress.main.interfaces.IPitFortress;
import pitFortress.main.models.Mine;
import pitFortress.main.models.Minion;
import pitFortress.main.models.Player;

public class PitFortressCollection implements IPitFortress {

    @Override
    public int getPlayerCount() {
        return 0;
    }

    @Override
    public int getMinionCount() {
        return 0;
    }

    @Override
    public int getMineCount() {
        return 0;
    }

    @Override
    public void addPlayer(String name, int mineRadius) {

    }

    @Override
    public void addMinion(int xCoordinate) {

    }

    @Override
    public void setMine(String playerName, int xCoordinate, int delay, int damage) {

    }

    @Override
    public Iterable<Minion> reportMinions() {
        return null;
    }

    @Override
    public Iterable<Player> top3PlayersByScore() {
        return null;
    }

    @Override
    public Iterable<Player> min3PlayersByScore() {
        return null;
    }

    @Override
    public Iterable<Mine> getMines() {
        return null;
    }

    @Override
    public void playTurn() {

    }
}
