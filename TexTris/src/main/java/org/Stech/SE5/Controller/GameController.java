package org.Stech.SE5.Controller;

import org.Stech.SE5.View.GameView;
import org.Stech.SE5.Model.GameModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.Timer;

public class GameController implements Controller {

    private GameView gameView;
    private GameModel gameModel;

    private final Timer mainTimer;

    private static final double INIT_INTERVAL = 1000 / 1;       //추후 1자리에 설정에서 speed 받아와서 넣기

    public GameController() {
        mainTimer = new Timer((int)INIT_INTERVAL, new MainTimerActionListener());
        initController();
    }

    public class MainTimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            gameView.setVisible(true);
            gameStart();
        } else {
            gameView.setVisible(false);
            gameStop();
        }
    }

    public final void gameStart() {
        gameView.stopPauseKeyListen();
        gameView.startPlayerKeyListen();
        gameView.setVisiblePauseDialog(false);
        gameView.drawBoard(gameModel.getBoard());
        mainTimer.start();
    }

    public final void gameStop() {
        gameView.stopPlayerKeyListen();
        gameView.startPauseKeyListen();
        gameView.setVisiblePauseDialog(true);
        mainTimer.stop();
    }

    public final void gameOver() {
        gameView.stopPlayerKeyListen();
        gameView.stopPauseKeyListen();
        mainTimer.stop();   //종료화면 불러와야함, 불러올때 게임난이도,점수 넘겨줘야함
    }

    @Override
    public void initController() {
        this.gameModel = new GameModel(this);
        this.gameView = new GameView(this);
        this.gameView.drawBoard(this.gameModel.getBoard());
    }

    public void drawView() {
        gameView.drawBoard(gameModel.getBoard());
        gameView.drawNextBlock(gameModel.getNextBlock());
        gameView.drawScore(gameModel.getScore());
        gameView.drawLevel();
        gameView.drawDeletedRaw(gameModel.getDeletedRaw());
    }

    public final void actRotate() {
        gameModel.actRotate();
        drawView();
    }

    public final void moveLeft() {
        gameModel.moveLeft();
        drawView();
    }

    public final void moveRight() {
        gameModel.moveRight();
        drawView();
    }

    public final void moveDown() {
        gameModel.moveDownAndCheck();
        drawView();
    }

    public final void moveStraightDown() {
        gameModel.moveStraightDown();
        drawView();
    }
}