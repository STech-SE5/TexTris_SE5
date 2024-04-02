package org.Stech.SE5.View;

import javax.swing.*;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;

public class GameView extends JFrame {

    static final int LINE_BORDER_OUTER_WEIGHT = 10;
    static final int LINE_BORDER_INNER_WEIGHT = 5;
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;
    static final int FONT_SIZE = 28;
    static final float LINE_SPACING = -0.45f;
    private JTextPane boardPane;
    private JTextPane nextBlockPane;
    private JTextPane scorePane;
    private JTextPane levelPane;
    private JTextPane deletedRawPane;
    private JTextPane recordScorePane;
    private JTextField namePane;
    private SimpleAttributeSet styleSet;

    public GameView() {
        super("TETRIS");
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));
        add(backgroundPanel);

        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(null);
        nextPanel.setBounds(280, 54, 70, 120);
        nextPanel.setBackground(Color.GRAY); // 패널의 배경색을 회색으로 설정

        JLabel nextLabel = new JLabel("next", SwingConstants.CENTER);    // nextPanel에 "next" 텍스트를 추가합니다.
        nextLabel.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        nextLabel.setBounds(0, 0, 70, 20); // 라벨의 위치와 크기를 설정합니다.
        nextPanel.add(nextLabel);
        backgroundPanel.add(nextPanel);

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setBounds(280,260,70,75);
        scorePanel.setBackground(Color.GRAY);

        JLabel scoreLabel = new JLabel("score", SwingConstants.CENTER); // scorePanel에 "score" 텍스트를 추가합니다.
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(0, 0, 70, 20);
        scorePanel.add(scoreLabel);
        backgroundPanel.add(scorePanel);

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(null);
        levelPanel.setBounds(280,340,70,75);
        levelPanel.setBackground(Color.GRAY);

        JLabel levelLabel = new JLabel("level", SwingConstants.CENTER); // levelPanel에 "level" 텍스트를 추가합니다.
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setBounds(0, 0, 70, 20);
        levelPanel.add(levelLabel);
        backgroundPanel.add(levelPanel);

        JPanel linesPanel = new JPanel();
        linesPanel.setLayout(null);
        linesPanel.setBounds(280,420,70,75);
        linesPanel.setBackground(Color.GRAY);

        JLabel linesLabel = new JLabel("lines", SwingConstants.CENTER);  // linesPanel에 "lines" 텍스트를 추가합니다.
        linesLabel.setForeground(Color.WHITE);
        linesLabel.setBounds(0, 0, 70, 20);
        linesPanel.add(linesLabel);
        backgroundPanel.add(linesPanel);

        boardPane = new JTextPane();
        boardPane.setEditable(false);
        boardPane.setOpaque(false);
        boardPane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPane.setBounds(40,55, 220,440);
        backgroundPanel.add(boardPane);

    }

    JPanel backgroundPanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // 부모 클래스의 paintComponent 호출
            g.setColor(Color.BLACK); // 색상을 검은색으로 설정
            g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 패널 전체를 검은색으로 채움
        }
    };


    public static void main(String[] args) {
        GameView Game = new GameView();
        Game.setVisible(true);
    }
}
