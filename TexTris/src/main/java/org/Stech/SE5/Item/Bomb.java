package org.Stech.SE5.Item;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.BlockType;
import org.Stech.SE5.Block.Element;

public class Bomb extends Block {
    private int itemPosY;

    public Bomb() {
        shape = new Element[][] {
                {Element.BOMB}
        };
        type = BlockType.BOMB;
        itemPosY = 0;
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
