package org.Stech.SE5;

import org.Stech.SE5.Controller.BattleController;
import org.Stech.SE5.Controller.GameController;
import org.Stech.SE5.Model.GameModel;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testSetRandomBlock() {
        GameController gameController = new GameController(false, 1);
        GameModel gameModel = new GameModel(gameController, false, 1);
        gameModel.setRandomBlock_test();

        assertNotNull(gameModel.getNextBlock());    //nextBlock 초기화 여부 확인
        assertNotNull(gameModel.getBoard());
    }

    @Test
    public void testBlocks_normal() {
        GameController gameController = new GameController(false, 1);
        GameModel gameModel = new GameModel(gameController, false, 1);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++){
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testBlocks_easy() {
        GameController gameController = new GameController(false, 0);
        GameModel gameModel = new GameModel(gameController, false, 0);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++){
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testBlocks_hard() {
        GameController gameController = new GameController(false, 2);
        GameModel gameModel = new GameModel(gameController, false, 2);
        gameModel.setRandomBlock_test();
        int I = 0;
        int J = 0;
        int L = 0;
        int O = 0;
        int S = 0;
        int T = 0;
        int Z = 0;
        for (int i = 0; i < 70; i++){
            gameModel.setRandomBlock_test();
            switch (gameModel.getNextBlock().getType()) {
                case I_BLOCK:
                    I++;
                    break;
                case J_BLOCK:
                    J++;
                    break;
                case L_BLOCK:
                    L++;
                    break;
                case O_BLOCK:
                    O++;
                    break;
                case S_BLOCK:
                    S++;
                    break;
                case T_BLOCK:
                    T++;
                    break;
                case Z_BLOCK:
                    Z++;
                    break;
            }
        }
        System.out.println("I: " + I);
        System.out.println("J: " + J);
        System.out.println("L: " + L);
        System.out.println("O: " + O);
        System.out.println("S: " + S);
        System.out.println("T: " + T);
        System.out.println("Z: " + Z);
    }

    @Test
    public void testItem() {
        GameController gameController = new GameController(true, 1);
        GameModel gameModel = new GameModel(gameController, true, 1);
        gameModel.setITEM_GENERATE_INTERVAL0();
        gameModel.setRandomBlock_test();
        int W = 0;
        int L = 0;
        int B = 0;
        int I = 0;
        int C = 0;
        for (int i = 0; i < 50; i++){
            gameModel.setRandomBlock_test();
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
    public void testModelValue_Single(){
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

    @Test
    public void testModelValue_Battle(){
        BattleController battleController = new BattleController(2);
        GameModel gameModel = new GameModel(battleController, 2, true);
        assertEquals(0, gameModel.getMode());   //item모드면 1, timer모드임
        assertEquals(0, gameModel.getLineCounts());
        assertEquals(180, battleController.seconds);
        double delta = 0.0001;
        assertEquals(1.01, gameModel.getGameSpeed(), delta);
        assertEquals(1.05, gameModel.getScorerate(), delta);
    }

    @Test
    public void testweightBlockStart() {
        BattleController battleController = new BattleController(1);
        battleController.gameStart();
        assertTrue(battleController.P1MainTimerisRunning());
        assertTrue(battleController.P2MainTimerisRunning());
        battleController.weightBlockStart(true);
        assertFalse(battleController.P1MainTimerisRunning());
        assertTrue(battleController.P2MainTimerisRunning());
        battleController.weightBlockStart(false);
        assertFalse(battleController.P1MainTimerisRunning());
        assertFalse(battleController.P2MainTimerisRunning());
    }
}