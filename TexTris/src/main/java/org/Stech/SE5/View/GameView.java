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

    private JPanel pauseDialog = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // 패널의 기본 그리기 동작을 수행합니다.

            // 배경을 그리기 위해 검은색 설정
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 패널 전체를 검은색으로 채웁니다.

            // Graphics 객체를 Graphics2D 객체로 캐스팅하여 더 세밀한 그리기 옵션 사용
            Graphics2D g2 = (Graphics2D) g;

            // 테두리를 그리기 위해 회색 설정
            g2.setColor(Color.GRAY);
            // drawRect 메서드를 사용하여 테두리 그리기
            // 여기서는 테두리의 두께를 직접 조절할 수 없으므로, 테두리를 원하는 두께로 만들기 위해 여러 번 그릴 수 있습니다.
            // 예를 들어, 1픽셀 두께의 테두리를 원한다면 아래와 같이 하면 됩니다.
            g2.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1); // 테두리의 두께를 고려하여 -1
            // 더 두꺼운 테두리를 원하면, 반복문을 사용하여 여러 번 그릴 수 있습니다.
        }
    };

    public GameView() {
        super("TETRIS");
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // 부모 클래스의 paintComponent 호출
                g.setColor(Color.BLACK); // 색상을 검은색으로 설정
                g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 패널 전체를 검은색으로 채움
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));

        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(null);
        nextPanel.setBounds(280, 54, 70, 120);
        nextPanel.setBackground(Color.GRAY); // 패널의 배경색을 회색으로 설정
        JLabel nextLabel = new JLabel("next", SwingConstants.CENTER);    // nextPanel에 "next" 텍스트를 추가합니다.
        nextLabel.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        nextLabel.setBounds(0, 0, 70, 20); // 라벨의 위치와 크기를 설정합니다.

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setBounds(280,260,70,75);
        scorePanel.setBackground(Color.GRAY);
        JLabel scoreLabel = new JLabel("score", SwingConstants.CENTER); // scorePanel에 "score" 텍스트를 추가합니다.
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(0, 0, 70, 20);

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(null);
        levelPanel.setBounds(280,340,70,75);
        levelPanel.setBackground(Color.GRAY);
        JLabel levelLabel = new JLabel("level", SwingConstants.CENTER); // levelPanel에 "level" 텍스트를 추가합니다.
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setBounds(0, 0, 70, 20);

        JPanel linesPanel = new JPanel();
        linesPanel.setLayout(null);
        linesPanel.setBounds(280,420,70,75);
        linesPanel.setBackground(Color.GRAY);
        JLabel linesLabel = new JLabel("lines", SwingConstants.CENTER);  // linesPanel에 "lines" 텍스트를 추가합니다.
        linesLabel.setForeground(Color.WHITE);
        linesLabel.setBounds(0, 0, 70, 20);

        boardPane = new JTextPane();
        boardPane.setEditable(false);
        boardPane.setOpaque(false);
        boardPane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPane.setBounds(40,55, 220,440);

        nextBlockPane = new JTextPane();
        nextBlockPane.setEditable(false);
        nextBlockPane.setOpaque(true);
        nextBlockPane.setBackground(Color.BLACK);
        nextBlockPane.setBounds(5, 20, 60, 95);

        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setOpaque(true);
        scorePane.setBackground(Color.BLACK);
        scorePane.setBounds(5, 20, 60, 50);

        levelPane = new JTextPane();
        levelPane.setEditable(false);
        levelPane.setOpaque(true);
        levelPane.setBackground(Color.BLACK);
        levelPane.setBounds(5, 20, 60, 50);

        deletedRawPane = new JTextPane();
        deletedRawPane.setEditable(false);
        deletedRawPane.setOpaque(true);
        deletedRawPane.setBackground(Color.BLACK);
        deletedRawPane.setBounds(5, 20, 60, 50);

        pauseDialog.setBounds(100, 200, 200, 100);
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setOpaque(false);

        add(backgroundPanel);
        backgroundPanel.add(nextPanel);
        nextPanel.add(nextBlockPane);
        nextPanel.add(nextLabel);

        backgroundPanel.add(scorePanel);
        scorePanel.add(scorePane);
        scorePanel.add(scoreLabel);

        backgroundPanel.add(levelPanel);
        levelPanel.add(levelPane);
        levelPanel.add(levelLabel);

        backgroundPanel.add(linesPanel);
        linesPanel.add(deletedRawPane);
        linesPanel.add(linesLabel);

        backgroundPanel.add(boardPane);
        backgroundPanel.add(pauseDialog);
    }


    public static void main(String[] args) {
        GameView Game = new GameView();
        Game.setVisible(true);
    }
}
