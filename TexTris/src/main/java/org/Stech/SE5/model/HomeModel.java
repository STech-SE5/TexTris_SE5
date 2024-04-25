package org.Stech.SE5.model;

/*
    1) Home 화면에 필요한 요소만 남기고 삭제
    2) GameView, ConfigView와 상호작용할 요소 추가
    3) GameModel과 RecordModel 보고 참고할 것
 */

public class HomeModel {
    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.NORMAL;

    private HomeModel() { }
    public enum GameMode {
        BASIC(0), ITEM(1);

        double rate;

        GameMode(double rate) {
            this.rate = rate;
        }
    }

    public enum GameDifficulty {
        EASY(0), NORMAL(1), HARD(2);

        double rate;

        GameDifficulty(double rate) {
            this.rate = rate;
        }
    }

    public static void changeGameMode(GameMode g) { // 게임 모드 변경?
        gameMode = g;
        // saveConfig();
    }

    public static void changeGameDifficulty(GameDifficulty d) { // 게임 난이도 변경?
        gameDifficulty = d;
        // saveConfig();
    }

    public static void initConfig() {
        gameMode = GameMode.BASIC;
        gameDifficulty = GameDifficulty.NORMAL;
    }
}