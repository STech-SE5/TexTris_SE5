package org.Stech.SE5;

import org.Stech.SE5.Controller.GameController;

public class Main {
    public static void main(String[] args) {
        GameController game = new GameController(false, 2);
        game.setVisible(true);
    }
}