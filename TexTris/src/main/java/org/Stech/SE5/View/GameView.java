package org.Stech.SE5.View;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.Element;
import org.Stech.SE5.Controller.GameController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameView extends JFrame {

    private  double Size;
    private  int VIEW_WIDTH;
    private  int VIEW_HEIGHT;
    static  int FONT_SIZE;
    static int LABEL_FONT;
    static  float LINE_SPACING;
    static  int PANE_WIDTH;
    static  int PANE_HEIGHT;
    private JTextPane boardPane;
    private JTextPane nextBlockPane;
    private JTextPane scorePane;
    private JTextPane levelPane;
    private JTextPane itemCountPane;

    private SimpleAttributeSet styleSet;

    private GameController gamecontroller;
    private PlayerKeyListener playerKeyListener;
    private PauseKeyListener pauseKeyListener;

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

    public GameView(final GameController controller, boolean modeflag) {
        super("TETRIS");
        setSize();
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        Font labelFont = new Font("Arial", Font.PLAIN, LABEL_FONT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gamecontroller = controller;

        JPanel backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g); // 부모 클래스의 paintComponent 호출
                g.setColor(Color.BLACK); // 색상을 검은색으로 설정
                g.fillRect(0, 0, this.getWidth(), this.getHeight()); // 패널 전체를 검은색으로 채움
            }
        };
        backgroundPanel.setLayout(null);

        JPanel nextPanel = new JPanel();
        nextPanel.setLayout(null);
        nextPanel.setBounds((int)(280 * Size), (int)(54 * Size), PANE_WIDTH,(int)(PANE_HEIGHT * 1.6));
        nextPanel.setBackground(Color.GRAY); // 패널의 배경색을 회색으로 설정
        JLabel nextLabel = new JLabel("next", SwingConstants.CENTER);    // nextPanel에 "next" 텍스트를 추가합니다.
        nextLabel.setForeground(Color.WHITE); // 텍스트 색상을 설정합니다.
        nextLabel.setFont(labelFont); // 생성한 Font 객체를 JLabel에 설정
        nextLabel.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size)); // 라벨의 위치와 크기를 설정합니다.

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(null);
        scorePanel.setBounds((int)(280 * Size),(int)(260 * Size),PANE_WIDTH,PANE_HEIGHT);
        scorePanel.setBackground(Color.GRAY);
        JLabel scoreLabel = new JLabel("score", SwingConstants.CENTER); // scorePanel에 "score" 텍스트를 추가합니다.
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(labelFont);
        scoreLabel.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(null);
        levelPanel.setBounds((int)(280 * Size),(int)(340 * Size),PANE_WIDTH,PANE_HEIGHT);
        levelPanel.setBackground(Color.GRAY);
        JLabel levelLabel = new JLabel("level", SwingConstants.CENTER); // levelPanel에 "level" 텍스트를 추가합니다.
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(labelFont);
        levelLabel.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        JPanel itemcountpanel = new JPanel();
        itemcountpanel.setLayout(null);
        itemcountpanel.setBounds((int)(280 * Size),(int)(420 * Size),PANE_WIDTH,PANE_HEIGHT);
        itemcountpanel.setBackground(Color.GRAY);
        JLabel itemcountLabel = new JLabel("ItemCounts", SwingConstants.CENTER);  // linesPanel에 "lines" 텍스트를 추가합니다.
        itemcountLabel.setForeground(Color.WHITE);
        itemcountLabel.setFont(labelFont);
        itemcountLabel.setBounds(0, 0, (int)(70 * Size), (int)(20 * Size));

        boardPane = new JTextPane();
        boardPane.setEditable(false);
        boardPane.setOpaque(false);
        boardPane.setBorder(new TitledBorder(new LineBorder(Color.white,3)));
        boardPane.setBounds((int)(40 * Size),(int)(55 * Size), (int)(220 * Size),(int)(440 * Size));

        nextBlockPane = new JTextPane();
        nextBlockPane.setEditable(false);
        nextBlockPane.setOpaque(true);
        nextBlockPane.setBackground(Color.BLACK);
        nextBlockPane.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(95 * Size));

        scorePane = new JTextPane();
        scorePane.setEditable(false);
        scorePane.setOpaque(true);
        scorePane.setBackground(Color.BLACK);
        scorePane.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

        levelPane = new JTextPane();
        levelPane.setEditable(false);
        levelPane.setOpaque(true);
        levelPane.setBackground(Color.BLACK);
        levelPane.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

        itemCountPane = new JTextPane();
        itemCountPane.setEditable(false);
        itemCountPane.setOpaque(true);
        itemCountPane.setBackground(Color.BLACK);
        itemCountPane.setBounds((int)(5 * Size), (int)(20 * Size), (int)(60 * Size), (int)(50 * Size));

        pauseDialog.setBounds((int)(100 * Size), (int)(200 * Size), (int)(200 * Size), (int)(100 * Size));
        pauseDialog.setLayout(null);
        pauseDialog.setVisible(false);
        pauseDialog.setOpaque(true);

        JButton continueBtn = new JButton("Continue");
        continueBtn.setBounds((int)(10 * Size), (int)(30 * Size), (int)(80 * Size), (int)(40 * Size));
        continueBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        continueBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        continueBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        continueBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        continueBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds((int)(110 * Size), (int)(30 * Size), (int)(80 * Size), (int)(40 * Size));
        exitBtn.setBorderPainted(false); // 버튼 테두리를 그리지 않습니다.
        exitBtn.setContentAreaFilled(true); // 버튼 배경을 그립니다.
        exitBtn.setOpaque(true); // 불투명 설정을 통해 배경색이 보이게 합니다.
        exitBtn.setBackground(Color.GRAY); // 버튼 배경색을 회색으로 설정합니다.
        exitBtn.setForeground(Color.WHITE); // 버튼 텍스트 색상을 흰색으로 설정합니다.

        continueBtn.addActionListener(e -> gamecontroller.gameStart());
        //exitBtn.addActionListener(e -> 메인메뉴 불러오는 함수 );

        add(backgroundPanel);
        backgroundPanel.add(pauseDialog);
        backgroundPanel.add(nextPanel);
        nextPanel.add(nextBlockPane);
        nextPanel.add(nextLabel);

        backgroundPanel.add(scorePanel);
        scorePanel.add(scorePane);
        scorePanel.add(scoreLabel);

        backgroundPanel.add(levelPanel);
        levelPanel.add(levelPane);
        levelPanel.add(levelLabel);

        backgroundPanel.add(itemcountpanel);
        itemcountpanel.add(itemCountPane);
        itemcountpanel.add(itemcountLabel);

        if (!modeflag){
            itemcountpanel.setVisible(false);
        }

        backgroundPanel.add(boardPane);
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

        this.playerKeyListener = new PlayerKeyListener();
        this.pauseKeyListener = new PauseKeyListener();
    }

    class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {          //일단은 기본키로 설정, 설정과 연동해서 키 값 받아와야함
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                   gamecontroller.moveDown();
                   break;
                case KeyEvent.VK_RIGHT:
                    gamecontroller.moveRight();    //각 case에 맞는 이동 구현해서 넣어야함
                    break;
                case KeyEvent.VK_LEFT:
                    gamecontroller.moveLeft();
                    break;
                case KeyEvent.VK_UP:
                    gamecontroller.actRotate();
                    break;
                case KeyEvent.VK_SPACE:
                    gamecontroller.moveStraightDown();
                    break;
                case KeyEvent.VK_ESCAPE:
                    gamecontroller.gameStop();
                    break;
            }
        }
        @Override
        public void keyReleased(final KeyEvent e) {}
    }

    class PauseKeyListener implements KeyListener {     //일단은 기본키로 설정, 설정과 연동해서 키 값 받아와야함
        @Override
        public void keyTyped(final KeyEvent e) {}
        @Override
        public void keyPressed(final KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    gamecontroller.gameStart();
                    break;
            }
        }
        @Override
        public void keyReleased(final KeyEvent e) {}
    }

    public void startPlayerKeyListen() {
        boardPane.addKeyListener(this.playerKeyListener);
    }

    public void stopPlayerKeyListen() {
        boardPane.removeKeyListener(this.playerKeyListener);
    }

    public void startPauseKeyListen() {
        boardPane.addKeyListener(this.pauseKeyListener);
    }

    public void stopPauseKeyListen() {
        boardPane.removeKeyListener(this.pauseKeyListener);
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

    public void setSize() {
        Size = 1.5;   //설정에서 받아와야함
        if (Size == 1) {
            VIEW_WIDTH = 400;
            VIEW_HEIGHT = 600;
            FONT_SIZE = 28;
            LINE_SPACING = -0.45f;
            PANE_WIDTH = 70;
            PANE_HEIGHT = 75;
            LABEL_FONT = 12;
        } else if (Size == 1.25){
            VIEW_WIDTH = 500;
            VIEW_HEIGHT = 750;
            FONT_SIZE = 35;
            LINE_SPACING = -0.43f;
            PANE_WIDTH = 87;
            PANE_HEIGHT = 94;
            LABEL_FONT = 15;
        } else if (Size == 1.5) {
            VIEW_WIDTH = 600;
            VIEW_HEIGHT = 900;
            FONT_SIZE = 42;
            LINE_SPACING = -0.41f;
            PANE_WIDTH = 105;
            PANE_HEIGHT = 112;
            LABEL_FONT = 18;
        }
    }

    public final void drawItemCount(int ItemCount) {
        itemCountPane.setText("");
        Style style = itemCountPane.addStyle("textStyle", null);
        StyledDocument doc = itemCountPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), styleSet, false);
        StyleConstants.setForeground(style, Color.WHITE);
        StyleConstants.setFontSize(style, 24);
        try {
            doc.insertString(doc.getLength(), Integer.toString(ItemCount), style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        itemCountPane.setStyledDocument(doc);
    }
}
