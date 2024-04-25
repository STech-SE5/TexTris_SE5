package org.Stech.SE5.controller;

import org.Stech.SE5.model.HomeModel;
import org.Stech.SE5.view.HomeView;

public class HomeController implements Controller { // Controller Interface에서 Overriding
    private HomeView homeView;
    private HomeModel homeModel;

    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public HomeController() {
        initController();
    }

    @Override
    public void initController() {
        homeView = new HomeView();
        HomeModel.initConfig();
    }

    @Override
    public final void setVisible(final boolean visible) {
        if (visible) {
            homeView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            homeView.setLocationRelativeTo(null);
            homeView.setVisible(true);
        } else {
            homeView.setVisible(false);
        }
    }
}
