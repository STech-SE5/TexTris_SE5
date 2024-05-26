package org.Stech.SE5.View;

import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Main;
import org.Stech.SE5.Model.ConfigModel;
//import tetris.model.RecordModel;
import org.Stech.SE5.Controller.ConfigController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConfigView extends JFrame {

    // Panel and Label for border size
    private JPanel borderSizeSetPanel;
    private JLabel borderSizeSetLabel;

    // Labels for color blindness, key labels
    private JLabel colorBlindnessLabel, downKeyLabel, leftKeyLabel, rightKeyLabel, dropKeyLabel, rotationKeyLabel;

    // Text panes for key settings
    private JTextPane downKeyPane, leftKeyPane, rightKeyPane, dropKeyPane, rotationKeyPane;

    // Last key event and key bind listener
    private KeyEvent lastKeyEvent;
    private KeyBindListener keyBindListener;

    private double Size;

    public ConfigView(final ConfigController presenter) {
        super("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getsize(ConfigModel.boardSize);

        JPanel configPanel = new JPanel();

        configPanel.setLayout(null);
        configPanel.setBackground(Color.black);

        // Set text label for border size
        borderSizeSetLabel = new JLabel("Border Size");
        borderSizeSetLabel.setText("Border Size");
        borderSizeSetLabel.setForeground(Color.white);
        borderSizeSetLabel.setBounds((int) (130 * Size), (int) (75 * Size), (int) (50 * Size), (int) (20 * Size));

        // Set panel for containing texts
        borderSizeSetPanel = new JPanel();
        borderSizeSetPanel.setBackground(Color.black);
        borderSizeSetPanel.add(borderSizeSetLabel);

        JButton smallBtn = new JButton("Small");
        JButton mediumBtn = new JButton("Medium");
        JButton largeBtn = new JButton("Large");

        colorBlindnessLabel = new JLabel("Color Blindness");
        colorBlindnessLabel.setText("Color Blindness");
        colorBlindnessLabel.setForeground(Color.white);
        JButton colorBlindToggleBtn = new JButton("Colorblind");

        downKeyLabel = new JLabel("Down Key");
        downKeyLabel.setText("Down Key");
        downKeyLabel.setForeground(Color.white);
        JButton setDownKeyButton = new JButton("Set");

        leftKeyLabel = new JLabel("Left Key");
        leftKeyLabel.setText("Left Key");
        leftKeyLabel.setForeground(Color.white);
        JButton setLeftKeyButton = new JButton("Set");

        rightKeyLabel = new JLabel("Right Key");
        rightKeyLabel.setText("Right Key");
        rightKeyLabel.setForeground(Color.white);
        JButton setRightKeyBtn = new JButton("Set");

        dropKeyLabel = new JLabel("Drop Key");
        dropKeyLabel.setText("Drop Key");
        dropKeyLabel.setForeground(Color.white);
        JButton setDropKeyBtn = new JButton("Set");

        rotationKeyLabel = new JLabel("Rotation Key");
        rotationKeyLabel.setText("Rotation Key");
        rotationKeyLabel.setForeground(Color.white);
        JButton setRotateKeyBtn = new JButton("Set");

        JButton initializeRecordBtn = new JButton("Initialize Record");
        JButton initializeSettingBtn = new JButton("Initialize Setting");
        JButton exit = new JButton("EXIT");

        smallBtn.setForeground(Color.WHITE);
        mediumBtn.setForeground(Color.WHITE);
        largeBtn.setForeground(Color.WHITE);
        colorBlindToggleBtn.setForeground(Color.WHITE);
        setDownKeyButton.setForeground(Color.WHITE);
        setLeftKeyButton.setForeground(Color.WHITE);
        setRightKeyBtn.setForeground(Color.WHITE);
        setDropKeyBtn.setForeground(Color.WHITE);
        setRotateKeyBtn.setForeground(Color.WHITE);
        initializeRecordBtn.setForeground(Color.WHITE);
        initializeSettingBtn.setForeground(Color.WHITE);

        smallBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray, 2)));
        smallBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray);
        smallBtn.setContentAreaFilled(false);
        smallBtn.setBounds((int) (172 * Size), (int) (126 * Size), (int) (50 * Size), (int) (33 * Size));
        smallBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.SMALL);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            smallBtn.setForeground(Color.white);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            mediumBtn.setForeground(Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            largeBtn.setForeground(Color.gray);
            setFocusable(true);
            requestFocus();
        });

        mediumBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray, 2)));
        mediumBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray);
        mediumBtn.setContentAreaFilled(false);
        mediumBtn.setBackground(new Color(0, 0, 0, ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? 0 : 122));
        mediumBtn.setBounds((int) (230 * Size), (int) (126 * Size), (int) (50 * Size), (int) (33 * Size));
        mediumBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.MEDIUM);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            smallBtn.setForeground(Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            mediumBtn.setForeground(Color.white);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            largeBtn.setForeground(Color.gray);
            setFocusable(true);
            requestFocus();
        });

        largeBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray, 2)));
        largeBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray);
        largeBtn.setContentAreaFilled(false);
        largeBtn.setBounds((int) (288 * Size), (int) (126 * Size), (int) (50 * Size), (int) (33 * Size));
        largeBtn.addActionListener(e -> {
            ConfigModel.changeBoardSize(ConfigModel.BoardSize.LARGE);
            smallBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            smallBtn.setForeground(Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(Color.gray)));
            mediumBtn.setForeground(Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(Color.white)));
            largeBtn.setForeground(Color.white);
            setFocusable(true);
            requestFocus();
        });

        colorBlindToggleBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray, 2)));
        colorBlindToggleBtn.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
        colorBlindToggleBtn.setContentAreaFilled(false);
        colorBlindToggleBtn.setBounds((int) (226 * Size), (int) (168 * Size), (int) (112 * Size), (int) (33 * Size));
        colorBlindToggleBtn.addActionListener(e -> {
            ConfigModel.changeColorBlindMode(!ConfigModel.colorBlindMode);
            colorBlindToggleBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray, 2)));
            colorBlindToggleBtn.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
            setFocusable(true);
            requestFocus();
        });

        keyBindListener = new KeyBindListener();
        addKeyListener(keyBindListener);
        keyBindListener.keyPressed(lastKeyEvent);

        setDownKeyButton.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        setDownKeyButton.setContentAreaFilled(false);
        setDownKeyButton.setBounds((int) (279 * Size), (int) (243 * Size), (int) (60 * Size), (int) (33 * Size));
        setDownKeyButton.addActionListener(e -> {

            // Print lastKeyEvent to console when down key setting button is pressed
            System.out.println("Down key set: " + lastKeyEvent);

            if (lastKeyEvent == null) return;

            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.DOWN, lastKeyEvent);
            downKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setLeftKeyButton.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        setLeftKeyButton.setContentAreaFilled(false);
        setLeftKeyButton.setBounds((int) (279 * Size), (int) (284 * Size), (int) (60 * Size), (int) (33 * Size));
        setLeftKeyButton.addActionListener(e -> {

            // Print lastKeyEvent to console when left key setting button is pressed
            System.out.println("Left key set: " + lastKeyEvent);
            if (lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.LEFT, lastKeyEvent);
            leftKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setRightKeyBtn.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        setRightKeyBtn.setContentAreaFilled(false);
        setRightKeyBtn.setBounds((int) (279 * Size), (int) (325 * Size), (int) (60 * Size), (int) (33 * Size));
        setRightKeyBtn.addActionListener(e -> {
            System.out.println("Right key set: " + lastKeyEvent);
            if (lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.RIGHT, lastKeyEvent);
            rightKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setDropKeyBtn.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        setDropKeyBtn.setContentAreaFilled(false);
        setDropKeyBtn.setBounds((int) (279 * Size), (int) (366 * Size), (int) (60 * Size), (int) (33 * Size));
        setDropKeyBtn.addActionListener(e -> {
            System.out.println("Drop key set: " + lastKeyEvent);
            if (lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.DROP, lastKeyEvent);
            dropKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        setRotateKeyBtn.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        setRotateKeyBtn.setContentAreaFilled(false);
        setRotateKeyBtn.setBounds((int) (279 * Size), (int) (407 * Size), (int) (60 * Size), (int) (33 * Size));
        setRotateKeyBtn.addActionListener(e -> {
            System.out.println("Rotate key set: " + lastKeyEvent);
            if (lastKeyEvent == null) return;
            ConfigModel.changeKeyBinding(ConfigModel.PlayerKey.ROTATE, lastKeyEvent);
            rotationKeyPane.setText(KeyEvent.getKeyText(lastKeyEvent.getKeyCode()));
            setFocusable(true);
            requestFocus();
        });

        initializeSettingBtn.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        initializeSettingBtn.setContentAreaFilled(false);
        initializeSettingBtn.setBounds((int) (208 * Size), (int) (488 * Size), (int) (130 * Size), (int) (33 * Size));
        initializeSettingBtn.addActionListener(e -> {
            ConfigModel.initConfig();
            smallBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray, 2)));
            smallBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.SMALL ? Color.white : Color.gray);
            mediumBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray, 2)));
            mediumBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.MEDIUM ? Color.white : Color.gray);
            largeBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray, 2)));
            largeBtn.setForeground(ConfigModel.boardSize == ConfigModel.BoardSize.LARGE ? Color.white : Color.gray);
            colorBlindToggleBtn.setBorder(new TitledBorder(new LineBorder(ConfigModel.colorBlindMode ? Color.white : Color.gray, 2)));
            colorBlindToggleBtn.setForeground(ConfigModel.colorBlindMode ? Color.white : Color.gray);
            downKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));
            leftKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));
            rightKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));
            dropKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));
            rotationKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));
            setFocusable(true);
            requestFocus();
        });

        exit.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        exit.setContentAreaFilled(false);
        exit.setBounds((int) (343 * Size), (int) (10 * Size), (int) (30 * Size), (int) (30 * Size));

        exit.addActionListener(e -> {
            HomeController homeController = new HomeController();
            homeController.setVisible(true);
            setVisible(false);
        });

        // pane

        downKeyPane = new JTextPane();
        downKeyPane.setEditable(false);
        downKeyPane.setBackground(Color.black);
        downKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        downKeyPane.setBounds((int) (144 * Size), (int) (243 * Size), (int) (115 * Size), (int) (33 * Size));
        downKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));

        leftKeyPane = new JTextPane();
        leftKeyPane.setEditable(false);
        leftKeyPane.setBackground(Color.black);
        leftKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        leftKeyPane.setBounds((int) (144 * Size), (int) (284 * Size), (int) (115 * Size), (int) (33 * Size));
        leftKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));

        rightKeyPane = new JTextPane();
        rightKeyPane.setEditable(false);
        rightKeyPane.setBackground(Color.black);
        rightKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        rightKeyPane.setBounds((int) (144 * Size), (int) (325 * Size), (int) (115 * Size), (int) (33 * Size));
        rightKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));

        dropKeyPane = new JTextPane();
        dropKeyPane.setEditable(false);
        dropKeyPane.setBackground(Color.black);
        dropKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        dropKeyPane.setBounds((int) (144 * Size), (int) (366 * Size), (int) (115 * Size), (int) (33 * Size));
        dropKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));

        rotationKeyPane = new JTextPane();
        rotationKeyPane.setEditable(false);
        rotationKeyPane.setBackground(Color.black);
        rotationKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        rotationKeyPane.setBounds((int) (144 * Size), (int) (407 * Size), (int) (115 * Size), (int) (33 * Size));
        rotationKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

        SimpleAttributeSet textPaneStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(textPaneStyle, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(textPaneStyle, 20);
        StyledDocument doc = downKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = leftKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rightKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = dropKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rotationKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        downKeyPane.setForeground(Color.WHITE);
        leftKeyPane.setForeground(Color.WHITE);
        rightKeyPane.setForeground(Color.WHITE);
        dropKeyPane.setForeground(Color.WHITE);
        rotationKeyPane.setForeground(Color.WHITE);

        this.setContentPane(configPanel);

        configPanel.add(smallBtn);
        configPanel.add(mediumBtn);
        configPanel.add(largeBtn);
        configPanel.add(colorBlindToggleBtn);
        configPanel.add(setDownKeyButton);
        configPanel.add(setLeftKeyButton);
        configPanel.add(setRightKeyBtn);
        configPanel.add(setDropKeyBtn);
        configPanel.add(setRotateKeyBtn);
        configPanel.add(initializeRecordBtn);
        configPanel.add(initializeSettingBtn);
        configPanel.add(exit);

        configPanel.add(borderSizeSetPanel);

        configPanel.add(downKeyPane);
        configPanel.add(leftKeyPane);
        configPanel.add(rightKeyPane);
        configPanel.add(dropKeyPane);
        configPanel.add(rotationKeyPane);

        keyBindListener = new KeyBindListener();
        addKeyListener(keyBindListener);
        setFocusable(true);
        requestFocus();
    }

    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize) {
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

    class KeyBindListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {

        }

        @Override
        public void keyPressed(final KeyEvent e) {
            lastKeyEvent = e;
        }

        @Override
        public void keyReleased(final KeyEvent e) {

        }
    }
}
