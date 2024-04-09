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
    private final Timer deleteTimer;
    private final Timer weightBlockTimer;

    private static final double INIT_INTERVAL = 1000 / 1;       //추후 1자리에 설정에서 speed 받아와서 넣기

    public GameController() {
        mainTimer = new Timer((int)INIT_INTERVAL, new MainTimerActionListener());
        deleteTimer = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener());
        weightBlockTimer = new Timer((int)INIT_INTERVAL / 5, new WeightBlockTimerActionListener());
        initController();
    }

    public class MainTimerActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown();
        }
    }

    public class DeleteTimerActionListener implements ActionListener {      //메인하고나서
        @Override
        public final void actionPerformed(final ActionEvent e) {
            gameModel.runDelete();
            drawView();
        }
    }

    public class WeightBlockTimerActionListener implements ActionListener {      //아이템 나중에
        @Override
        public final void actionPerformed(final ActionEvent e) {
            gameModel.moveWeightBlockDown();
            drawView();
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

    @Override
    public void initController() {
        this.gameModel = new GameModel(this);
        this.gameView = new GameView(this);
        this.gameView.drawBoard(this.gameModel.getBoard());
    }

    public final void gameStart() {
        gameView.stopPauseKeyListen();
        gameView.startPlayerKeyListen();
        gameView.setVisiblePauseDialog(false);
        gameView.drawBoard(gameModel.getBoard());
        mainTimer.start();
        deleteTimer.start();
    }

    public final void gameStop() {
        gameView.stopPlayerKeyListen();
        gameView.startPauseKeyListen();
        gameView.setVisiblePauseDialog(true);
        mainTimer.stop();
        deleteTimer.stop();
    }

    public final void gameOver() {
        gameView.stopPlayerKeyListen();
        gameView.stopPauseKeyListen();
        mainTimer.stop();   //종료화면 불러와야함, 불러올때 게임난이도,점수 넘겨줘야함
        deleteTimer.stop();
    }

    public final void weightBlockStart() {
        gameView.stopPlayerKeyListen();
        mainTimer.stop();
        weightBlockTimer.start();
    }

    public final void weightBlockStop() {
        gameView.startPlayerKeyListen();
        mainTimer.start();
        weightBlockTimer.stop();
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