package org.Stech.SE5;

import org.Stech.SE5.Controller.GameController;
import org.Stech.SE5.Model.GameModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testSetRandomBlock() {
        GameController gameController = new GameController(false, 1);
        GameModel gameModel = new GameModel(gameController, false, 1);
        gameModel.setRandomBlock();

        assertNotNull(gameModel.getNextBlock());    //nextBlock 초기화 여부 확인
        assertNotNull(gameModel.getBoard());
    }

    @Test
    public void testItem() {
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        gameModel.setITEM_GENERATE_INTERVAL0();
        gameModel.setRandomBlock();
        int W = 0;
        int L = 0;
        int B = 0;
        int I = 0;
        int C = 0;
        for (int i = 0; i < 500; i++){
            gameModel.setRandomBlock();
            gameModel.triggerItem();
            switch (gameModel.getNextBlock().getType()) {
                case WEIGHT_BLOCK:
                    W++;
                    break;
                case LINE_CLEANER:
                    L++;
                    break;
                case BOMB:
                    B++;
                    break;
                case ITEM_BOOST:
                    I++;
                    break;
                case CROSS_DELETE:
                    C++;
                    break;
            }
        }
        System.out.println("WEIGHT_BLOCK: " + W);
        System.out.println("LINE_CLEANER: " + L);
        System.out.println("BOMB: " + B);
        System.out.println("ITEM_BOOST: " + I);
        System.out.println("CROSS_DELETE: " + C);
    }

    @Test
    public void testModelValue(){
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        assertEquals(1, gameModel.getMode());
        assertEquals(1, gameModel.getDiff());
        assertEquals(0, gameModel.getLineCounts());
        assertEquals(0, gameModel.getItemCount());
        double delta = 0.0001;
        assertEquals(1.01, gameModel.getGameSpeed(), delta);
        assertEquals(1.05, gameModel.getScorerate(), delta);
    }
}