package org.Stech.SE5.View;

import org.Stech.SE5.Controller.ConfigController;
import org.Stech.SE5.Controller.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ConfigViewNew extends JFrame {
    private static final long serialVersionUID = 1L;
    private int buttonPtrIndex; // buttonList의 인덱스를 가리킬 변수


    // Key Bindings
    private KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
    private KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
    private KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
    private KeyStroke dropKey = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0);
    private KeyStroke rotateKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);

    // buttonList
    private JButton goToHomeButton = new JButton("Go Previous");
    private JButton bordersizeButton = new JButton("Border Size");
    private JButton setDownKeyButton = new JButton("Set Down Key");
    private JButton setLeftKeyButton = new JButton("Set Left Key");
    private JButton setRightKeyButton = new JButton("Set Right Key");
    private JButton setDropKeyButton = new JButton("Set Drop Key");
    private JButton setRotateKeyButton = new JButton("Set Rotate Key");
    private JToggleButton colorBlindToggleButton = new JToggleButton("Colourblind Mode");
    private JButton initializeSettingsButton = new JButton("Initialize Settings");

    // Navigation
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();  // 만든 버튼을 저장할 ArrayList

    public ConfigViewNew(final ConfigController controller) {
        setTitle("Tetris Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 1));
        setSize(300, 400);

        // Initialize button array for navigation

        // You should consider an existence of JToggleButton: colorBlindToggleButton

        buttonList = new ArrayList<>(); // Remove the empty square brackets

        buttonList.add(goToHomeButton);
        buttonList.add(bordersizeButton);
        buttonList.add(setDownKeyButton);
        buttonList.add(setLeftKeyButton);
        buttonList.add(setRightKeyButton);
        buttonList.add(setDropKeyButton);
        buttonList.add(setRotateKeyButton);
        buttonList.add(initializeSettingsButton);

        goToHomeButton.addActionListener(e -> {
            HomeController homeController = new HomeController();
            homeController.setVisible(true);
            setVisible(false);
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
