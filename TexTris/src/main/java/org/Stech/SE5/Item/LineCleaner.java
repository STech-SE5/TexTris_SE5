package org.Stech.SE5.Item;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.BlockType;
import org.Stech.SE5.Block.Element;

import java.util.Random;

public class LineCleaner extends Block {

    private int itemPosY;

    public LineCleaner() {
        Random rnd = new Random(System.currentTimeMillis());
        int rndNum = rnd.nextInt(7);
        BlockType blockKind = BlockType.values()[rndNum];
        shape = BlockType.getBlockInstance(blockKind).getFullShape();

        int cnt = 0;
        rndNum = rnd.nextInt(4);
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != Element.EMPTY) {
                    if (cnt == rndNum) {
                        shape[i][j] = Element.LINE_CLEANER;
                        itemPosY = i;
                    }
                    cnt++;
                }
            }
        }

        type = BlockType.LINE_CLEANER;
    }

    @Override
    public void rotate() {
        Element[][] temp = new Element[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
                if (temp[i][j] == Element.LINE_CLEANER) {
                    itemPosY = i;
                }
            }
        }
        shape = temp;
    }

    @Override
    public int getItemPosY() {
        return itemPosY;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
