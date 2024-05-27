package org.Stech.SE5;

import org.Stech.SE5.Controller.BattleController;
import org.Stech.SE5.Controller.HomeController;


public class Main {
    public static void main(String[] args) {
        HomeController homeController = new HomeController();
        homeController.setVisible(true);
        //BattleController battleController = new BattleController(1);
        //battleController.setVisible(true);
        //SelectView selectView = new SelectView(homeController, 1);
        //selectView.setVisible(true);
    }
}