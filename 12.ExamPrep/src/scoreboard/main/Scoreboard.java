package scoreboard.main;

import java.util.*;

public class Scoreboard {

    private Map<String, String> usernamePass;
    private SortedMap<String, String> gamePass;
    private Map<String, SortedSet<ScoreboardEntry>> gameResults;

    public Scoreboard() {
        this.usernamePass = new HashMap<>();
        this.gamePass = new TreeMap<>();
        this.gameResults = new HashMap<>();
    }

    public boolean registerUser(String username, String password) {
        if (this.usernamePass.containsKey(username)) {
            return false;
        }
        this.usernamePass.put(username, password);
        return true;
    }

    public boolean registerGame(String game, String password) {
        if (this.gamePass.containsKey(game)) {
            return false;
        }
        this.gamePass.put(game, password);
        this.gameResults.put(game, new TreeSet<>());
        return true;
    }

    public boolean addScore(String username, String userPassword, String game, String gamePassword, int score) {
        if (!this.gamePass.containsKey(game) || !this.usernamePass.containsKey(username) ||
                !this.usernamePass.get(username).equals(userPassword) ||
                !this.gamePass.get(game).equals(gamePassword)) {
            return false;
        }

        this.gameResults.get(game).add(new ScoreboardEntry(username, score));
        return true;
    }

    public Iterable<ScoreboardEntry> showScoreboard(String game) {
        if (!this.gamePass.containsKey(game)) {
            return null;
        }
        List<ScoreboardEntry> top10 = new ArrayList<>();
        for (ScoreboardEntry se : this.gameResults.get(game)) {
            top10.add(se);
            if (top10.size() == 10) {
                break;
            }
        }
        return top10;
    }

    public boolean deleteGame(String game, String gamePassword) {
        if (!this.gamePass.containsKey(game) || !this.gamePass.get(game).equals(gamePassword)) {
            return false;
        }
        this.gamePass.remove(game);
        this.gameResults.remove(game);
        return true;
    }

    public Iterable<String> listGamesByPrefix(String gameNamePrefix) {
        Set<String> prefixFilter = this.gamePass.subMap(gameNamePrefix, gameNamePrefix + Character.MAX_VALUE).keySet();
        List<String> top10 = new ArrayList<>();
        for (String game : prefixFilter) {
            top10.add(game);
            if (top10.size() == 10) {
                break;
            }
        }
        return top10;
    }
}
