package org.Stech.SE5.Block;


public class OBlock extends Block {
    public OBlock() {
        element = Element.O_BLOCK;
        shape = new Element[][] {
                {Element.O_BLOCK, Element.O_BLOCK},
                {Element.O_BLOCK, Element.O_BLOCK}
        };
        type = BlockType.O_BLOCK;
    }
}