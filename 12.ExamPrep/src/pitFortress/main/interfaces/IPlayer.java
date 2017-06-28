package pitFortress.main.interfaces;

import pitFortress.main.models.Player;

public interface IPlayer extends Comparable<Player> {

    String getName();

    int getRadius();

    int getScore();
}
