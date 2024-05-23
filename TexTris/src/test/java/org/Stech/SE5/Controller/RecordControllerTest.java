package org.Stech.SE5.Controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.Model.RecordModel;
import org.Stech.SE5.View.RecordView;
import org.Stech.SE5.Data.Record;
import java.util.ArrayList;

class RecordControllerTest {

    @Test
    void testInitResolution() {
        // Given
        RecordController recordController = new RecordController();

        // When
        int width = recordController.WIDTH;
        int height = recordController.HEIGHT;

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
        int width = recordController.WIDTH;
        int height = recordController.HEIGHT;

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
        int width = recordController.WIDTH;
        int height = recordController.HEIGHT;

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
        int width = recordController.WIDTH;
        int height = recordController.HEIGHT;

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
        int resolutionLarge = recordController.resolution;
        recordController.getsize(ConfigModel.BoardSize.MEDIUM);
        int resolutionMedium = recordController.resolution;
        recordController.getsize(ConfigModel.BoardSize.SMALL);
        int resolutionSmall = recordController.resolution;

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
}
