package org.Stech.SE5.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.Stech.SE5.Data.Record;

public class RecordModel {
    public static ArrayList<Record> rankedRecords = new ArrayList<Record>();
    private final static String path = "Data/record.txt";
    public static int lastID = -1;

    public static void initRecord() {
        rankedRecords = new ArrayList<Record>();
        saveRecord();
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

    public static void saveRecord() {
        BufferedWriter out = null;
        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileWriter fStream = new FileWriter(f, false);
            out = new BufferedWriter(fStream);
            for (int i = 0; i < rankedRecords.size(); i++) {
                out.write(rankedRecords.get(i).score + ",");
                out.write(rankedRecords.get(i).deletedLine + ",");
                out.write(rankedRecords.get(i).gameMode + ",");
                out.write(rankedRecords.get(i).gameDifficulty + ",");
                out.write(rankedRecords.get(i).createdAt + ",");
                out.write(rankedRecords.get(i).name + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadRecord() {
        Random rnd = new Random(System.currentTimeMillis());
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = "";
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
            bufReader.close();
        } catch (IOException e) {
            System.out.println("저장된 기록이 없습니다.");
        }
    }

    public static void clearRecord() {
        try {
            FileWriter fw = new FileWriter(path, false); // 파일을 덮어쓰기 모드로 열기
            fw.write(""); // 파일에 빈 문자열 쓰기
            fw.close(); // 파일 닫기
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

