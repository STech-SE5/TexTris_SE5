package org.Stech.SE5.Block;

public class SBlock extends Block {
    public SBlock() {
        element = Element.S_BLOCK;
        shape = new Element[][] {
                {Element.EMPTY, Element.S_BLOCK, Element.S_BLOCK},
                {Element.S_BLOCK, Element.S_BLOCK, Element.EMPTY}
        };
        type = BlockType.S_BLOCK;
    }
}