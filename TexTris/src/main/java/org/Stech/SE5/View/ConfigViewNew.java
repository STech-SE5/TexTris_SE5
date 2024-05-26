package org.Stech.SE5.View;

import org.Stech.SE5.Controller.ConfigController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.ConfigViews.KeyListenConfigView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ConfigViewNew extends JFrame {
    private int focusedButtonIndex = 0;
    private int columns = 2; // Number of the columns

    private static final long serialVersionUID = 1L;
    private int buttonPtrIndex; // buttonList의 인덱스를 가리킬 변수

    private ConfigModel configModel = new ConfigModel();
    private ConfigController configController;

    // Key Bindings
    private KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
    private KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
    private KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
    private KeyStroke dropKey = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
    private KeyStroke rotateKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);

    // buttonList
    private JButton goToHomeButton = new JButton("Go Previous");
    private JButton bordersizeButton = new JButton("Border Size");

    // Key Setting
    // Show button names and their current key bindings - 1P
    private JButton setDownKeyButton = new JButton("1P Down Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));
    private JButton setLeftKeyButton = new JButton("1P Left Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));
    private JButton setRightKeyButton = new JButton("1P Right Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));
    private JButton setDropKeyButton = new JButton("1P Drop Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));
    private JButton setRotateKeyButton = new JButton("1P Rotate Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

    // Show button names and their current key bindings
    private JButton setDownKeyButton2P = new JButton("2P Down Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN_2P.ordinal()]));
    private JButton setLeftKeyButton2P = new JButton("2P Left Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT_2P.ordinal()]));
    private JButton setRightKeyButton2P = new JButton("2P Right Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT_2P.ordinal()]));
    private JButton setDropKeyButton2P = new JButton("2P Drop Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP_2P.ordinal()]));
    private JButton setRotateKeyButton2P = new JButton("2P Rotate Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE_2P.ordinal()]));

    // UI Setting

    private JButton boarderSizeSettingButton = new JButton("Boardsize: ");

    // Colorblind Mode Toggle
    private JButton colorBlindToggleButton = new JButton("Colorblind: Deactivated");


    // Initialize Settings
    private JButton initializeSettingsButton = new JButton("Initialize Settings");
    
    private final ArrayList<JButton> buttonList; // 만든 버튼을 저장할 ArrayList

    public ConfigViewNew(final ConfigController controller) {
        setTitle("Tetris Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));
        setSize(300, 400);

        // Initialize button array for navigation

        // You should consider an existence of JToggleButton: colorBlindToggleButton

        buttonList = new ArrayList<>(); // Remove the empty square brackets

        // 홈으로 이동
        buttonList.add(goToHomeButton);

        // 창 크 기설정
        buttonList.add(bordersizeButton);

        // 게임 플레이 키보드 키 설정 - 1P
        buttonList.add(setDownKeyButton);
        buttonList.add(setLeftKeyButton);
        buttonList.add(setRightKeyButton);
        buttonList.add(setDropKeyButton);
        buttonList.add(setRotateKeyButton);

        // 게임 플레이 키보드 키 설정 - 2P
        buttonList.add(setDownKeyButton2P);
        buttonList.add(setLeftKeyButton2P);
        buttonList.add(setRightKeyButton2P);
        buttonList.add(setDropKeyButton2P);
        buttonList.add(setRotateKeyButton2P);

        // 색맹 버튼
        buttonList.add(colorBlindToggleButton);

        // 초기화
        buttonList.add(initializeSettingsButton);

        goToHomeButton.addActionListener(e -> {
            setVisible(false);
            HomeController homeController = new HomeController();
            homeController.setVisible(true);
        });

        // Key binding - 게임 조작 방향키 설정 뷰 접근

        setDownKeyButton.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.DOWN);
            keyListenConfigView.setVisible(true);
        });

        setRotateKeyButton.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.ROTATE);
            keyListenConfigView.setVisible(true);
        });

        setDropKeyButton.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.DROP);
            keyListenConfigView.setVisible(true);
        });

        setRightKeyButton.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.RIGHT);
            keyListenConfigView.setVisible(true);
        });

        setLeftKeyButton.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.LEFT);
            keyListenConfigView.setVisible(true);
        });

        // Key binding - 2P 게임 조작 방향키 설정 뷰 접근

        setDownKeyButton2P.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.DOWN_2P);
            keyListenConfigView.setVisible(true);
        });

        setRotateKeyButton2P.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.ROTATE_2P);
            keyListenConfigView.setVisible(true);
        });

        setDropKeyButton2P.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.DROP_2P);
            keyListenConfigView.setVisible(true);
        });

        setRightKeyButton2P.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.RIGHT_2P);
            keyListenConfigView.setVisible(true);
        });

        setLeftKeyButton2P.addActionListener(e -> {
            setVisible(false);
            KeyListenConfigView keyListenConfigView = new KeyListenConfigView(controller, new ConfigModel(), ConfigModel.PlayerKey.LEFT_2P);
            keyListenConfigView.setVisible(true);
        });

        colorBlindToggleButton.addActionListener(e -> {
            ConfigModel.changeColorBlindMode(!ConfigModel.colorBlindMode);
            colorBlindToggleButton.setText(ColorBlindStatusText());

            // Log to the prompt
            System.out.println("Colorblind: " + ConfigModel.colorBlindMode);

        });

        initializeSettingsButton.addActionListener(e -> {
            ConfigModel.initConfig();

            setDownKeyButton.setText("1P Down Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));
            setLeftKeyButton.setText("1P Left Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));
            setRightKeyButton.setText("1P Right Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));
            setDropKeyButton.setText("1P Drop Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));
            setRotateKeyButton.setText("1P Rotate Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

            setDownKeyButton2P.setText("2P Down Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN_2P.ordinal()]));
            setLeftKeyButton2P.setText("2P Left Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT_2P.ordinal()]));
            setRightKeyButton2P.setText("2P Right Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT_2P.ordinal()]));
            setDropKeyButton2P.setText("2P Drop Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP_2P.ordinal()]));
            setRotateKeyButton2P.setText("2P Rotate Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE_2P.ordinal()]));

            colorBlindToggleButton.setText("Colorblind: " + (ConfigModel.colorBlindMode ? "Activated" : "Deactivated"));
            
        });

        // Add buttonList to the frame
        for (JButton button : buttonList) {
            add(button);
            button.setBackground(new Color(20, 20, 20));
            button.setForeground(new Color(180, 180, 180));
            button.setFocusable(true);
        }

        // Set up key bindings for navigation
        setupKeyboardNavigation();

        setVisible(true);
    }

    // 키보드 네비게이션 설정
    private void setupKeyboardNavigation() {
        ActionMap actionMap = getRootPane().getActionMap();
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "navigateDown");
        actionMap.put("navigateDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(1);
            }
        });
 
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "navigateDown");
        actionMap.put("navigateDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(columns);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "navigateUp");
        actionMap.put("navigateUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(-columns);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "navigateRight");
        actionMap.put("navigateRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(1);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "navigateLeft");
        actionMap.put("navigateLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(-1);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "activate");
        actionMap.put("activate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performButtonAction();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "exit");
        actionMap.put("exit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitSettings();
            }
        });
    }

    // Set colorblind panel text
    public String ColorBlindStatusText() {
        if (ConfigModel.colorBlindMode) return "Colorblind: Activated";
        else return "Colorblind: Deactivated";
    }

    private void performButtonAction() {
        Component focused = getFocusOwner();
        if (focused instanceof JButton) {
            ((JButton) focused).doClick();
        }
    }

    // 버튼 포커스 이동
    private void moveFocusAndUpdateHighlight(int direction) {
        int newFocusedButtonIndex = (focusedButtonIndex + direction + buttonList.size()) % buttonList.size();
        highlightButton(newFocusedButtonIndex);
        focusedButtonIndex = newFocusedButtonIndex;
    }


    // 버튼 하이라이트
    private void highlightButton(int index) {
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == index) {
                buttonList.get(i).setBackground(new Color(180, 180, 180));
                buttonList.get(i).setForeground(new Color(20, 20, 20));
            } else {
                buttonList.get(i).setBackground(new Color(20, 20, 20));
                buttonList.get(i).setForeground(new Color(180, 180, 180));
            }
        }
        buttonList.get(index).requestFocusInWindow();
    }

    private void exitSettings() {
        System.out.println("Exiting Settings, show home view");
        HomeController homeController = new HomeController();
        homeController.setVisible(true);
        setVisible(false);
    }
}
