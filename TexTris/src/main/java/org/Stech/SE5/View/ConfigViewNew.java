package org.Stech.SE5.View;

import org.Stech.SE5.Controller.ConfigController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.ConfigViews.KeyListenConfigView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.ObjectInputFilter;
import java.util.ArrayList;

public class ConfigViewNew extends JFrame {
    private static final long serialVersionUID = 1L;
    private int buttonPtrIndex; // buttonList의 인덱스를 가리킬 변수

    private ConfigModel configModel;
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
    //Show button names and their current key bindings
    private JButton setDownKeyButton = new JButton("Set Down Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DOWN.ordinal()]));
    private JButton setLeftKeyButton = new JButton("Set Left Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.LEFT.ordinal()]));
    private JButton setRightKeyButton = new JButton("Set Right Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.RIGHT.ordinal()]));
    private JButton setDropKeyButton = new JButton("Set Drop Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.DROP.ordinal()]));
    private JButton setRotateKeyButton = new JButton("Set Rotate Key: " + KeyEvent.getKeyText(ConfigModel.keyBinding[ConfigModel.PlayerKey.ROTATE.ordinal()]));

    // UI Setting

    private JButton boarderSizeSettingButton = new JButton("Boardsize: ");

    // Colorblind Mode Toggle
    private JButton colorBlindToggleButton = new JButton("Colorblind: Deactivated");

    // Initialize Settings
    private JButton initializeSettingsButton = new JButton("Initialize Settings");

    // Navigation
    private final ArrayList<JButton> buttonList; // 만든 버튼을 저장할 ArrayList

    public ConfigViewNew(final ConfigController controller) {
        setTitle("Tetris Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setSize(300, 400);

        // Initialize button array for navigation

        // You should consider an existence of JToggleButton: colorBlindToggleButton

        buttonList = new ArrayList<>(); // Remove the empty square brackets

        // 홈으로 이동
        buttonList.add(goToHomeButton);

        // 창 크 기설정
        buttonList.add(bordersizeButton);

        // 게임 플레이 키보드 키 설정
        buttonList.add(setDownKeyButton);
        buttonList.add(setLeftKeyButton);
        buttonList.add(setRightKeyButton);
        buttonList.add(setDropKeyButton);
        buttonList.add(setRotateKeyButton);
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

        colorBlindToggleButton.addActionListener(e -> {
            ConfigModel.changeColorBlindMode(!ConfigModel.colorBlindMode);
            colorBlindToggleButton.setText(ColorBlindStatusText());

            // Log to the prompt
            System.out.println("Colorblind: " + ConfigModel.colorBlindMode);

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

    private void setupKeyboardNavigation() {
        ActionMap actionMap = getRootPane().getActionMap();
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        // Navigate down
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "navigateDown");
        actionMap.put("navigateDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(1);
            }
        });

        // Navigate up
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "navigateUp");
        actionMap.put("navigateUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFocusAndUpdateHighlight(-1);
            }
        });

        // Activate button
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "activate");
        actionMap.put("activate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performButtonAction();
            }
        });

        // Exit settings
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

    private void moveFocusAndUpdateHighlight(int direction) {
        Component focused = getFocusOwner();
        if (focused != null) {
            int nextIndex = -1;
            for (int i = 0; i < buttonList.size(); i++) {
                if (buttonList.get(i).isFocusOwner()) {
                    nextIndex = i + direction;
                    break;
                }
            }

            // Check and correct the index to stay within valid range
            if (nextIndex >= 0 && nextIndex < buttonList.size()) {
                // Move focus to the next button
                buttonList.get(nextIndex).requestFocusInWindow();

                // Highlight the newly focused button and unhighlight others
                for (int i = 0; i < buttonList.size(); i++) {
                    if (i == nextIndex) {
                        buttonList.get(i).setBackground(Color.WHITE); // Highlight the new button
                    } else {
                        buttonList.get(i).setBackground(Color.BLACK); // Unhighlight the rest
                    }
                }
            }
        }
    }

    private void exitSettings() {
        System.out.println("Exiting Settings, show home view");
        HomeController homeController = new HomeController();
        homeController.setVisible(true);
        setVisible(false);
    }
}
