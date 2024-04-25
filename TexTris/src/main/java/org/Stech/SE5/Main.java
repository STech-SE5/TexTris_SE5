package org.Stech.SE5;

import org.Stech.SE5.controller.HomeController;
import org.Stech.SE5.controller.Controller;

/*
    1) Main에 남은 navigate() 제거?
    2) 각 컨트롤러를 어떻게 생성하고 활용할 것인지
 */

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