package org.Stech.SE5.View;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.Element;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends JFrame {

    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;
    static final int FONT_SIZE = 28;
    static final float LINE_SPACING = -0.45f;
    private JTextPane boardPane;
    private JTextPane nextBlockPane;
    private JTextPane scorePane;
    private JTextPane levelPane;
    private JTextPane deletedRawPane;

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

        pauseDialog.setBounds(100, 200, 200, 100);      //이새끼 똑바로 안됨 나중에 확인
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setOpaque(false);

        JButton continueBtn = new JButton("Continue");
        continueBtn.setBounds(10, 30, 80, 40);
        continueBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        continueBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        continueBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        continueBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        continueBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(110, 30, 80, 40);
        exitBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        exitBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        exitBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        exitBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        exitBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        //continueBtn.addActionListener(e -> ); 나중에 기능 만들고 연결시키기
        //exitBtn.addActionListener(e -> );

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
        pauseDialog.add(continueBtn);
        pauseDialog.add(exitBtn);

        styleSet = new SimpleAttributeSet();
        StyleConstants.setFontSize(styleSet, FONT_SIZE);
        StyleConstants.setLineSpacing(styleSet, LINE_SPACING);
        StyleConstants.setFontFamily(styleSet, "Courier New");
        StyleConstants.setBold(styleSet, true);
        StyleConstants.setAlignment(styleSet, StyleConstants.ALIGN_CENTER);

        boardPane.setFocusable(true);
        boardPane.requestFocus();
        boardPane.requestFocusInWindow();
    }

    public void setVisiblePauseDialog(boolean ifVisible) {
        pauseDialog.setVisible(ifVisible);
    }

    public final void drawBoard(final ArrayList<Element[]> board) {
        boardPane.setText("");
        Style style = boardPane.addStyle("textStyle", null);
        StyledDocument doc = boardPane.getStyledDocument();

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
        boardPane.setStyledDocument(doc);
    }

    public final void drawNextBlock(Block nextBlock) {
        nextBlockPane.setText("");
        Style style = nextBlockPane.addStyle("textStyle", null);
        StyledDocument doc = nextBlockPane.getStyledDocument();

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
        nextBlockPane.setStyledDocument(doc);
    }

    public final void drawScore(double score) {
        scorePane.setText("");
        Style style = scorePane.addStyle("textStyle", null);
        StyledDocument doc = scorePane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), Integer.toString((int) score), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        scorePane.setStyledDocument(doc);
    }

    public final void drawLevel() {
        levelPane.setText("");
        Style style = levelPane.addStyle("textStyle", null);
        StyledDocument doc = levelPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 12);
        try {
            doc.insertString(doc.getLength(),"NORMAL", style);  //난이도 추후 설정부분에서 받아와서 적용하도록 변경해야함
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        levelPane.setStyledDocument(doc);
    }

    public final void drawDeletedRaw(int deletedRaw) {
        deletedRawPane.setText("");
        Style style = deletedRawPane.addStyle("textStyle", null);
        StyledDocument doc = deletedRawPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), Integer.toString(deletedRaw), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        deletedRawPane.setStyledDocument(doc);
    }
}
