package org.Stech.SE5;

import org.Stech.SE5.controller.HomeController;
import org.Stech.SE5.controller.Controller;

public class Main {
    private static HomeController homeController;
    private static Controller currentController;

    public enum View {HOME, /*GAME, CONFIG, RECORD*/} // 추후 추가?

    protected Main() {
        throw new UnsupportedOperationException();
    }

    public static void main(final String[] args) {
        /*HomeView homeView = new HomeView();
        homeView.setVisible(true);*/

        /*HomeModel.loadConfig();*/
        homeController = new HomeController();
        currentController = homeController;
        currentController.setVisible(true);
    }

    public static void navigate(final View view) {  // 추후 활용할 navigate()
        currentController.setVisible(false);
        switch (view) {
            case HOME -> currentController = homeController;
        }
        currentController.initController();
        currentController.setVisible(true);
    }
}