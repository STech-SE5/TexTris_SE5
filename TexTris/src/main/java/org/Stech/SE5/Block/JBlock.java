package org.Stech.SE5.Block;

public class JBlock extends Block {
    public JBlock() {
        element = Element.J_BLOCK;
        shape = new Element[][] {
                {Element.J_BLOCK, Element.J_BLOCK, Element.J_BLOCK},
                {Element.EMPTY, Element.EMPTY, Element.J_BLOCK}
        };
        type = BlockType.J_BLOCK;
    }
}