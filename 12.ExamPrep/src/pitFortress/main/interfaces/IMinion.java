package pitFortress.main.interfaces;

import pitFortress.main.models.Minion;

public interface IMinion extends Comparable<Minion> {

    int getId();

    int getX();

    int getHealth();
}
