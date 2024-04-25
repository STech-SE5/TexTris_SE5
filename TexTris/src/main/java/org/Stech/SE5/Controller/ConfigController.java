package org.Stech.SE5.Controller;

import org.Stech.SE5.View.ConfigView;


public class ConfigController implements Controller {
    private ConfigView configView;

    public ConfigController() {
        initPresent();
    }

    @Override
    public void initPresent() {
        configView = new ConfigView(this);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            int VIEW_WIDTH = 400;
            int VIEW_HEIGHT = 600;
            configView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            configView.setLocationRelativeTo(null);
            configView.setVisible(true);
        } else {
            configView.setVisible(false);
        }
    }
}
