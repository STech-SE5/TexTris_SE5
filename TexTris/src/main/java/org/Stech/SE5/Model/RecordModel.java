package org.Stech.SE5.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.Stech.SE5.Data.Record;

public class RecordModel {

    final static String RECORD_FILE_PATH = "Data/record.txt";
    public static int lastID = -1;

    public static ArrayList<Record> rankedRecords = new ArrayList<>(); //record 자체의 조작이 외부에서 불가능 하도록 수정 필요..?

    public static void initRecord() {
        loadRecord();
    }

    public static int getLastID() {
        return lastID;
    }

    // 여기서 점수, 게임모드, 게임 난이도 불러오는 메소드 호출.
    public static void addRecord(int score, int deletedLine, int gameMode, int gameDifficulty, String createdAt, String name) {
        Random rnd = new Random(System.currentTimeMillis());
        int id = rnd.nextInt(1000000);
        lastID = id;

        rankedRecords.add(new Record(id, score, deletedLine, gameMode, gameDifficulty, createdAt, name));
        Collections.sort(rankedRecords);
        saveRecord();
    }

    public static ArrayList<Record> getRankedRecords() {
        return rankedRecords;
    }

    static void saveRecord() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(RECORD_FILE_PATH))) {
            for (Record record : rankedRecords) {
                out.write(String.format("%d,%d,%d,%d,%s,%s\n",
                        record.getScore(), record.getDeletedLine(), record.getGameMode(),
                        record.getGameDifficulty(), record.getCreatedAt(), record.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadRecord() {
        rankedRecords.clear(); // 기존 기록 초기화
        try (BufferedReader bufReader = new BufferedReader(new FileReader(RECORD_FILE_PATH))) {
            String line;
            Random rnd = new Random(System.currentTimeMillis());
            while ((line = bufReader.readLine()) != null) {
                String[] record = line.split(",");
                rankedRecords.add(new Record(
                        rnd.nextInt(1000000),
                        Integer.parseInt(record[0]),
                        Integer.parseInt(record[1]),
                        Integer.parseInt(record[2]),
                        Integer.parseInt(record[3]),
                        record[4],
                        record[5])
                );
            }
        } catch (IOException e) {
            System.out.println("저장된 기록이 없습니다.");
        }
    }

    public static void clearRecord() {
        try {
            FileWriter fw = new FileWriter(RECORD_FILE_PATH, false); // 파일을 덮어쓰기 모드로 열기
            fw.write(""); // 파일에 빈 문자열 쓰기
            fw.close(); // 파일 닫기
            rankedRecords.clear(); // rankedRecords 리스트 초기화
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}