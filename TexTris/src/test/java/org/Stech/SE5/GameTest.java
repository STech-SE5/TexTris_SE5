package org.Stech.SE5;

import org.Stech.SE5.Controller.GameController;
import org.Stech.SE5.Model.GameModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testSetRandomBlock() {
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        gameModel.setRandomBlock();

        assertNotNull(gameModel.getNextBlock());    //nextBlock 초기화 여부 확인
    }
}