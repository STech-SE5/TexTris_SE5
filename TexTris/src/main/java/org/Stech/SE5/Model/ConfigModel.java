package org.Stech.SE5.Model;

import java.awt.event.KeyEvent;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigModel {
    public enum GameMode {
        BASIC(1), ITEM(0.5);

        double rate;

        GameMode(double rate) {
            this.rate = rate;
        }
    }

    public enum GameDifficulty {
        EASY(0.5), NORMAL(1.0), HARD(1.5);

        double rate;

        GameDifficulty(double rate) {
            this.rate = rate;
        }
    }

    public enum GameBattleMode {
        TIME, NORMAL, ITEM
    }

    public enum BoardSize {
        SMALL(8, 20), MEDIUM(10, 20), LARGE(12, 20);

        int width;
        int height;

        BoardSize(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, ESC, UNDEFINED
    }

    public static GameMode gameMode = GameMode.BASIC;
    public static GameDifficulty gameDifficulty = GameDifficulty.NORMAL;
    public static GameBattleMode gameBattleMode = GameBattleMode.NORMAL;
    public static BoardSize boardSize = BoardSize.MEDIUM;
    public static int boardWidth = 10;
    public static int boardHeight = 20;
    public static double gameSpeed = 1;
    public static boolean colorBlindMode = false;
    public static boolean isTimeAttackMode = false;
    public static int[] keyBinding = {
            KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, 0
    };

    private ConfigModel() {

    }

    public static PlayerKey getPlayerKey(final KeyEvent e) {
        PlayerKey[] values = PlayerKey.values();
        for (int i = 0; i < values.length; i++) {
            if (keyBinding[i] == e.getKeyCode()) {
                return values[i];
            }
        }
        return PlayerKey.UNDEFINED;
    }

    public static void changeGameMode(GameMode g) {
        gameMode = g;
        saveConfig();
    }

    public static void changeGameDifficulty(GameDifficulty d) {
        gameDifficulty = d;
        saveConfig();
    }

    public static void changeBattleMode(GameBattleMode b) {
        gameBattleMode = b;
    }

    public static void changeBoardSize(BoardSize b) {
        boardWidth = b.width;
        boardHeight = b.height;
        boardSize = b;
        saveConfig();
    }

    public static void changeGameSpeed(double g) {
        gameSpeed = g;
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

    public static void initConfig() {
        gameMode = GameMode.BASIC;
        gameDifficulty = GameDifficulty.NORMAL;
        boardSize = BoardSize.MEDIUM;
        boardWidth = 10;
        boardHeight = 20;
        gameSpeed = 1;
        colorBlindMode = false;
        keyBinding = new int[]{
                KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
                KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, 0
        };
        saveConfig();
    }


    // public static void loadConfig() {
    static class ConfigFromJson {

        public GameMode GameMode;
        public GameDifficulty GameDifficulty;
        public BoardSize BoardSize;
        public int BoardWidth;
        public int BoardHeight;
        public double GameSpeed;
        public boolean ColorBlindMode;
        public int KeyBindingLength;
        public int moveBlockLeft;
        public int moveBlockRight;
        public int moveBlockDown;
        public int clockwiseRotate;
        public int dropBlockAtOnce;

    }

    // Load JSON file
    static ObjectMapper objectMapper = new ObjectMapper();
    private static final String path = "src/main/java/org/Stech/SE5/Model/data/config.json";

    public static void loadConfig() {
        try {
            ConfigFromJson config = objectMapper.readValue(new File(path), ConfigFromJson.class);

            boardWidth = config.BoardWidth;
            boardHeight = config.BoardHeight;
            gameSpeed = config.GameSpeed;
            colorBlindMode = config.ColorBlindMode;
            keyBinding = new int[]{
                    config.clockwiseRotate, config.moveBlockLeft, config.moveBlockRight,
                    config.moveBlockDown, config.dropBlockAtOnce
            };

            // Applying configuration values
//            System.out.println("Game Mode: " + config.GameMode);
//            System.out.println("Game Difficulty: " + config.GameDifficulty);
            System.out.println("Board Size: " + config.BoardSize);
            System.out.println("Board Width: " + config.BoardWidth);
            System.out.println("Board Height: " + config.BoardHeight);
            System.out.println("Game Speed: " + config.GameSpeed);
            System.out.println("Color Blind Mode: " + config.ColorBlindMode);
            System.out.println("Key Binding Length: " + config.KeyBindingLength);
            System.out.println("Move Block Left Key: " + config.moveBlockLeft);
            System.out.println("Move Block Right Key: " + config.moveBlockRight);
            System.out.println("Move Block Down Key: " + config.moveBlockDown);
            System.out.println("Clockwise Rotate Key: " + config.clockwiseRotate);
            System.out.println("Drop Block At Once Key: " + config.dropBlockAtOnce);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // saveConfig() overwrites current Setting of the game to the JSON file
    public static void saveConfig() {
        try {
            ConfigFromJson config = new ConfigFromJson();
            config.GameMode = gameMode;
            config.GameDifficulty = gameDifficulty;
            config.BoardSize = boardSize;
            config.BoardWidth = boardWidth;
            config.BoardHeight = boardHeight;
            config.GameSpeed = gameSpeed;
            config.ColorBlindMode = colorBlindMode;
            config.KeyBindingLength = keyBinding.length;
            config.clockwiseRotate = keyBinding[0];
            config.moveBlockLeft = keyBinding[1];
            config.moveBlockRight = keyBinding[2];
            config.moveBlockDown = keyBinding[3];
            config.dropBlockAtOnce = keyBinding[4];

            objectMapper.writeValue(new File(path), config);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public static double getScoreRate() {
        return gameSpeed * gameMode.rate * gameDifficulty.rate;
    }
}

