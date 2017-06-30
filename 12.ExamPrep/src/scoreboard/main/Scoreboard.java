package scoreboard.main;

public class Scoreboard {

    public Scoreboard() {
    }

    public boolean registerUser(String username, String password) {
        throw new UnsupportedOperationException();
    }

    public boolean registerGame(String game, String password) {
        throw new UnsupportedOperationException();
    }

    public boolean addScore(String username, String userPassword, String game, String gamePassword, int score) {
        throw new UnsupportedOperationException();
    }

    public Iterable<ScoreboardEntry> showScoreboard(String game) {
        throw new UnsupportedOperationException();
    }

    public boolean deleteGame(String game, String gamePassword) {
        throw new UnsupportedOperationException();
    }

    public Iterable<String> listGamesByPrefix(String gameNamePrefix) {
        throw new UnsupportedOperationException();
    }
}
