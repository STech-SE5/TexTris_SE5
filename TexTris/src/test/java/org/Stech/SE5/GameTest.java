package org.Stech.SE5;

import org.Stech.SE5.Controller.*;
import org.Stech.SE5.Model.GameModel;
import org.Stech.SE5.Model.RecordModel;
import org.Stech.SE5.View.ConfigView;
import org.Stech.SE5.View.HomeView;
import org.Stech.SE5.View.RecordView;
import org.Stech.SE5.Data.Record;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.SelectView;
import org.junit.Test;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTest {

    @Test
    public void testSetRandomBlock() {
        GameController gameController = new GameController(false, 1);
        GameModel gameModel = new GameModel(gameController, false, 1);
        gameModel.setRandomBlock_test();

        assertNotNull(gameModel.getNextBlock());    //nextBlock 초기화 여부 확인
        assertNotNull(gameModel.getBoard());
    }

    @Test
    public void testBlocks_normal() {
        BattleController battleController = new BattleController(0);
        GameModel gameModel = new GameModel(battleController, 0, true);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++) {
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testBlocks_easy() {
        GameController gameController = new GameController(false, 0);
        GameModel gameModel = new GameModel(gameController, false, 0);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++) {
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testBlocks_hard() {
        GameController gameController = new GameController(false, 2);
        GameModel gameModel = new GameModel(gameController, false, 2);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++) {
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testItem() {
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        gameModel.setITEM_GENERATE_INTERVAL0();
        gameModel.setRandomBlock_test();
        int W = 0;
        int L = 0;
        int B = 0;
        int I = 0;
        int C = 0;
        for (int i = 0; i < 50; i++) {
            gameModel.setRandomBlock_test();
            gameModel.triggerItem();
            switch (gameModel.getNextBlock().getType()) {
                case WEIGHT_BLOCK:
                    W++;
                    break;
                case LINE_CLEANER:
                    L++;
                    break;
                case BOMB:
                    B++;
                    break;
                case ITEM_BOOST:
                    I++;
                    break;
                case CROSS_DELETE:
                    C++;
                    break;
            }
        }
        System.out.println("WEIGHT_BLOCK: " + W);
        System.out.println("LINE_CLEANER: " + L);
        System.out.println("BOMB: " + B);
        System.out.println("ITEM_BOOST: " + I);
        System.out.println("CROSS_DELETE: " + C);
    }

    @Test
    public void testModelValue_Single() {
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        assertEquals(1, gameModel.getMode());
        assertEquals(1, gameModel.getDiff());
        assertEquals(0, gameModel.getLineCounts());
        assertEquals(0, gameModel.getItemCount());
        double delta = 0.0001;
        assertEquals(1.01, gameModel.getGameSpeed(), delta);
        assertEquals(1.05, gameModel.getScorerate(), delta);
    }

    @Test
    public void testModelValue_Battle() {
        BattleController battleController = new BattleController(2);
        GameModel gameModel = new GameModel(battleController, 2, true);
        assertEquals(0, gameModel.getMode());   //item모드면 1, timer모드임
        assertEquals(0, gameModel.getLineCounts());
        assertEquals(180, battleController.seconds);
        double delta = 0.0001;
        assertEquals(1.01, gameModel.getGameSpeed(), delta);
        assertEquals(1.05, gameModel.getScorerate(), delta);
        assertEquals(gameModel.getCurrnetAttack(), 0);
    }

    @Test
    public void testweightBlockStart() {
        BattleController battleController = new BattleController(1);
        battleController.gameStart();
        assertTrue(battleController.P1MainTimerisRunning());
        assertTrue(battleController.P2MainTimerisRunning());
        battleController.weightBlockStart(true);
        assertFalse(battleController.P1MainTimerisRunning());
        assertTrue(battleController.P2MainTimerisRunning());
        battleController.weightBlockStart(false);
        assertFalse(battleController.P1MainTimerisRunning());
        assertFalse(battleController.P2MainTimerisRunning());
    }

    @Test
    public void testInitResolution() {
        // Given
        RecordController recordController = new RecordController();

        // When
        int width = recordController.getWIDTH();
        int height = recordController.getHEIGHT();

        // Then
        assertEquals(500, width); // 기본 해상도가 MEDIUM일 때 너비가 500인지 확인
        assertEquals(750, height); // 기본 해상도가 MEDIUM일 때 높이가 750인지 확인
    }

    @Test
    public void testInitResolutionMedium() {
        // Given
        RecordController recordController = new RecordController();
        ConfigModel.boardSize = ConfigModel.BoardSize.MEDIUM;

        // When
        recordController.initResolution();
        int width = recordController.getWIDTH();
        int height = recordController.getHEIGHT();

        // Then
        assertEquals(500, width); // 해상도가 MEDIUM일 때 너비가 500인지 확인
        assertEquals(750, height); // 해상도가 MEDIUM일 때 높이가 750인지 확인
    }

    @Test
    public void testGetsize() {
        // Given
        RecordController recordController = new RecordController();

        // When
        recordController.getsize(ConfigModel.BoardSize.LARGE);
        int resolutionLarge = recordController.getResolution();
        recordController.getsize(ConfigModel.BoardSize.MEDIUM);
        int resolutionMedium = recordController.getResolution();
        recordController.getsize(ConfigModel.BoardSize.SMALL);
        int resolutionSmall = recordController.getResolution();

        // Then
        assertEquals(2, resolutionLarge); // LARGE 설정 시 해상도가 2인지 확인
        assertEquals(1, resolutionMedium); // MEDIUM 설정 시 해상도가 1인지 확인
        assertEquals(0, resolutionSmall); // SMALL 설정 시 해상도가 0인지 확인
    }

    @Test
    public void testInitController() {
        // Given
        RecordController recordController = new RecordController();

        // When
        RecordView recordView = recordController.recordView;

        // Then
        assertNotNull(recordView); // RecordView가 초기화되었는지 확인
    }

    @Test
    public void testSetVisible() {
        // Given
        RecordController recordController = new RecordController();

        // When
        recordController.setVisible(true);

        // Then
        assertTrue(recordController.recordView.isVisible()); // setVisible(true) 호출 시 RecordView가 표시되는지 확인

        // When
        recordController.setVisible(false);

        // Then
        assertFalse(recordController.recordView.isVisible()); // setVisible(false) 호출 시 RecordView가 숨겨지는지 확인
    }

    @Test
    public void testGetRecords() {
        // Given
        RecordModel.initRecord();
        RecordController recordController = new RecordController();
        ArrayList<Record> records = new ArrayList<>();
        records.add(new Record(1, 100, 5, 1, 2, "2024-05-18", "TestUser"));
        records.add(new Record(2, 200, 6, 2, 3, "2024-05-19", "TestUser2"));
        RecordModel.rankedRecords = records;

        // When
        ArrayList<Record> retrievedRecords = recordController.getRecords();

        // Then
        assertEquals(records, retrievedRecords); // 가져온 레코드 리스트가 올바른지 확인
    }


    //RecordModelTest

    @Test
    public void testInitRecord() {
        // Given
        RecordModel.clearRecord();
        RecordModel.initRecord();

        // When
        int recordSize = RecordModel.getRankedRecords().size();

        // Then
        assertEquals(0, recordSize); // 초기화 후 레코드 크기가 0인지 확인
        // 저장된 레코드가 없으므로 파일에 아무것도 쓰여 있지 않은지도 확인 가능
    }

    @Test
    public void testAddRecord() {
        // Given
        RecordModel.clearRecord();
        RecordModel.initRecord();
        int score = 100;
        int deletedLine = 5;
        int gameMode = 1;
        int gameDifficulty = 2;
        String createdAt = "2024-05-18";
        String name = "TestUser";

        // When
        RecordModel.addRecord(score, deletedLine, gameMode, gameDifficulty, createdAt, name);
        int recordSize = RecordModel.getRankedRecords().size();

        // Then
        assertEquals(1, recordSize); // 레코드가 추가되었는지 확인
        // 추가적인 테스트 가능: 파일에 레코드가 저장되었는지, 추가된 레코드의 내용이 맞는지 등
    }

    @Test
    public void testSaveRecord() {
        // Given
        RecordModel.clearRecord();
        RecordModel.initRecord();
        int score = 100;
        int deletedLine = 5;
        int gameMode = 1;
        int gameDifficulty = 2;
        String createdAt = "2024-05-18";
        String name = "TestUser";
        RecordModel.addRecord(score, deletedLine, gameMode, gameDifficulty, createdAt, name);

        // When
        RecordModel.saveRecord();

        // Then
        File file = new File(RecordModel.RECORD_FILE_PATH);
        assertTrue(file.exists()); // 파일이 존재하는지 확인

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertNotNull(line); // 파일에 적어도 한 줄이 있는지 확인
            String[] record = line.split(",");
            assertEquals(100, Integer.parseInt(record[0])); // 저장된 레코드의 score가 올바른지 확인
            assertEquals(5, Integer.parseInt(record[1])); // 저장된 레코드의 deletedLine이 올바른지 확인
            assertEquals(1, Integer.parseInt(record[2])); // 저장된 레코드의 gameMode가 올바른지 확인
            assertEquals(2, Integer.parseInt(record[3])); // 저장된 레코드의 gameDifficulty가 올바른지 확인
            assertEquals("2024-05-18", record[4]); // 저장된 레코드의 createdAt이 올바른지 확인
            assertEquals("TestUser", record[5]); // 저장된 레코드의 name이 올바른지 확인
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }
    }


    @Test
    public void testLoadRecord() {
        // Given
        RecordModel.initRecord();
        RecordModel.addRecord(100, 5, 1, 2, "2024-05-18", "TestUser");
        RecordModel.saveRecord();

        // When
        RecordModel.loadRecord();

        // Then
        assertEquals(1, RecordModel.getRankedRecords().size()); // 레코드가 읽혀졌는지 확인
        assertEquals(100, RecordModel.getRankedRecords().get(0).getScore()); // 읽어온 레코드의 score가 올바른지 확인
        assertEquals(5, RecordModel.getRankedRecords().get(0).getDeletedLine()); // 읽어온 레코드의 deletedLine이 올바른지 확인
        assertEquals(1, RecordModel.getRankedRecords().get(0).getGameMode()); // 읽어온 레코드의 gameMode가 올바른지 확인
        assertEquals(2, RecordModel.getRankedRecords().get(0).getGameDifficulty()); // 읽어온 레코드의 gameDifficulty가 올바른지 확인
        assertEquals("2024-05-18", RecordModel.getRankedRecords().get(0).getCreatedAt()); // 읽어온 레코드의 createdAt이 올바른지 확인
        assertEquals("TestUser", RecordModel.getRankedRecords().get(0).getName()); // 읽어온 레코드의 name이 올바른지 확인
    }


    @Test
    public void testClearRecord() {
        // Given
        RecordModel.initRecord();
        RecordModel.addRecord(100, 1, 1, 1, "2024-05-18", "TestUser2");
        RecordModel.addRecord(200, 2, 0, 2, "2024-05-19", "TestUser3");
        RecordModel.saveRecord();

        // When
        RecordModel.initRecord();
        RecordModel.clearRecord();

        // Then
        assertEquals(0, RecordModel.getRankedRecords().size()); // 레코드가 모두 삭제되었는지 확인
        // 파일이 비어 있는지 확인
        File file = new File(RecordModel.RECORD_FILE_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            assertNull(line); // 파일이 비어 있어야 함
        } catch (IOException e) {
            fail("IOException occurred while reading the file: " + e.getMessage());
        }
    }

    @Test
    public void testInitConfig() {
        // Initialize the configuration
        ConfigModel.initConfig();

        // Assert that all the configuration settings are correct

        assertEquals(ConfigModel.BoardSize.MEDIUM, ConfigModel.boardSize);
        assertEquals(10, ConfigModel.boardWidth);
        assertEquals(20, ConfigModel.boardHeight);
        assertFalse(ConfigModel.colorBlindMode);
        assertArrayEquals(
                new int[]{
                        KeyEvent.VK_W, // ROTATE_2P
                        KeyEvent.VK_A, // LEFT_2P
                        KeyEvent.VK_D, // RIGHT_2P
                        KeyEvent.VK_S, // DOWN_2P
                        KeyEvent.VK_SPACE, // DROP_2P
                        KeyEvent.VK_UP, // ROTATE
                        KeyEvent.VK_LEFT, // LEFT
                        KeyEvent.VK_RIGHT, // RIGHT
                        KeyEvent.VK_DOWN, // DOWN
                        KeyEvent.VK_SHIFT, // DROP
                        KeyEvent.VK_ESCAPE, 0},
                ConfigModel.keyBinding
        );
    }

    @Test
    public void testSaveConfig() throws IOException {
        // Change settings before save

        ConfigModel.boardSize = ConfigModel.BoardSize.LARGE;
        ConfigModel.boardWidth = 15;
        ConfigModel.boardHeight = 25;
        ConfigModel.gameSpeed = 2;
        ConfigModel.colorBlindMode = true;
        ConfigModel.keyBinding = new int[]{
                KeyEvent.VK_DOWN, // ROTATE
                KeyEvent.VK_RIGHT, // LEFT
                KeyEvent.VK_LEFT, // RIGHT
                KeyEvent.VK_UP, // DOWN
                KeyEvent.VK_SHIFT, // DROP
                KeyEvent.VK_S, // ROTATE_2P
                KeyEvent.VK_D, // LEFT_2P
                KeyEvent.VK_A, // RIGHT_2P
                KeyEvent.VK_W, // DOWN_2P
                KeyEvent.VK_ENTER, // DROP_2P
                KeyEvent.VK_ESCAPE, 1
        };

        // Save the configuration to the file
        ConfigModel.saveConfig();
/*
        // Read the saved configuration from the file
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tempConfigFile.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line);
            }
        }
*/
        // Verify the contents of the saved configuration file
        String expectedContent = String.join(",",
                ConfigModel.gameMode.name(),
                ConfigModel.gameDifficulty.name(),
                ConfigModel.boardSize.name(),
                Integer.toString(ConfigModel.boardWidth),
                Integer.toString(ConfigModel.boardHeight),
                Double.toString(ConfigModel.gameSpeed),
                Boolean.toString(ConfigModel.colorBlindMode),
                Integer.toString(ConfigModel.keyBinding.length),
                Arrays.stream(ConfigModel.keyBinding)
                        .mapToObj(String::valueOf)
                        .reduce((a, b) -> a + "," + b)
                        .orElse("")
        );

        //assertEquals(expectedContent, contentBuilder.toString());

        // Reinitialize the configuration to verify persistence
        ConfigModel.initConfig();

        // Assert that the initial values are restored after reinitialization
        assertEquals(ConfigModel.BoardSize.MEDIUM, ConfigModel.boardSize);
        assertEquals(10, ConfigModel.boardWidth);
        assertEquals(20, ConfigModel.boardHeight);

        assertFalse(ConfigModel.colorBlindMode);
        assertArrayEquals(
                new int[]{
                        KeyEvent.VK_W, // ROTATE_2P
                        KeyEvent.VK_A, // LEFT_2P
                        KeyEvent.VK_D, // RIGHT_2P
                        KeyEvent.VK_S, // DOWN_2P
                        KeyEvent.VK_SPACE, // DROP_2P
                        KeyEvent.VK_UP, // ROTATE
                        KeyEvent.VK_LEFT, // LEFT
                        KeyEvent.VK_RIGHT, // RIGHT
                        KeyEvent.VK_DOWN, // DOWN
                        KeyEvent.VK_SHIFT, // DROP
                        KeyEvent.VK_ESCAPE, 0},
                ConfigModel.keyBinding
        );
    }

    @Test
    public void ViewTest() {
        HomeController homeController = new HomeController();
        ConfigController configController = new ConfigController();
        SelectView selectView = new SelectView(homeController,true,1);
        HomeView homeView = new HomeView(homeController);
        ConfigView configView = new ConfigView(configController);

        assertNotNull(homeView);
        assertNotNull(configView);
        assertNotNull(selectView);
    }
}