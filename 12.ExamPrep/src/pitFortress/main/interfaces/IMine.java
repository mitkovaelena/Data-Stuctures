package pitFortress.main.interfaces;

import pitFortress.main.models.Mine;
import pitFortress.main.models.Player;

public interface IMine extends Comparable<Mine> {

    int getId();

    int getDelay();

    int getDamage();

    int getX();

    Player getPlayer();
}
