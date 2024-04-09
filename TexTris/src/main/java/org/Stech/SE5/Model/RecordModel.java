package org.Stech.SE5.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import org.Stech.SE5.Data.Record;

public class RecordModel {
    public static ArrayList<Record> rankedRecords = new ArrayList<Record>();
    private final static String path = "Data/record.txt";

    public static void addRecord(int score, int deletedLine, String createdAt, String name) {
        rankedRecords.add(new Record(score, deletedLine, createdAt, name));
        Collections.sort(rankedRecords);
        saveRecord();
    }

    public static void initRecord() {
        rankedRecords = new ArrayList<Record>();
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
                //out.write(rankedRecords.get(i).gameMode + ",");
                //out.write(rankedRecords.get(i).gameDifficulty + ",");
                out.write(rankedRecords.get(i).createdAt + ",");
                out.write(rankedRecords.get(i).name + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void loadRecord() {
        try {
            File f = new File(path);
            FileReader fStream = new FileReader(f);
            BufferedReader bufReader = new BufferedReader(fStream);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                String[] record = line.split(",");
                rankedRecords.add(new Record(
                        Integer.parseInt(record[0]),
                        Integer.parseInt(record[1]),
                        record[2],
                        record[3]));
                        //record[4],
                        //record[5]
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

