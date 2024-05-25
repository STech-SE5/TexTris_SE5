package org.Stech.SE5.Controller;

import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.ConfigView;

// New config view - prototype
import org.Stech.SE5.View.ConfigViewNew;

import java.awt.event.KeyEvent;

public class ConfigController {

    private ConfigModel configModel;

    public ConfigController(ConfigModel configModel) {
        this.configModel = configModel;
    }

    private ConfigViewNew configView;

    int VIEW_WIDTH;
    int VIEW_HEIGHT;

    double Size;

    public ConfigController() {
        initController();
    }

    public void initController() {
        configView = new ConfigViewNew(this);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            setSize();
            configView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            configView.setLocationRelativeTo(null);
            configView.revalidate();
            configView.repaint();
            configView.setVisible(true);
        } else {
            configView.setVisible(false);
        }
    }

    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize) {
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

        } else if (Size == 1.25) {
            VIEW_WIDTH = 500;
            VIEW_HEIGHT = 750;

        } else if (Size == 1.5) {
            VIEW_WIDTH = 600;
            VIEW_HEIGHT = 900;

        }
    }

//    public void updateModelWithKeyEvent(KeyEvent e) {
//        ConfigModel.PlayerKey key = ConfigModel.PlayerKey.getPlayerKey(e);
//        // Update the model with the prepared key (could be based on tempKey logic)
//        configModel.setLastKey(key);
//        System.out.println("Model updated with PlayerKey: " + key); // Debug information
//    }

}
