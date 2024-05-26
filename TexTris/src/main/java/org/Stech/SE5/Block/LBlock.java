package org.Stech.SE5.Block;

public class LBlock extends Block {

    public LBlock() {
        element = Element.L_BLOCK;
        shape = new Element[][] {
                {Element.L_BLOCK, Element.L_BLOCK, Element.L_BLOCK},
                {Element.L_BLOCK, Element.EMPTY, Element.EMPTY}
        };
        type = BlockType.L_BLOCK;
    }
}