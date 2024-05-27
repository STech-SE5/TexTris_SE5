package org.Stech.SE5;

import org.Stech.SE5.Controller.RecordController;
import org.Stech.SE5.Data.Record;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.Model.RecordModel;
import org.Stech.SE5.View.RecordView;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecordTest {
    //RecordControllerTest

    @Test
    void testInitResolution() {
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
    void testInitResolutionSmall() {
        // Given
        RecordController recordController = new RecordController();
        ConfigModel.boardSize = ConfigModel.BoardSize.SMALL;

        // When
        recordController.initResolution();
        int width = recordController.getWIDTH();
        int height = recordController.getHEIGHT();

        // Then
        assertEquals(400, width); // 해상도가 SMALL일 때 너비가 400인지 확인
        assertEquals(600, height); // 해상도가 SMALL일 때 높이가 600인지 확인
    }

    @Test
    void testInitResolutionMedium() {
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
    void testInitResolutionLarge() {
        // Given
        RecordController recordController = new RecordController();
        ConfigModel.boardSize = ConfigModel.BoardSize.LARGE;

        // When
        recordController.initResolution();
        int width = recordController.getWIDTH();
        int height = recordController.getHEIGHT();

        // Then
        assertEquals(600, width); // 해상도가 LARGE일 때 너비가 500인지 확인
        assertEquals(900, height); // 해상도가 LARGE일 때 높이가 750인지 확인
    }


    @Test
    void testGetsize() {
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
    void testInitController() {
        // Given
        RecordController recordController = new RecordController();

        // When
        RecordView recordView = recordController.recordView;

        // Then
        assertNotNull(recordView); // RecordView가 초기화되었는지 확인
    }

    @Test
    void testSetVisible() {
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
    void testGetRecords() {
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
    void testInitRecord() {
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
    void testAddRecord() {
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
    void testSaveRecord() {
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
    void testLoadRecord() {
        // Given
        RecordModel.initRecord();
        RecordModel.addRecord(100,5,1,2,"2024-05-18","TestUser");
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
    void testClearRecord() {
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

}

