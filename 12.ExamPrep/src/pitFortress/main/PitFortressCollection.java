package pitFortress.main;

import pitFortress.main.interfaces.IPitFortress;
import pitFortress.main.models.Mine;
import pitFortress.main.models.Minion;
import pitFortress.main.models.Player;

import java.util.*;

public class PitFortressCollection implements IPitFortress {

    private Map<String, Player> playersByName;
    private SortedMap<Integer, SortedSet<Minion>> xCoordinateMinions;
    private SortedSet<Player> playersByScoreDesc;
    private SortedSet<Player> playersByScoreAsc;
    private SortedSet<Minion> minionsByX;
    private SortedSet<Mine> minesByDelay;

    public PitFortressCollection() {
        Mine.count = 0;
        Minion.count = 0;
        this.playersByName = new HashMap<>();
        this.xCoordinateMinions = new TreeMap<>();
        this.playersByScoreDesc = new TreeSet<>(Comparator.reverseOrder());
        this.playersByScoreAsc = new TreeSet<>();
        this.minionsByX = new TreeSet<>();
        this.minesByDelay = new TreeSet<>();
    }

    @Override
    public int getPlayerCount() {
        return this.playersByScoreAsc.size();
    }

    @Override
    public int getMinionCount() {
        return this.minionsByX.size();
    }

    @Override
    public int getMineCount() {
        return this.minesByDelay.size();
    }

    @Override
    public void addPlayer(String name, int mineRadius) {
        if (this.playersByName.containsKey(name) || mineRadius < 0) {
            throw new IllegalArgumentException();
        }

        Player player = new Player(name, mineRadius);
        this.playersByName.put(name, player);
        this.playersByScoreDesc.add(player);
        this.playersByScoreAsc.add(player);
    }

    @Override
    public void addMinion(int xCoordinate) {
        if (xCoordinate < 0 || xCoordinate > 1000000) {
            throw new IllegalArgumentException();
        }

        Minion minion = new Minion(xCoordinate);

        this.xCoordinateMinions.computeIfAbsent(xCoordinate, k -> new TreeSet<>());
        this.xCoordinateMinions.get(xCoordinate).add(minion);
        this.minionsByX.add(minion);
    }

    @Override
    public void setMine(String playerName, int xCoordinate, int delay, int damage) {
        if (this.playersByName.get(playerName) == null || xCoordinate < 0 || xCoordinate > 1000000
                || damage < 0 || damage > 100) {
            throw new IllegalArgumentException();
        }

        Mine mine = new Mine(xCoordinate, delay, damage, this.playersByName.get(playerName));
        this.minesByDelay.add(mine);
    }

    @Override
    public Iterable<Minion> reportMinions() {
        return this.minionsByX;
    }

    @Override
    public Iterable<Player> top3PlayersByScore() {
        if (this.playersByName.size() < 3) {
            throw new IllegalArgumentException();
        }
        List<Player> top3 = new ArrayList<>();
        for (Player player : this.playersByScoreDesc) {
            top3.add(player);
            if (top3.size() == 3) {
                break;
            }
        }
        return top3;
    }

    @Override
    public Iterable<Player> min3PlayersByScore() {
        if (this.playersByName.size() < 3) {
            throw new IllegalArgumentException();
        }
        List<Player> last3 = new ArrayList<>();
        for (Player player : this.playersByScoreAsc) {
            last3.add(player);
            if (last3.size() == 3) {
                break;
            }
        }
        return last3;
    }

    @Override
    public Iterable<Mine> getMines() {
        return this.minesByDelay;
    }

    @Override
    public void playTurn() {

        List<Mine> removedMines = new LinkedList<>();
        List<Minion> removedMinions = new LinkedList<>();

        for (Mine mine : this.minesByDelay) {
            mine.updateDelay();

            if (mine.getDelay() == 0) {
                Map<Integer, SortedSet<Minion>> injuredMinions = (this.xCoordinateMinions.subMap(mine.getX() - mine.getPlayer().getRadius(), mine.getX() + mine.getPlayer().getRadius() + 1));

                for (SortedSet<Minion> minionsSet : injuredMinions.values()) {
                    for (Minion minion : minionsSet) {
                        minion.decreaseHealth(mine.getDamage());

                        if (minion.getHealth() <= 0 && !removedMinions.contains(minion)) {
                            removedMinions.add(minion);

                            this.playersByScoreAsc.remove(mine.getPlayer());
                            this.playersByScoreDesc.remove(mine.getPlayer());
                            Player player = mine.getPlayer();
                            player.addScore();
                            this.playersByScoreAsc.add(player);
                            this.playersByScoreDesc.add(player);
                            this.minionsByX.remove(minion);
                        }
                    }
                }

                removedMines.add(mine);
            }
        }
        for (Mine m : removedMines) {
            this.minesByDelay.remove(m);
        }
        for (Minion min : removedMinions) {
            this.xCoordinateMinions.get(min.getX()).remove(min);
        }
    }
}
