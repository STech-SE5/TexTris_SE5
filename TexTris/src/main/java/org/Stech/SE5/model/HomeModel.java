package org.Stech.SE5.model;

/*
    1) HomeModel에 필요한 요소 확정
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

    // HomeView에서 구현한 KeyListener 클래스의 기능을, 각 성격에 맞게 HomeModel과 HomeController로 분할
    /*public final void moveUP() {
        // 위로 이동할 때, 위에 버튼이 있다면 해당 버튼을 선택 (선택 후 강조)
        // 위에 버튼이 없다면 (ArrayList<JButton>)buttonList 내의 가장 마지막 버튼으로 이동
    }

    public final void moveDown() {
        // 아래로 이동할 때, 아래에 버튼이 있다면 해당 버튼을 선택 (선택 후 강조)
        // 아래에 버튼이 없다면 (ArrayList<JButton>)buttonList 내의 가장 처음 버튼으로 이동
    }

    public final void click() {
        // VK_ENTER를 눌렀을 때 보여야 할 반응
        // Selectview나 HomeView를 setVisible(false) 설정
        // Selectview에서 난이도 버튼을 눌렀다면, 해당 인자를 넘겨준 뒤 gameController에 통제권을 일임
        // HomeView에서 게임 모드 버튼을 눌렀다면, SelectView를 호출
        // HomeView에서 설정 버튼이나 스코어보드 버튼을 눌렀다면 recordController에 통제권을 일임
        // HomeView에서 종료 버튼을 눌렀다면, 게임을 아예 종료
    }*/

}