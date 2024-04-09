package org.Stech.SE5.Item;

import org.Stech.SE5.Block.*;

public class WeightBlock extends Block {
    public WeightBlock() {
        shape = new Element[][] {
                { Element.EMPTY, Element.WEIGHT_BLOCK, Element.WEIGHT_BLOCK, Element.EMPTY },
                { Element.WEIGHT_BLOCK, Element.WEIGHT_BLOCK, Element.WEIGHT_BLOCK, Element.WEIGHT_BLOCK}
        };
        type = BlockType.WEIGHT_BLOCK;
    }

    @Override
    public boolean isItemBlock() {
        return true;
    }
}
