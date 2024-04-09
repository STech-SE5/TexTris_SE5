package org.Stech.SE5.Block;

import org.Stech.SE5.Item.*;

public enum BlockType {
    J_BLOCK, L_BLOCK, Z_BLOCK, S_BLOCK, T_BLOCK, O_BLOCK, I_BLOCK,
    WEIGHT_BLOCK, LINE_CLEANER, BOMB, ITEM_BOOST;

    public static final int getTetrominoSize() {
        return 7;
    }

    public static final int getItemSize() {
        return 4;
    }

    public static final Block getBlockInstance(final BlockType blocktype) {
        return switch (blocktype) {
            case I_BLOCK -> new IBlock();
            case J_BLOCK -> new JBlock();
            case L_BLOCK -> new LBlock();
            case Z_BLOCK -> new ZBlock();
            case S_BLOCK -> new SBlock();
            case T_BLOCK -> new TBlock();
            case O_BLOCK -> new OBlock();
            case WEIGHT_BLOCK -> new WeightBlock();
            case LINE_CLEANER -> new LineCleaner();
            case BOMB -> new Bomb();
            case ITEM_BOOST -> new ItemBoost();
        };
    }
}