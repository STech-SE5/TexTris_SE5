package org.Stech.SE5;

import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.View.BattleView;

public class Main {
    public static void main(String[] args) {
        //HomeController homeController = new HomeController();
        //homeController.setVisible(true);
        BattleView battleView = new BattleView(2);
        battleView.setVisible(true);
    }
}