package org.Stech.SE5.Block;

public class ZBlock extends Block {
    public ZBlock() {
        element = Element.Z_BLOCK;
        shape = new Element[][] {
                {Element.Z_BLOCK, Element.Z_BLOCK, Element.EMPTY},
                {Element.EMPTY, Element.Z_BLOCK, Element.Z_BLOCK}
        };
        type = BlockType.Z_BLOCK;
    }
}