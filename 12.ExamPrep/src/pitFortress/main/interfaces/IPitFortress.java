package pitFortress.main.interfaces;

import pitFortress.main.models.Mine;
import pitFortress.main.models.Minion;
import pitFortress.main.models.Player;

public interface IPitFortress {

    int getPlayerCount();

    int getMinionCount();

    int getMineCount();

    void addPlayer(String name, int mineRadius);

    void addMinion(int xCoordinate);

    void setMine(String playerName, int xCoordinate, int delay, int damage);

    Iterable<Minion> reportMinions();

    Iterable<Player> top3PlayersByScore();

    Iterable<Player> min3PlayersByScore();

    Iterable<Mine> getMines();

    void playTurn();
}
