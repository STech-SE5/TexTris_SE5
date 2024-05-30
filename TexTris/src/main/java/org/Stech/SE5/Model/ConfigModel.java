package org.Stech.SE5.Model;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigModel {
    public ConfigModel() {
        loadConfig();

    }

    // 게임 모드, 난이도, 창 크기, 게임 속도, 컬러블라인 모드, 키 바인딩 설정

    // 게임 모드
    public enum GameMode {
        BASIC(1), ITEM(0.5);

        double rate;

        GameMode(double rate) {
            this.rate = rate;
        }
    }

    // 난이도
    public enum GameDifficulty {
        EASY(0.5), NORMAL(1.0), HARD(1.5);

        double rate;

        GameDifficulty(double rate) {
            this.rate = rate;
        }
    }

    // 창 크기
    public enum BoardSize {
        SMALL(8, 20), MEDIUM(10, 20), LARGE(12, 20);

        int width;
        int height;

        BoardSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }


    // 초기화 - 게임 모드, 난이도, 보드 사이즈, 게임 속도, 컬러블라인 모드, 키 바인딩 초기 설정 값
    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.NORMAL;
    public static BoardSize boardSize = BoardSize.MEDIUM;
    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static double gameSpeed = 1;
    public static boolean colorBlindMode = false;

    // Config 파일 경로 - loadConfig(), saveConfig() 메소드에서 사용
    public static String path = "saved-config/config.txt";

    // Update Keyboard Part
    private PlayerKey lastKey = PlayerKey.UNDEFINED;

    // Default key bindings:
    public static int[] keyBinding = {KeyEvent.VK_UP, // ROTATE
            KeyEvent.VK_LEFT, // LEFT
            KeyEvent.VK_RIGHT, // RIGHT
            KeyEvent.VK_DOWN, // DOWN
            KeyEvent.VK_SHIFT, // DROP
            KeyEvent.VK_W, // ROTATE_2P
            KeyEvent.VK_A, // LEFT_2P
            KeyEvent.VK_D, // RIGHT_2P
            KeyEvent.VK_S, // DOWN_2P
            KeyEvent.VK_SPACE, // DROP_2P
            KeyEvent.VK_ESCAPE // ESC
            , 0
    };
    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, ROTATE_2P, LEFT_2P, RIGHT_2P, DOWN_2P, DROP_2P, ESC, UNDEFINED;

        // Method to get PlayerKey based on KeyEvent
        public static PlayerKey getPlayerKey(final KeyEvent e) {
            for (int i = 0; i < keyBinding.length; i++) {
                if (keyBinding[i] == e.getKeyCode()) {
                    return values()[i];
                }
            }
            return PlayerKey.UNDEFINED;
        }
    }

    public static void changeBoardSize(BoardSize b) {
        boardWidth = b.width;
        boardHeight = b.height;
        boardSize = b;
        saveConfig();
    }

    public static void changeColorBlindMode(boolean g) {
        colorBlindMode = g;
        saveConfig();
    }

    public static void changeKeyBinding(PlayerKey playerKey, KeyEvent e) {
        keyBinding[playerKey.ordinal()] = e.getKeyCode();
        saveConfig();
    }

    // Config 초기화 - 게임 모드, 난이도, 보드 사이즈, 게임 속도, 컬러블라인 모드, 키 바인딩 초기 설정으로 초기화
    public static void initConfig() {
        gameMode = GameMode.BASIC;
        gameDifficulty = GameDifficulty.NORMAL;
        boardSize = BoardSize.MEDIUM;
        boardWidth = 10;
        boardHeight = 20;
        gameSpeed = 1;
        colorBlindMode = false;
        keyBinding = new int[]{
                KeyEvent.VK_UP, // ROTATE
                KeyEvent.VK_LEFT, // LEFT
                KeyEvent.VK_RIGHT, // RIGHT
                KeyEvent.VK_DOWN, // DOWN
                KeyEvent.VK_SHIFT, // DROP
                KeyEvent.VK_W, // ROTATE_2P
                KeyEvent.VK_A, // LEFT_2P
                KeyEvent.VK_D, // RIGHT_2P
                KeyEvent.VK_S, // DOWN_2P
                KeyEvent.VK_SPACE, // DROP_2P
                KeyEvent.VK_ESCAPE, 0};
        saveConfig();
    }

    // Config 설정 파일 저장
    public static void saveConfig() {
        BufferedWriter out = null;
        List<String> lisfOfKeyBindingStr = new ArrayList<>();
        for (Integer integer : keyBinding) {
            lisfOfKeyBindingStr.add(String.valueOf(integer));
        }

        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileWriter fStream = new FileWriter(f, false);
            out = new BufferedWriter(fStream);
            out.write(gameMode.name() + ",");
            out.write(gameDifficulty.name() + ",");
            out.write(boardSize.name() + ",");
            out.write(Integer.toString(boardWidth) + ",");
            out.write(Integer.toString(boardHeight) + ",");
            out.write(Double.toString(gameSpeed) + ",");
            out.write(Boolean.toString(colorBlindMode) + ",");
            out.write(Integer.toString(keyBinding.length) + ",");
            out.write(String.join(",", lisfOfKeyBindingStr));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Config 설정 파일 불러오기
    public static void loadConfig() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = bufReader.readLine();
            String[] configs = line.split(",");
            gameMode = Enum.valueOf(GameMode.class, configs[0]);
            gameDifficulty = Enum.valueOf(GameDifficulty.class, configs[1]);
            boardSize = Enum.valueOf(BoardSize.class, configs[2]);
            boardWidth = Integer.parseInt(configs[3]);
            boardHeight = Integer.parseInt(configs[4]);
            gameSpeed = Double.parseDouble(configs[5]);
            colorBlindMode = Boolean.parseBoolean(configs[6]);
            int keyBindingLength = Integer.parseInt(configs[7]);
            for (int i = 0; i < keyBindingLength; i++) {
                keyBinding[i] = Integer.parseInt(configs[8 + i]);
            }
            bufReader.close();
        } catch (IOException e) {
            System.out.println("저장된 환경설정이 없습니다.");
        }
    }
}
