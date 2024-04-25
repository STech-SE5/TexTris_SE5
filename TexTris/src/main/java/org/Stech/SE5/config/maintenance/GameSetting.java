package org.Stech.SE5.config.maintenance;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import java.awt.Rectangle;

@Getter
@Setter
public class GameSetting {

    private int displayMode;
    private java.util.List<Rectangle> displayList;
    private boolean isColorBlind; // True when color-blind mode is activated

    // For 1st player keyboard

    // moveBlockLeft, moveBlockRight, moveDownKey move the tetris block left, right,
    // and down by one cell
    private int moveBlockLeft; // Move tetris block to the left
    private int moveBlockRight; // Move tetris block to the right
    private int moveDownKey; // Move tetris block down
    private int clockwiseRotate; // Rotate the tetris block clockwise once
    private int dropBlockAtOnce; // Drop the tetris block to the bottom of the board by once

    // For 2nd Player
    private int moveLeft2Key;
    private int moveRight2Key;
    private int moveDown2Key;
    private int rotate2Key;
    private int stack2Key;

    public GameSetting() {
        displayMode = 0;
        displayList = Arrays.asList(new Rectangle(0, 0, 1280, 720), new Rectangle(0, 0, 1120, 630), new Rectangle(0, 0, 960, 540));
        isColorBlind = false;
        moveBlockLeft = KeyEvent.VK_LEFT;
        moveBlockRight = KeyEvent.VK_RIGHT;
        moveDownKey = KeyEvent.VK_DOWN;
        clockwiseRotate = KeyEvent.VK_UP;
        dropBlockAtOnce = KeyEvent.VK_SPACE;
        moveLeft2Key = KeyEvent.VK_A;
        moveRight2Key = KeyEvent.VK_D;
        moveDown2Key = KeyEvent.VK_S;
        rotate2Key = KeyEvent.VK_W;
        stack2Key = KeyEvent.VK_R;
    }

}
