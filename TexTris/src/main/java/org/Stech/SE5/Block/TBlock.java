package org.Stech.SE5.Block;

import org.Stech.SE5.Model.Element;

public class TBlock extends Block {
    public TBlock() {
        element = Element.T_BLOCK;
        shape = new Element[][] {
                {Element.EMPTY, Element.T_BLOCK, Element.EMPTY},
                {Element.T_BLOCK, Element.T_BLOCK, Element.T_BLOCK}
        };
        type = BlockType.T_BLOCK;
    }
}