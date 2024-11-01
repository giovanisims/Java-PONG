import java.io.Serializable;

class Player implements Serializable {
    String name;
    int highscore;

    public Player(String name, int highscore) {
        this.name = name;
        this.highscore = highscore;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', highscore=" + highscore + "}";
    }
}