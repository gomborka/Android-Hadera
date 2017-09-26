package borka.com.gameanddb;

/**
 * Created by java on 20/07/2017.
 */

public class Player {

    private int id;
    private String name;
    private String family;
    private String user;
    private String password;
    private String date;
    private int score;

    public Player(int id, String name, String family, String user, String password, String date, int score) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.user = user;
        this.password = password;
        this.date = date;
        this.score = score;
    }

    public Player() {
            }

    public Player(String name, String family, String user, String password, String date, int score) {
        this.name = name;
        this.family = family;
        this.user = user;
        this.password = password;
        this.date = date;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", date='" + date + '\'' +
                ", score=" + score +
                '}';
    }
}
