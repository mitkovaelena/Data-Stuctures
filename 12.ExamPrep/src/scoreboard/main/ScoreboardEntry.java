package scoreboard.main;

public class ScoreboardEntry implements Comparable<ScoreboardEntry>
{
    private Integer score;
    private String userName;

    public ScoreboardEntry(String userName, Integer score) {
        this.score = score;
        this.userName = userName;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUserName() {
        return  this.userName;
    }

    public void setUsername (String userName) {
        this.userName = userName;
    }

    public int compareTo(ScoreboardEntry other) {
        int cmp = this.score.compareTo(other.getScore());
        if(cmp != 0){
            return cmp*(-1);
        }
        cmp =  this.userName.compareTo(other.getUserName());
        if(cmp != 0){
            return cmp;
        }
        return 1;
    }
}