package borka.com.mytargdb;


import java.util.Date;

public class Player {

    private int id;
    private String name;
    private String date;
    private int score;


    public Player() {
    }

    public Player(int id, String name, String date,int score) {
        this.id = id;
        this.name = name;
         this.date = date;
        this.score = score;
    }

    public Player(String name, String date,int score) {

        this.name = name;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
