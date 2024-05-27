package org.Stech.SE5.Block;

public abstract class Block {
    protected BlockType type;
    protected Element element;
    protected Element[][] shape;

    public Block() {}

    public final Element getShape(final int x, final int y) {
        return shape[y][x];
    }

    public final Element[][] getFullShape() {
        return shape;
    }

    public void rotate() {
        Element[][] temp = new Element[width()][height()];
        for (int i = 0; i < width(); i++) {
            for (int j = 0; j < height(); j++) {
                temp[i][j] = shape[height() - 1 - j][i];
            }
        }
        shape = temp;
    }

    public final int height() {
        return shape.length;
    }

    public final int width() {
        if (shape.length > 0) {
            return shape[0].length;
        }
        return 0;
    }

    public int getItemPosY() {
        throw new UnsupportedOperationException();
    }

    public Element getElement() {
        return this.element;
    }

    public boolean isItemBlock() {
        return false;
    }

    public BlockType getType() {
        return type;
    }
}