package org.Stech.SE5.Controller;

import org.Stech.SE5.Model.GameModel;
import org.Stech.SE5.View.BattleView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleController {
    private GameModel gameModelP1, gameModelP2;
    private BattleView battleView;
    private final Timer mainTimerP1;
    private final Timer deleteTimerP1;
    private final Timer weightItemTimerP1;
    private final Timer mainTimerP2;
    private final Timer deleteTimerP2;
    private final Timer weightItemTimerP2;
    private final Timer timeAttackTimer;
    private int seconds;
    private int Mode;
    private static final double INIT_INTERVAL = 1000;

    public BattleController(int mode) {
        mainTimerP1 = new Timer((int)INIT_INTERVAL, new MainTimerActionListener(true));
        deleteTimerP1 = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener(true));
        weightItemTimerP1 = new Timer((int)INIT_INTERVAL / 5, new WeightItemTimerActionListener(true));
        mainTimerP2 = new Timer((int)INIT_INTERVAL, new MainTimerActionListener(false));
        deleteTimerP2 = new Timer((int)INIT_INTERVAL / 3, new DeleteTimerActionListener(false));
        weightItemTimerP2 = new Timer((int)INIT_INTERVAL / 5, new WeightItemTimerActionListener(false));
        timeAttackTimer = new Timer(1000, new TimeAttackActionListener());

        Mode = mode;
        initController(Mode);
    }

    public class MainTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public MainTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            moveDown(isPlayer1);
        }
    }

    public class DeleteTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public DeleteTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if(isPlayer1) gameModelP1.runDelete();
            else gameModelP2.runDelete();
            drawView();
        }
    }

    public class WeightItemTimerActionListener implements ActionListener {
        private boolean isPlayer1;
        public WeightItemTimerActionListener(boolean isPlayer1) {
            this.isPlayer1 = isPlayer1;
        }

        @Override
        public final void actionPerformed(final ActionEvent e) {
            if(isPlayer1) gameModelP1.moveWeightBlockDown();
            else gameModelP2.moveWeightBlockDown();
            drawView();
        }
    }

    public class TimeAttackActionListener implements ActionListener {
        @Override
        public final void actionPerformed(final ActionEvent e) {
            seconds--;
            battleView.drawTimer(seconds);
            if(seconds == 0) {
                gameOver();
            }
        }
    }

    public void initController(int mode) {
        this.seconds = 180;
        this.gameModelP1 = new GameModel(this, mode, true);
        this.gameModelP2 = new GameModel(this, mode, false);
        this.gameModelP1.setOpposite(gameModelP2);
        this.gameModelP2.setOpposite(gameModelP1);

        this.battleView = new BattleView(this, mode);
        this.battleView.drawBoard(this.gameModelP1.getBoard(), true);
        this.battleView.drawBoard(this.gameModelP2.getBoard(), false);
    }

    public final void setTimeIntervalP1(int interval) {
        mainTimerP1.setDelay(interval);
        deleteTimerP1.setDelay(Math.max(interval/3, 200));
    }

    public final void setTimeIntervalP2(int interval) {
        mainTimerP2.setDelay(interval);
        deleteTimerP2.setDelay(Math.max(interval/3, 200));
    }

    public final void setVisible(final boolean visible) {
        if (visible) {
            battleView.setVisible(true);
            gameStart();
        } else {
            battleView.setVisible(false);
            gameStop();
        }
    }

    public final void gameStart() {
            battleView.stopPauseKeyListen();
            battleView.startPlayerKeyListen(true);
            battleView.startPlayerKeyListen(false);
            battleView.setVisiblePauseDialog(false);
            battleView.drawBoard(gameModelP1.getBoard(), true);
            battleView.drawBoard(gameModelP2.getBoard(), false);
            mainTimerP1.start();
            deleteTimerP1.start();
            mainTimerP2.start();
            deleteTimerP2.start();
            if(Mode == 2) {
                timeAttackTimer.start();
            }
    }

    public final void gameStop() {
        battleView.stopPlayerKeyListen(true);
        battleView.stopPlayerKeyListen(false);
        battleView.startPauseKeyListen();
        battleView.setVisiblePauseDialog(true);
        mainTimerP1.stop();
        deleteTimerP1.stop();
        mainTimerP2.stop();
        deleteTimerP2.stop();
        if(Mode == 2) {
            timeAttackTimer.stop();
        }
    }

    public final void gameOver() {
        battleView.stopPlayerKeyListen(true);
        battleView.stopPlayerKeyListen(false);
        battleView.stopPauseKeyListen();
        battleView.setVisiblePauseDialog(false);
        mainTimerP1.stop();
        mainTimerP2.stop();
        timeAttackTimer.stop();
    }

    public final void weightItemStart(boolean isPlayer1) {
        if(isPlayer1) {
            battleView.stopPlayerKeyListen(true);
            mainTimerP1.stop();
            weightItemTimerP1.start();
        } else {
            battleView.stopPlayerKeyListen(false);
            mainTimerP2.stop();
            weightItemTimerP2.start();
        }
    }

    public final void weightItemStop(boolean isPlayer1) {
        if(isPlayer1) {
            battleView.startPlayerKeyListen(true);
            mainTimerP1.start();
            weightItemTimerP1.stop();
        } else {
            battleView.startPlayerKeyListen(false);
            mainTimerP2.start();
            weightItemTimerP2.stop();
        }
    }

    public void drawView() {
        battleView.drawBoard(gameModelP1.getBoard(), true);
        battleView.drawBoard(gameModelP2.getBoard(), false);
        battleView.drawNextBlock(gameModelP1.getNextBlock(), true);
        battleView.drawNextBlock(gameModelP2.getNextBlock(), false);
        battleView.drawScore(gameModelP1.getScore(), true);
        battleView.drawScore(gameModelP2.getScore(), false);
        battleView.drawItemCount(gameModelP1.getItemCount(), true);
        battleView.drawItemCount(gameModelP2.getItemCount(), false);
        battleView.drawAttack(gameModelP1.getAttack(), true);
        battleView.drawAttack(gameModelP2.getAttack(), false);
    }

    public final void moveRotate(boolean isPlayer1) {
        if(isPlayer1) {
            gameModelP1.actRotate();
        } else {
            gameModelP2.actRotate();
        }
        drawView();
    }

    public final void moveDown(boolean isPlayer1) {
        if(isPlayer1) {
            gameModelP1.moveDownAndCheck();
        } else {
            gameModelP2.moveDownAndCheck();
        }
        drawView();
    }

    public final void moveLeft(boolean isPlayer1) {
        if(isPlayer1) {
            gameModelP1.moveLeft();
        } else {
            gameModelP2.moveLeft();
        }
        drawView();
    }

    public final void moveRight(boolean isPlayer1) {
        if(isPlayer1) {
            gameModelP1.moveRight();
        } else {
            gameModelP2.moveRight();
        }
        drawView();
    }

    public final void moveStraightDown(boolean isPlayer1) {
        if(isPlayer1) {
            gameModelP1.moveStraightDown();
        } else {
            gameModelP2.moveStraightDown();
        }
        drawView();
    }
}
