package org.Stech.SE5.controller;

import org.Stech.SE5.model.HomeModel;
import org.Stech.SE5.view.HomeView;

/*
    1) 설정에서 변경한 해상도 반영
    2) 키보드 입력 받기
 */

public class HomeController { // Controller Interface에서 Overriding
    private HomeView homeView;
    private HomeModel homeModel;

    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;

    public HomeController() {
        initController();
    }


    public void initController() {
        homeView = new HomeView(this);
        HomeModel.initConfig();
    }


    public void setVisible(final boolean visible) {
        if (visible) {
            homeView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
            homeView.setLocationRelativeTo(null);
            homeView.setVisible(true);
        } else {
            homeView.setVisible(false);
        }
    }


    // HomeView에서 구현한 KeyListener 클래스의 기능을, 각 성격에 맞게 HomeModel과 HomeController로 분할
    /*public final void moveUP() {
        // homeModel.moveUP();
        // drawView();
    }

    public final void moveDown() {
        // homeModel.moveDown();
        // drawView();
    }

    public final void click() {
        // homeModel.click();
    }*/
}
