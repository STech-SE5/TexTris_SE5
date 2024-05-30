package org.Stech.SE5.Controller;

import org.Stech.SE5.View.HomeView;

/*
    1) 설정에서 변경한 해상도 반영
    2) 키보드 입력 받기
 */

public class HomeController { // Controller Interface에서 Overriding
    private HomeView homeView;

    public HomeController() {
        initController();
    }


    public void initController() {
        homeView = new HomeView(this);
    }


    public void setVisible(final boolean visible) {
        if (visible) {
            homeView.setLocationRelativeTo(null);
            homeView.setVisible(true);
        } else {
            homeView.setVisible(false);
        }
    }
}
