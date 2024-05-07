package org.Stech.SE5.View;

import org.Stech.SE5.Controller.GameController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Model.ConfigModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BattleView extends JFrame{
    private HomeController homeController;
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
    private SimpleAttributeSet styleSet;

    private JPanel backgroundPanel;
    private GameController gamecontroller;

    //private Player1KeyListener player1KeyListener;        나중에 추가
    //private Player2KeyListener player2KeyListener;
    //private PauseKeyListener pauseKeyListener;

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

    public BattleView(/*final GameController controller, */int modeflag/*0,1,2 각 normal, item, time*/) {
        super("TETRIS");
        setSize();
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        Font labelFont = new Font("Arial", Font.PLAIN, LABEL_FONT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        attackPanelP1.setBounds((int)(280 * Size),(int)(340 * Size),PANE_WIDTH,PANE_HEIGHT);
        attackPanelP1.setBackground(Color.GRAY);
        JLabel attackLabel1 = new JLabel("attack", SwingConstants.CENTER); // attackPanel에 "level" 텍스트를 추가합니다.
        attackLabel1.setForeground(Color.WHITE);
        attackLabel1.setFont(labelFont);
        attackLabel1.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));
        attackPanelP2 = new JPanel();
        attackPanelP2.setLayout(null);
        attackPanelP2.setBounds((int)(680 * Size),(int)(340 * Size),PANE_WIDTH,PANE_HEIGHT);
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
        attackPaneP1.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));
        attackPaneP2 = new JTextPane();
        attackPaneP2.setEditable(false);
        attackPaneP2.setOpaque(true);
        attackPaneP2.setBackground(Color.BLACK);
        attackPaneP2.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

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

        pauseDialog.setBounds((int)(100 * Size), (int)(200 * Size), (int)(200 * Size), (int)(100 * Size));
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

        //continueBtn.addActionListener(e -> gamecontroller.gameStart());   나중에 추가
        //exitBtn.addActionListener(e -> exitGame());

        add(backgroundPanel);
        backgroundPanel.add(pauseDialog);
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
