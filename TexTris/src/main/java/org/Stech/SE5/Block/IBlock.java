package org.Stech.SE5.Block;

import org.Stech.SE5.Model.Element;

public class IBlock extends Block {
    public IBlock() {
        element = Element.I_BLOCK;
        shape = new Element[][] {
                {Element.I_BLOCK, Element.I_BLOCK, Element.I_BLOCK, Element.I_BLOCK}
        };
        type = BlockType.I_BLOCK;
    }
}