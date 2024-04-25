package org.Stech.SE5.Item;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.BlockType;
import org.Stech.SE5.Block.Element;

public class CrossDelete extends Block {

    private int itemPosY;

    public CrossDelete() {
        shape = new Element[][] {
                {Element.CROSS_DELETE}
        };
        type = BlockType.CROSS_DELETE;
        itemPosY = 0;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
