package org.Stech.SE5.View.ConfigViews;

import org.Stech.SE5.Controller.ConfigController;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.View.ConfigViewNew;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyListenConfigView extends JFrame {
    private JLabel label;
    private Timer timer;
    private ConfigController configController;
    private ConfigModel configModel;


    public KeyListenConfigView(ConfigController controller, ConfigModel model, ConfigModel.PlayerKey currentKey) {
        this.configController = controller;
        this.configModel = model;

        // Initialize the JFrame
        setTitle("Key Press Display");
        System.out.println("Current Key Setting: " + currentKey);

        // 모델에서 너비, 높이 받아서 창 크기 같게 만들어야 함
        setSize(300, 200);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Set the background and foreground colors of the JFrame
        getContentPane().setBackground(new Color(20, 20, 20));

        // Create and add the label to the JFrame
        label = new JLabel("Press any key", SwingConstants.CENTER);
        label.setForeground(new Color(180, 180, 180));

        add(label, BorderLayout.CENTER);

        // Configure the key listener
        setupKeyListener(currentKey);

        // Timer to clear the "Saved" message
        timer = new Timer(3000, e -> label.setText("Press any key"));
        timer.setRepeats(false); // Ensure the timer only runs once per activation

        setVisible(true);
    }

    private void setupKeyListener(ConfigModel.PlayerKey keyToChange) {
        // Adding key listener to the whole JFrame content pane
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                System.out.printf("KeyText: %s, KeyCode: %d%n", KeyEvent.getKeyText(e.getKeyCode()), e.getKeyCode());

                // Temporary storage for the last pressed key that is not Enter or Escape
                ConfigModel.PlayerKey tempKey = ConfigModel.PlayerKey.UNDEFINED;

                // Ignore Escape key for updating the model, handle Enter separately
                if (e.getKeyCode() != KeyEvent.VK_ESCAPE) {
                    // Update temporary key state
                    tempKey = ConfigModel.PlayerKey.getPlayerKey(e);
                    label.setText("Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
                }

                // Finalize and update model when Enter is pressed
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Update the model with the accumulated key
                    if (tempKey != ConfigModel.PlayerKey.UNDEFINED) {
                        ConfigModel.changeKeyBinding(keyToChange, e);
                        System.out.printf("Key Setting of %s - KeyText: %s, KeyCode: %d%n", keyToChange, KeyEvent.getKeyText(e.getKeyCode()), e.getKeyCode());
                    }

                    label.setText("Saved");

                    timer.restart();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                    configController.setVisible(true);
                }

            }
        });

        // Ensure the JFrame can receive the key events
        setFocusable(true);
        requestFocusInWindow();
    }

}
