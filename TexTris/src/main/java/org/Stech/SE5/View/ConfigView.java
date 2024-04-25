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

    private JPanel speedSetPanel;
    private JPanel borderSizeSetPanel;
    private JPanel colorBlindnessPanel;
    private JPanel downKeyPanel;
    private JPanel leftKeyPanel;
    private JPanel rightKeyPanel;
    private JPanel dropKeyPanel;
    private JPanel rotationKeyPanel;

    //    private JLabel speedSetLabel;
    private JLabel borderSizeSetLabel;
    private JLabel colorBlindnessLabel;
    private JLabel downKeyLabel;
    private JLabel leftKeyLabel;
    private JLabel rightKeyLabel;
    private JLabel dropKeyLabel;
    private JLabel rotationKeyLabel;

    private JTextPane gameSpeedPane;
    private JTextPane downKeyPane;
    private JTextPane leftKeyPane;
    private JTextPane rightKeyPane;
    private JTextPane dropKeyPane;
    private JTextPane rotationKeyPane;
    private KeyEvent lastKeyEvent;
    private KeyBindListener keyBindListener;


    public ConfigView(final ConfigController presenter) {
        super("TETRIS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel configPanel = new JPanel();

        configPanel.setLayout(null);
        configPanel.setBackground(Color.black);

        // Set text label for border size
        borderSizeSetLabel = new JLabel("Border Size");
        borderSizeSetLabel.setText("Border Size");
        borderSizeSetLabel.setForeground(Color.white);
        borderSizeSetLabel.setBounds(130, 75, 50, 20);

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
        smallBtn.setBounds(172, 126, 50, 33);
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
        mediumBtn.setBounds(230, 126, 50, 33);
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
        largeBtn.setBounds(288, 126, 50, 33);
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
        colorBlindToggleBtn.setBounds(226, 168, 112, 33);
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
        setDownKeyButton.setBounds(279, 243, 60, 33);
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
        setLeftKeyButton.setBounds(279, 284, 60, 33);
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
        setRightKeyBtn.setBounds(279, 325, 60, 33);
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
        setDropKeyBtn.setBounds(279, 366, 60, 33);
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
        setRotateKeyBtn.setBounds(279, 407, 60, 33);
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
        initializeSettingBtn.setBounds(208, 488, 130, 33);
        initializeSettingBtn.addActionListener(e -> {
            ConfigModel.initConfig();
            gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));
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

        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setBounds(343, 10, 30, 30);


        exit.addActionListener(e -> {
            HomeController homeController = new HomeController();
            homeController.setVisible(true);
        });

        // pane
        gameSpeedPane = new JTextPane();
        gameSpeedPane.setEditable(false);
        gameSpeedPane.setBackground(Color.black);
        gameSpeedPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        gameSpeedPane.setBounds(170, 55, 70, 35);
        gameSpeedPane.setText(Double.toString(ConfigModel.gameSpeed));

        downKeyPane = new JTextPane();
        downKeyPane.setEditable(false);
        downKeyPane.setBackground(Color.black);
        downKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        downKeyPane.setBounds(144, 243, 115, 33);
        downKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));

        leftKeyPane = new JTextPane();
        leftKeyPane.setEditable(false);
        leftKeyPane.setBackground(Color.black);
        leftKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        leftKeyPane.setBounds(144, 284, 115, 33);
        leftKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));

        rightKeyPane = new JTextPane();
        rightKeyPane.setEditable(false);
        rightKeyPane.setBackground(Color.black);
        rightKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        rightKeyPane.setBounds(144, 325, 115, 33);
        rightKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));

        dropKeyPane = new JTextPane();
        dropKeyPane.setEditable(false);
        dropKeyPane.setBackground(Color.black);
        dropKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        dropKeyPane.setBounds(144, 366, 115, 33);
        dropKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));

        rotationKeyPane = new JTextPane();
        rotationKeyPane.setEditable(false);
        rotationKeyPane.setBackground(Color.black);
        rotationKeyPane.setBorder(new TitledBorder(new LineBorder(Color.white, 2)));
        rotationKeyPane.setBounds(144, 407, 115, 33);
        rotationKeyPane.setText(KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

        SimpleAttributeSet textPaneStyle = new SimpleAttributeSet();
        StyleConstants.setAlignment(textPaneStyle, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(textPaneStyle, 20);
        StyledDocument doc = gameSpeedPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = downKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = leftKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rightKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = dropKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        doc = rotationKeyPane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), textPaneStyle, false);

        gameSpeedPane.setForeground(Color.WHITE);
        downKeyPane.setForeground(Color.WHITE);
        leftKeyPane.setForeground(Color.WHITE);
        rightKeyPane.setForeground(Color.WHITE);
        dropKeyPane.setForeground(Color.WHITE);
        rotationKeyPane.setForeground(Color.WHITE);

        this.setContentPane(configPanel);
//        configPanel.add(upBtn);
//        configPanel.add(downBtn);

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
