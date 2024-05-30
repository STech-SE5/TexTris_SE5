package org.Stech.SE5.Controller;

import org.Stech.SE5.View.HomeView;


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
