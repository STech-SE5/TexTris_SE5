package org.Stech.SE5.Controller;

import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.ConfigView;


public class ConfigController{
    private ConfigView configView;

    int VIEW_WIDTH;
    int VIEW_HEIGHT;

    double Size;

    public ConfigController() {
        initController();
    }

    public void initController() {
        configView = new ConfigView(this);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            setSize();
            configView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            configView.setLocationRelativeTo(null);
            configView.setVisible(true);
        } else {
            configView.setVisible(false);
        }
    }
    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize){
            case LARGE -> {
                Size = 1.5;
                break;
            }
            case MEDIUM -> {
                Size = 1.25;
                break;
            }
            case SMALL -> {
                Size = 1;
                break;
            }
        }
    }

    public void setSize() {
        getsize(ConfigModel.boardSize);   //설정에서 받아와야함
        if (Size == 1) {
            VIEW_WIDTH = 400;
            VIEW_HEIGHT = 600;

        } else if (Size == 1.25){
            VIEW_WIDTH = 500;
            VIEW_HEIGHT = 750;

        } else if (Size == 1.5) {
            VIEW_WIDTH = 600;
            VIEW_HEIGHT = 900;

        }
    }
}
