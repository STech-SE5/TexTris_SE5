package org.Stech.SE5.Data;

public class Record implements Comparable<Record> {
    private int id;
    private int score;
    private int deletedLine;
    private int gameMode;
    private int gameDifficulty;
    private String createdAt;
    private String name;

    public Record(int id, int score, int deletedLine, int gameMode, int gameDifficulty, String createdAt, String name) {
        this.id = id;
        this.score = score;
        this.deletedLine = deletedLine;
        this.gameMode = gameMode;
        this.gameDifficulty = gameDifficulty;
        this.createdAt = createdAt;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getDeletedLine() {
        return deletedLine;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getGameDifficulty() {
        return gameDifficulty;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Record o) {
        return Integer.compare(o.score, score);
    }
}
