package org.Stech.SE5.Item;

import org.Stech.SE5.Block.Block;
import org.Stech.SE5.Block.BlockType;
import org.Stech.SE5.Block.Element;

public class ItemBoost extends Block {

    public ItemBoost() {
        shape = new Element[][] {
                {Element.ITEM_BOOST}
        };
        type = BlockType.ITEM_BOOST;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
