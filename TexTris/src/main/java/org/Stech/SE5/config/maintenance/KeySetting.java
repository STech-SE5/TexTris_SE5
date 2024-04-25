package org.Stech.SE5.config.maintenance;

import java.awt.event.KeyEvent;

import static org.Stech.SE5.config.maintenance.SetConfig.saveConfig;

public class KeySetting {


    public enum PlayerKey {
        ROTATE, LEFT, RIGHT, DOWN, DROP, ESC, UNDEFINED
    }


    public static int[] keyBinding = {
            KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT,
            KeyEvent.VK_DOWN, KeyEvent.VK_SPACE, KeyEvent.VK_ESCAPE, 0
    };

    
    // private final static String path = "data/config.txt";

    private void ConfigModel() {

    }

    public static PlayerKey getPlayerKey(final KeyEvent e) {
        PlayerKey[] values = PlayerKey.values();
        for (int i = 0; i < values.length; i++) {
            if (keyBinding[i] == e.getKeyCode()) {
                return values[i];
            }
        }
        return PlayerKey.UNDEFINED;
    }



    public static void changeKeyBinding(PlayerKey playerKey, KeyEvent e) {
        keyBinding[playerKey.ordinal()] = e.getKeyCode();
        saveConfig();
    }





}
