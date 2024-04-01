package org.Stech.SE5.View;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;

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
    }

    public static void main(String[] args) {
        GameView Game = new GameView();
    }
}
