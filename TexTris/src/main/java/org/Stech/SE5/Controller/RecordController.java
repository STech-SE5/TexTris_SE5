package org.Stech.SE5.Controller;

import org.Stech.SE5.Data.Record;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.Model.RecordModel;
import org.Stech.SE5.View.RecordView;

import java.util.ArrayList;

public class RecordController {
    private RecordView recordView;

    int resolution = 1; // 해상도 설정시 값을 불러와서 대입.

    int WIDTH;
    int HEIGHT;

    public RecordController() {
        initResolution();
        initController();
    }

    private void initResolution() {
        getsize(ConfigModel.boardSize);
        switch (resolution) {
            case 0:
                WIDTH = 400;
                HEIGHT = 600;
                break;
            case 1:
                WIDTH = 500;
                HEIGHT = 750;
                break;
            case 2:
                WIDTH = 600;
                HEIGHT = 900;
                break;
            default:
                WIDTH = 400;
                HEIGHT = 600;
        }
    }

    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize){
            case LARGE -> {
                resolution = 2;
                break;
            }
            case MEDIUM -> {
                resolution = 1;
                break;
            }
            case SMALL -> {
                resolution = 0;
                break;
            }
        }
    }

    public void initController() {
        recordView = new RecordView(this);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            recordView.setSize(WIDTH, HEIGHT);
            recordView.setLocationRelativeTo(null);
            recordView.setVisible(true);
        } else {
            recordView.setVisible(false);
        }
    }

    public ArrayList<Record> getRecords() {
        return RecordModel.rankedRecords;
    }
}
