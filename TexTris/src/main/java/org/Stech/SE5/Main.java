package org.Stech.SE5;

import org.Stech.SE5.Controller.BattleController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Model.GameModel;
import org.Stech.SE5.View.BattleView;

public class Main {
    public static void main(String[] args) {
        //HomeController homeController = new HomeController();
        //homeController.setVisible(true);
        BattleController battleController = new BattleController(1);
        battleController.setVisible(true);
    }
}