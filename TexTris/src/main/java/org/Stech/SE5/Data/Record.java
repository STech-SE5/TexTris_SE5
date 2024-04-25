package org.Stech.SE5.Data;

public class Record implements Comparable<Record> {
    public int id;
    public int score;
    public int deletedLine;
    public int gameMode;
    public int gameDifficulty;
    public String createdAt;
    public String name;

    public Record(int id, int score, int deletedLine, int gameMode, int gameDifficulty, String createdAt, String name) {
        this.id = id;
        this.score = score;
        this.deletedLine = deletedLine;
        this.gameMode = gameMode;
        this.gameDifficulty = gameDifficulty;
        this.createdAt = createdAt;
        this.name = name;
    }

    @Override
    public int compareTo(Record o) {
        if (o.score > score) {
            return 1;
        } else if (o.score < score) {
            return -1;
        }
        return 0;
    }
}