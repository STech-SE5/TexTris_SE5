package org.Stech.SE5.View;

import org.Stech.SE5.Controller.BattleController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.Block.Element;
import org.Stech.SE5.Block.Block;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BattleView extends JFrame{
    private HomeController homeController;
    private int gamemodeflag;
    private  double Size;
    private  int VIEW_WIDTH;
    private  int VIEW_HEIGHT;
    static  int FONT_SIZE;
    static int LABEL_FONT;
    static  float LINE_SPACING;
    static  int PANE_WIDTH;
    static  int PANE_HEIGHT;
    private boolean buttoncount = true;
    private JTextPane boardPaneP1;
    private JPanel nextPanelP1;
    private JTextPane nextBlockPaneP1;
    private JPanel scorePanelP1;
    private JTextPane scorePaneP1;
    private JPanel attackPanelP1;
    private JTextPane attackPaneP1;
    private JPanel itemCountPanelP1;
    private JTextPane itemCountPaneP1;

    private JTextPane boardPaneP2;
    private JPanel nextPanelP2;
    private JTextPane nextBlockPaneP2;
    private JPanel scorePanelP2;
    private JTextPane scorePaneP2;
    private JPanel attackPanelP2;
    private JTextPane attackPaneP2;
    private JPanel itemCountPanelP2;
    private JTextPane itemCountPaneP2;

    private JPanel timerPanel;
    private JTextPane timerPane;
    private JButton exitBtn;
    private JButton continueBtn;

    private JButton MainBtn;

    private SimpleAttributeSet styleSet;

    private JPanel backgroundPanel;
    private BattleController battlecontroller;

    private Player1KeyListener player1KeyListener;
    private Player2KeyListener player2KeyListener;
    private PauseKeyListener pauseKeyListener;
    private EndKeyListener endKeyListener;

    private JPanel pauseDialog = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GRAY);
            g2.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    };

    private JTextPane resultPane;

    private JPanel finishDialog = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GRAY);
            g2.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    };

    public BattleView(final BattleController controller, int modeflag/*0,1,2 각 normal, item, time*/) {
        if (modeflag < 0 || modeflag > 2){
            throw new IllegalArgumentException("Invalid mode data");
        }
        super("TETRIS");
        setSize();
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        Font labelFont = new Font("Arial", Font.PLAIN, LABEL_FONT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamemodeflag = modeflag;

        this.battlecontroller = controller;

        backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // 부모 클래스의 paintComponent 호출
                g.setColor(Color.BLACK); // 색상을 검은색으로 설정
                g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 패널 전체를 검은색으로 채움
            }
        };
        backgroundPanel.setLayout(null);

        timerPanel = new JPanel();
        timerPanel.setLayout(null);
        timerPanel.setBackground(Color.GRAY);
        timerPanel.setBounds((int)(355 * Size), (int) (20 * Size), 100, 75);        //수정 필요
        JLabel timerLabel = new JLabel("timer", SwingConstants.CENTER);
        timerLabel.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        timerLabel.setFont(labelFont); // 생성한 Font 객체를 JLabel에 설정
        timerLabel.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        nextPanelP1 = new JPanel();
        nextPanelP1.setLayout(null);
        nextPanelP1.setBounds((int)(280 * Size), (int)(54 * Size), PANE_WIDTH,(int)(PANE_HEIGHT * 1.6));
        nextPanelP1.setBackground(Color.GRAY); // 패널의 배경색을 회색으로 설정
        JLabel nextLabel1 = new JLabel("next", SwingConstants.CENTER);    // nextPanel에 "next" 텍스트를 추가합니다.
        nextLabel1.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        nextLabel1.setFont(labelFont); // 생성한 Font 객체를 JLabel에 설정
        nextLabel1.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size)); // 라벨의 위치와 크기를 설정합니다.
        nextPanelP2 = new JPanel();
        nextPanelP2.setLayout(null);
        nextPanelP2.setBounds((int)(680 * Size), (int)(54 * Size), PANE_WIDTH,(int)(PANE_HEIGHT * 1.6));
        nextPanelP2.setBackground(Color.GRAY); // 패널의 배경색을 회색으로 설정
        JLabel nextLabel2 = new JLabel("next", SwingConstants.CENTER);    // nextPanel에 "next" 텍스트를 추가합니다.
        nextLabel2.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        nextLabel2.setFont(labelFont); // 생성한 Font 객체를 JLabel에 설정
        nextLabel2.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size)); // 라벨의 위치와 크기를 설정합니다.

        scorePanelP1 = new JPanel();
        scorePanelP1.setLayout(null);
        scorePanelP1.setBounds((int)(280 * Size),(int)(260 * Size),PANE_WIDTH,PANE_HEIGHT);
        scorePanelP1.setBackground(Color.GRAY);
        JLabel scoreLabel1 = new JLabel("score", SwingConstants.CENTER); // scorePanel에 "score" 텍스트를 추가합니다.
        scoreLabel1.setForeground(Color.WHITE);
        scoreLabel1.setFont(labelFont);
        scoreLabel1.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));
        scorePanelP2 = new JPanel();
        scorePanelP2.setLayout(null);
        scorePanelP2.setBounds((int)(680 * Size),(int)(260 * Size),PANE_WIDTH,PANE_HEIGHT);
        scorePanelP2.setBackground(Color.GRAY);
        JLabel scoreLabel2 = new JLabel("score", SwingConstants.CENTER); // scorePanel에 "score" 텍스트를 추가합니다.
        scoreLabel2.setForeground(Color.WHITE);
        scoreLabel2.setFont(labelFont);
        scoreLabel2.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        attackPanelP1 = new JPanel();
        attackPanelP1.setLayout(null);
        attackPanelP1.setBounds((int)(280 * Size),(int)(340 * Size),(int) (100 * Size),PANE_HEIGHT);
        attackPanelP1.setBackground(Color.GRAY);
        JLabel attackLabel1 = new JLabel("attack", SwingConstants.CENTER); // attackPanel에 "level" 텍스트를 추가합니다.
        attackLabel1.setForeground(Color.WHITE);
        attackLabel1.setFont(labelFont);
        attackLabel1.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));
        attackPanelP2 = new JPanel();
        attackPanelP2.setLayout(null);
        attackPanelP2.setBounds((int)(680 * Size),(int)(340 * Size),(int) (100 * Size),PANE_HEIGHT);
        attackPanelP2.setBackground(Color.GRAY);
        JLabel attackLabel2 = new JLabel("attack", SwingConstants.CENTER); // attackPanel에 "level" 텍스트를 추가합니다.
        attackLabel2.setForeground(Color.WHITE);
        attackLabel2.setFont(labelFont);
        attackLabel2.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        itemCountPanelP1 = new JPanel();
        itemCountPanelP1.setLayout(null);
        itemCountPanelP1.setBounds((int)(280 * Size),(int)(420 * Size),PANE_WIDTH,PANE_HEIGHT);
        itemCountPanelP1.setBackground(Color.GRAY);
        JLabel itemcountLabel1 = new JLabel("ItemCounts", SwingConstants.CENTER);  // linesPanel에 "lines" 텍스트를 추가합니다.
        itemcountLabel1.setForeground(Color.WHITE);
        itemcountLabel1.setFont(labelFont);
        itemcountLabel1.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));
        itemCountPanelP2 = new JPanel();
        itemCountPanelP2.setLayout(null);
        itemCountPanelP2.setBounds((int)(680 * Size),(int)(420 * Size),PANE_WIDTH,PANE_HEIGHT);
        itemCountPanelP2.setBackground(Color.GRAY);
        JLabel itemcountLabel2 = new JLabel("ItemCounts", SwingConstants.CENTER);  // linesPanel에 "lines" 텍스트를 추가합니다.
        itemcountLabel2.setForeground(Color.WHITE);
        itemcountLabel2.setFont(labelFont);
        itemcountLabel2.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        timerPane = new JTextPane();
        timerPane.setEditable(false);
        timerPane.setOpaque(true);
        timerPane.setBackground(Color.BLACK);
        timerPane.setBounds((int)(5 * Size), (int)(20 * Size), (int)(70 * Size), (int)(35 * Size));     //수정필요

        resultPane = new JTextPane();
        resultPane.setEditable(false);
        resultPane.setOpaque(true);
        resultPane.setBackground(Color.GRAY);
        resultPane.setBounds((int)(10 * Size),(int)(10 * Size), (int)(180 * Size), (int)(60 * Size));

        finishDialog.setBounds((int)(300 * Size), (int)(180 * Size), (int)(200 * Size), (int)(100 * Size));
        finishDialog.setLayout(null);
        finishDialog.setVisible(false);
        finishDialog.setOpaque(true);

        MainBtn = new JButton("Press Enter To Main");
        MainBtn.setBounds((int)(10 * Size), (int)(75 * Size), (int)(180 * Size), (int)(20 * Size));
        MainBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        MainBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        MainBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        MainBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        MainBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        boardPaneP1 = new JTextPane();
        boardPaneP1.setEditable(false);
        boardPaneP1.setOpaque(false);
        boardPaneP1.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPaneP1.setBounds((int)(40 * Size),(int)(55 * Size), (int)(220 * Size),(int)(440 * Size));
        boardPaneP2 = new JTextPane();
        boardPaneP2.setEditable(false);
        boardPaneP2.setOpaque(false);
        boardPaneP2.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPaneP2.setBounds((int)(440 * Size),(int)(55 * Size), (int)(220 * Size),(int)(440 * Size));

        nextBlockPaneP1 = new JTextPane();
        nextBlockPaneP1.setEditable(false);
        nextBlockPaneP1.setOpaque(true);
        nextBlockPaneP1.setBackground(Color.BLACK);
        nextBlockPaneP1.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(95 * Size));
        nextBlockPaneP2 = new JTextPane();
        nextBlockPaneP2.setEditable(false);
        nextBlockPaneP2.setOpaque(true);
        nextBlockPaneP2.setBackground(Color.BLACK);
        nextBlockPaneP2.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(95 * Size));

        scorePaneP1 = new JTextPane();
        scorePaneP1.setEditable(false);
        scorePaneP1.setOpaque(true);
        scorePaneP1.setBackground(Color.BLACK);
        scorePaneP1.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));
        scorePaneP2 = new JTextPane();
        scorePaneP2.setEditable(false);
        scorePaneP2.setOpaque(true);
        scorePaneP2.setBackground(Color.BLACK);
        scorePaneP2.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

        attackPaneP1 = new JTextPane();
        attackPaneP1.setEditable(false);
        attackPaneP1.setOpaque(true);
        attackPaneP1.setBackground(Color.BLACK);
        attackPaneP1.setBounds((int)(5 * Size), (int)(20 * Size), (int)(90 * Size), (int)(50 * Size));
        attackPaneP2 = new JTextPane();
        attackPaneP2.setEditable(false);
        attackPaneP2.setOpaque(true);
        attackPaneP2.setBackground(Color.BLACK);
        attackPaneP2.setBounds((int)(5 * Size), (int)(20 * Size), (int)(90 * Size), (int)(50 * Size));

        itemCountPaneP1 = new JTextPane();
        itemCountPaneP1.setEditable(false);
        itemCountPaneP1.setOpaque(true);
        itemCountPaneP1.setBackground(Color.BLACK);
        itemCountPaneP1.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));
        itemCountPaneP2 = new JTextPane();
        itemCountPaneP2.setEditable(false);
        itemCountPaneP2.setOpaque(true);
        itemCountPaneP2.setBackground(Color.BLACK);
        itemCountPaneP2.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

        pauseDialog.setBounds((int)(300 * Size), (int)(200 * Size), (int)(200 * Size), (int)(100 * Size));
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setOpaque(true);

        continueBtn = new JButton("Continue");
        continueBtn.setBounds((int)(10 * Size), (int)(30 * Size), (int)(80 * Size), (int)(40 * Size));
        continueBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        continueBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        continueBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        continueBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        continueBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        exitBtn = new JButton("Exit");
        exitBtn.setBounds((int)(110 * Size), (int)(30 * Size), (int)(80 * Size), (int)(40 * Size));
        exitBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        exitBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        exitBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        exitBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        exitBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        continueBtn.addActionListener(e -> battlecontroller.gameStart());
        exitBtn.addActionListener(e -> exitGame());
        MainBtn.addActionListener(e -> exitGame());

        add(backgroundPanel);
        backgroundPanel.add(finishDialog);
        finishDialog.add(MainBtn);
        finishDialog.add(resultPane);
        backgroundPanel.add(pauseDialog);
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);
        backgroundPanel.add(boardPaneP1);
        backgroundPanel.add(boardPaneP2);

        backgroundPanel.add(nextPanelP1);
        nextPanelP1.add(nextBlockPaneP1);
        nextPanelP1.add(nextLabel1);
        backgroundPanel.add(nextPanelP2);
        nextPanelP2.add(nextBlockPaneP2);
        nextPanelP2.add(nextLabel2);

        backgroundPanel.add(scorePanelP1);
        scorePanelP1.add(scorePaneP1);
        scorePanelP1.add(scoreLabel1);
        backgroundPanel.add(scorePanelP2);
        scorePanelP2.add(scorePaneP2);
        scorePanelP2.add(scoreLabel2);

        backgroundPanel.add(attackPanelP1);
        attackPanelP1.add(attackPaneP1);
        attackPanelP1.add(attackLabel1);
        backgroundPanel.add(attackPanelP2);
        attackPanelP2.add(attackPaneP2);
        attackPanelP2.add(attackLabel2);

        backgroundPanel.add(itemCountPanelP1);
        itemCountPanelP1.add(itemCountPaneP1);
        itemCountPanelP1.add(itemcountLabel1);
        backgroundPanel.add(itemCountPanelP2);
        itemCountPanelP2.add(itemCountPaneP2);
        itemCountPanelP2.add(itemcountLabel2);

        backgroundPanel.add(timerPanel);
        timerPanel.add(timerPane);
        timerPanel.add(timerLabel);

        if (modeflag != 1){
            itemCountPanelP1.setVisible(false);
            itemCountPanelP2.setVisible(false);
        }
        if (modeflag != 2){
            timerPanel.setVisible(false);
        }

        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, FONT_SIZE);
        StyleConstants.setLineSpacing(styleSet, LINE_SPACING);
        StyleConstants.setFontFamily(styleSet, "Courier New");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        backgroundPanel.setFocusable(true);
        backgroundPanel.requestFocus();
        backgroundPanel.requestFocusInWindow();

        this.player1KeyListener = new Player1KeyListener();
        this.player2KeyListener = new Player2KeyListener();
        this.pauseKeyListener = new PauseKeyListener();
        this.endKeyListener = new EndKeyListener();
    }

    class Player1KeyListener implements KeyListener {
        private static final Logger logger = Logger.getLogger(BattleView.Player1KeyListener.class.getName());
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            long startTime = System.nanoTime();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_S:
                    battlecontroller.moveDown(true);
                    break;
                case KeyEvent.VK_D:
                    battlecontroller.moveRight(true);
                    break;
                case KeyEvent.VK_A:
                    battlecontroller.moveLeft(true);
                    break;
                case KeyEvent.VK_W:
                    battlecontroller.moveRotate(true);
                    break;
                case KeyEvent.VK_SHIFT:
                    if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT) {
                        battlecontroller.moveStraightDown(true);
                        break;
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    battlecontroller.gameStop();
                    highlightPauseButton(buttoncount);
                    break;

            }
            long endTime = System.nanoTime();
            long responseTime = endTime - startTime;
            logger.info("Response Time (ns): " + responseTime);
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    class Player2KeyListener implements KeyListener {
        private static final Logger logger = Logger.getLogger(BattleView.Player2KeyListener.class.getName());
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            long startTime = System.nanoTime();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    battlecontroller.moveDown(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    battlecontroller.moveRight(false);
                    break;
                case KeyEvent.VK_LEFT:
                    battlecontroller.moveLeft(false);
                    break;
                case KeyEvent.VK_UP:
                    battlecontroller.moveRotate(false);
                    break;
                case KeyEvent.VK_SHIFT:
                    if(e.getKeyLocation() == KeyEvent.KEY_LOCATION_RIGHT) {
                        battlecontroller.moveStraightDown(false);
                        break;
                    }
                    break;

                case KeyEvent.VK_P:
                battlecontroller.gameStop();
                highlightPauseButton(buttoncount);
                    break;
            }
            long endTime = System.nanoTime();
            long responseTime = endTime - startTime;
            logger.info("Response Time (ns): " + responseTime);
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }

    class PauseKeyListener implements KeyListener {     //일단은 기본키로 설정, 설정과 연동해서 키 값 받아와야함
        private static final Logger logger = Logger.getLogger(BattleView.PauseKeyListener.class.getName());
        @Override
        public void keyTyped(final KeyEvent e) {}
        @Override
        public void keyPressed(final KeyEvent e) {
            long startTime = System.nanoTime();
             switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    battlecontroller.gameStart();
                    break;
                case KeyEvent.VK_LEFT:
                    buttoncount = !buttoncount;
                    highlightPauseButton(buttoncount);
                    break;
                case KeyEvent.VK_RIGHT:
                    buttoncount = !buttoncount;
                    highlightPauseButton(buttoncount);
                    break;
                case KeyEvent.VK_ENTER:
                    if (buttoncount){
                        battlecontroller.gameStart();
                        break;
                    }else {
                        exitGame();
                        break;
                    }
            }
            long endTime = System.nanoTime();
            long responseTime = endTime - startTime;
            logger.info("Response Time (ns): " + responseTime);
        }
        @Override
        public void keyReleased(final KeyEvent e) {}
    }

    class EndKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {}
        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                        exitGame();
                        break;
            }
        }
        @Override
        public void keyReleased(final KeyEvent e) {}
    }

    private void highlightPauseButton(boolean count){
        if (count){
            continueBtn.setBackground(Color.YELLOW);
            exitBtn.setBackground(Color.GRAY);
        }else {
            continueBtn.setBackground(Color.GRAY);
            exitBtn.setBackground(Color.YELLOW);
        }
    }

    public void startPlayerKeyListen(boolean isPlayer1) {
        if(isPlayer1) backgroundPanel.addKeyListener(player1KeyListener);
        else backgroundPanel.addKeyListener(player2KeyListener);
    }

    public void stopPlayerKeyListen(boolean isPlayer1) {
        if(isPlayer1) {
            backgroundPanel.removeKeyListener(player1KeyListener);
        }
        else {
            backgroundPanel.removeKeyListener(player2KeyListener);
        }
    }

    public void startPauseKeyListen() {
        backgroundPanel.addKeyListener(pauseKeyListener);
    }

    public void stopPauseKeyListen() {
        backgroundPanel.removeKeyListener(pauseKeyListener);
    }

    public void startEndKeyListen() {
        backgroundPanel.addKeyListener(endKeyListener);
    }

    public void setVisiblePauseDialog(boolean ifVisible) {
        pauseDialog.setVisible(ifVisible);
    }

    public void exitGame(){
        setVisible(false);
        homeController = new HomeController();
        homeController.setVisible(true);
    }

    public final void drawBoard(final ArrayList<Element[]> board, boolean isPlayer1) {
        if(!isPlayer1) {
            boardPaneP2.setText("");
            Style style = boardPaneP2.addStyle("textStyle", null);
            StyledDocument doc = boardPaneP2.getStyledDocument();

            try {
                for (int i = 0; i < board.size() + 2; i++) {
                    for (int j = 0; j < board.get(0).length + 2; j++) {
                        boolean isBorder = i == 0 || i == board.size() + 1 || j == 0 || j == board.get(0).length + 1;
                        if (isBorder) {
                            StyleConstants.setForeground(style, Element.getElementColor(Element.BORDER));
                            doc.insertString(doc.getLength(), Element.getElementText(Element.BORDER), style);
                        } else {
                            StyleConstants.setForeground(style, Element.getElementColor(board.get(i - 1)[j - 1]));
                            doc.insertString(doc.getLength(), Element.getElementText(board.get(i - 1)[j - 1]), style);
                        }
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {
            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            boardPaneP2.setStyledDocument(doc);
        } else {
            boardPaneP1.setText("");
            Style style = boardPaneP1.addStyle("textStyle", null);
            StyledDocument doc = boardPaneP1.getStyledDocument();

            try {
                for (int i = 0; i < board.size() + 2; i++) {
                    for (int j = 0; j < board.get(0).length + 2; j++) {
                        boolean isBorder = i == 0 || i == board.size() + 1 || j == 0 || j == board.get(0).length + 1;
                        if (isBorder) {
                            StyleConstants.setForeground(style, Element.getElementColor(Element.BORDER));
                            doc.insertString(doc.getLength(), Element.getElementText(Element.BORDER), style);
                        } else {
                            StyleConstants.setForeground(style, Element.getElementColor(board.get(i - 1)[j - 1]));
                            doc.insertString(doc.getLength(), Element.getElementText(board.get(i - 1)[j - 1]), style);
                        }
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {
            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            boardPaneP1.setStyledDocument(doc);
        }
    }

    public final void drawNextBlock(Block nextBlock, boolean isPlayer1) {
        if(!isPlayer1) {
            nextBlockPaneP2.setText("");
            Style style = nextBlockPaneP2.addStyle("textStyle", null);
            StyledDocument doc = nextBlockPaneP2.getStyledDocument();

            try {
                for (int i = 0; i < nextBlock.width(); i++) {
                    for (int j = 0; j < nextBlock.height(); j++) {
                        Element currentElement = nextBlock.getShape(i, j);
                        StyleConstants.setForeground(style, Element.getElementColor(currentElement));
                        doc.insertString(doc.getLength(), Element.getElementText(currentElement), style);
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {

            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            nextBlockPaneP2.setStyledDocument(doc);
        } else {
            nextBlockPaneP1.setText("");
            Style style = nextBlockPaneP1.addStyle("textStyle", null);
            StyledDocument doc = nextBlockPaneP1.getStyledDocument();

            try {
                for (int i = 0; i < nextBlock.width(); i++) {
                    for (int j = 0; j < nextBlock.height(); j++) {
                        Element currentElement = nextBlock.getShape(i, j);
                        StyleConstants.setForeground(style, Element.getElementColor(currentElement));
                        doc.insertString(doc.getLength(), Element.getElementText(currentElement), style);
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {

            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            nextBlockPaneP1.setStyledDocument(doc);
        }
    }

    public final void drawScore(double score, boolean isPlayer1) {
        if(!isPlayer1) {
            scorePaneP2.setText("");
            Style style = scorePaneP2.addStyle("textStyle", null);
            StyledDocument doc = scorePaneP2.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, (int)(24 * Size));
            try {
                doc.insertString(doc.getLength(), Integer.toString((int) score), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            scorePaneP2.setStyledDocument(doc);
        } else {
            scorePaneP1.setText("");
            Style style = scorePaneP1.addStyle("textStyle", null);
            StyledDocument doc = scorePaneP1.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, (int)(24 * Size));
            try {
                doc.insertString(doc.getLength(), Integer.toString((int) score), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            scorePaneP1.setStyledDocument(doc);
        }
    }

    public final void drawItemCount(int itemCount, boolean isPlayer1) {
        if(!isPlayer1) {
            itemCountPaneP2.setText("");
            Style style = itemCountPaneP2.addStyle("textStyle", null);
            StyledDocument doc = itemCountPaneP2.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, (int)(30 * Size));
            try {
                doc.insertString(doc.getLength(), Integer.toString(itemCount), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            itemCountPaneP2.setStyledDocument(doc);
        } else {
            itemCountPaneP1.setText("");
            Style style = itemCountPaneP1.addStyle("textStyle", null);
            StyledDocument doc = itemCountPaneP1.getStyledDocument();
            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            StyleConstants.setForeground(style, Color.WHITE);
            StyleConstants.setFontSize(style, (int)(30 * Size));
            try {
                doc.insertString(doc.getLength(), Integer.toString(itemCount), style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            itemCountPaneP1.setStyledDocument(doc);
        }

    }

    public final void drawTimer(int second) {
        timerPane.setText("");
        Style style = timerPane.addStyle("textStyle", null);
        StyledDocument doc = timerPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 25);
        try {
            doc.insertString(doc.getLength(), Integer.toString(second/60) + ":" + Integer.toString(second%60), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        timerPane.setStyledDocument(doc);
    }

    public final void drawAttack(ArrayList<Element[]> attack, boolean isPlayer1) {
        if(attack.size() != 0) System.out.println(attack);
        if(isPlayer1) {
            attackPaneP1.setText("");
            Style style = attackPaneP1.addStyle("textStyle", null);
            StyledDocument doc = attackPaneP1.getStyledDocument();
            StyleConstants.setFontSize(style, 12);

            try {
                for (int i = 0; i < attack.size(); i++) {
                    for (int j = 0; j < attack.get(0).length; j++) {
                        StyleConstants.setForeground(style, Element.getElementColor(attack.get(i)[j]));
                        doc.insertString(doc.getLength(), Element.getElementText(attack.get(i)[j]), style);
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {
            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            attackPaneP1.setStyledDocument(doc);
        } else {
            attackPaneP2.setText("");
            Style style = attackPaneP2.addStyle("textStyle", null);
            StyledDocument doc = attackPaneP2.getStyledDocument();
            StyleConstants.setFontSize(style, 12);

            try {
                for (int i = 0; i < attack.size(); i++) {
                    for (int j = 0; j < attack.get(0).length; j++) {
                        StyleConstants.setForeground(style, Element.getElementColor(attack.get(i)[j]));
                        doc.insertString(doc.getLength(), Element.getElementText(attack.get(i)[j]), style);
                    }
                    doc.insertString(doc.getLength(), "\n", style);
                }
            } catch (BadLocationException e) {
            }

            doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
            attackPaneP2.setStyledDocument(doc);
        }
    }

    public void setVisibleFinishDialog(boolean ifVisible) {
        finishDialog.setVisible(ifVisible);
    }

    public final void drawFinishDialog(int player1Score, int player2Score, boolean bPlayer1) {
        resultPane.setText("");
        Style style = resultPane.addStyle("textStyle", null);
        StyledDocument doc = resultPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, (int)(28 * Size));
        try {
            if (gamemodeflag == 2 && battlecontroller.seconds == 0){
                if(player1Score > player2Score) doc.insertString(doc.getLength(), "Player1 Win!", style);
                else doc.insertString(doc.getLength(), "Player2 Win!", style);
            } else {
                if (bPlayer1){
                    doc.insertString(doc.getLength(), "Player2 Win!", style);
                } else {
                    doc.insertString(doc.getLength(), "Player1 Win!", style);
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        resultPane.setStyledDocument(doc);
    }

    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize){
            case LARGE -> {
                Size = 1.5;
                break;
            }
            case MEDIUM -> {
                Size = 1.25;
                break;
            }
            case SMALL -> {
                Size = 1;
                break;
            }
        }
    }

    public void setSize() {
        getsize(ConfigModel.boardSize);   //설정에서 받아와야함
        if (Size == 1) {
            VIEW_WIDTH = 800;
            VIEW_HEIGHT = 600;
            FONT_SIZE = 28;
            LINE_SPACING = -0.45f;
            PANE_WIDTH = 70;
            PANE_HEIGHT = 75;
            LABEL_FONT = 12;
        } else if (Size == 1.25){
            VIEW_WIDTH = 1000;
            VIEW_HEIGHT = 750;
            FONT_SIZE = 35;
            LINE_SPACING = -0.43f;
            PANE_WIDTH = 87;
            PANE_HEIGHT = 94;
            LABEL_FONT = 15;
        } else if (Size == 1.5) {
            VIEW_WIDTH = 1200;
            VIEW_HEIGHT = 900;
            FONT_SIZE = 42;
            LINE_SPACING = -0.41f;
            PANE_WIDTH = 105;
            PANE_HEIGHT = 112;
            LABEL_FONT = 18;
        }
    }
}
