package org.Stech.SE5.Model;
/*
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordModelTest {

    @Test
    void initRecord() {
    }

    @Test
    void addRecord() {
    }

    @Test
    void saveRecord() {
    }

    @Test
    void loadRecord() {
    }

    @Test
    void clearRecord() {
    }
}
*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class RecordModelTest {

    @Test
    void testInitRecord() {
        // Given
        RecordModel.initRecord();

        // When
        int recordSize = RecordModel.rankedRecords.size();

        // Then
        assertEquals(0, recordSize); // 초기화 후 레코드 크기가 0인지 확인
        // 저장된 레코드가 없으므로 파일에 아무것도 쓰여 있지 않은지도 확인 가능
    }

    @Test
    void testAddRecord() {
        // Given
        RecordModel.initRecord();
        int score = 100;
        int deletedLine = 5;
        int gameMode = 1;
        int gameDifficulty = 2;
        String createdAt = "2024-05-18";
        String name = "TestUser";

        // When
        RecordModel.addRecord(score, deletedLine, gameMode, gameDifficulty, createdAt, name);
        int recordSize = RecordModel.rankedRecords.size();

        // Then
        assertEquals(1, recordSize); // 레코드가 추가되었는지 확인
        // 추가적인 테스트 가능: 파일에 레코드가 저장되었는지, 추가된 레코드의 내용이 맞는지 등
    }

    @Test
    void testSaveRecord() {
        // Given
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
        File file = new File(RecordModel.path);
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

    /*
    @Test
    void testLoadRecord() {
        // Given
        RecordModel.initRecord();
        RecordModel.saveRecord();

        // When
        RecordModel.loadRecord();

        // Then
        assertEquals(1, RecordModel.rankedRecords.size()); // 레코드가 읽혀졌는지 확인
        assertEquals(100, RecordModel.rankedRecords.get(0).score); // 읽어온 레코드의 score가 올바른지 확인
        assertEquals(5, RecordModel.rankedRecords.get(0).deletedLine); // 읽어온 레코드의 deletedLine이 올바른지 확인
        assertEquals(1, RecordModel.rankedRecords.get(0).gameMode); // 읽어온 레코드의 gameMode가 올바른지 확인
        assertEquals(2, RecordModel.rankedRecords.get(0).gameDifficulty); // 읽어온 레코드의 gameDifficulty가 올바른지 확인
        assertEquals("2024-05-18", RecordModel.rankedRecords.get(0).createdAt); // 읽어온 레코드의 createdAt이 올바른지 확인
        assertEquals("TestUser", RecordModel.rankedRecords.get(0).name); // 읽어온 레코드의 name이 올바른지 확인
    }


    @Test
    void testClearRecord() {
        // Given
        RecordModel.initRecord();
        RecordModel.addRecord(100, 1, 1, 1, "2024-05-18", "TestUser");
        RecordModel.addRecord(200, 2, 0, 2, "2024-05-19", "TestUser2");
        RecordModel.saveRecord();

        // When
        RecordModel.clearRecord();

        // Then
        assertEquals(0, RecordModel.rankedRecords.size()); // 레코드가 모두 삭제되었는지 확인
        // 파일에 저장된 내용이 모두 삭제되었는지도 확인 가능
        File file = new File(RecordModel.path);
        assertFalse(file.exists()); // 파일이 존재하지 않아야 함
    }
    */
}
