package org.Stech.SE5.Model;

import  org.Stech.SE5.Controller.GameController;
import  org.Stech.SE5.Block.*;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private GameController gamecontroller;
    private ArrayList<Element[]> board;
    private Block currentBlock;
    private Block nextBlock;
    private double score = 0;
    private int deletedRowCount = 0;

    private final int DEFAULT_POS_X = 3;
    private final int DEFAULT_POS_Y = 0;
    private double gameSpeed = 1;       //추후 설정에서 받아오기

    private int posX;
    private int posY;

    public GameModel(final GameController controller) {
        this.gamecontroller = controller;
        initBoard(10, 20);  //나중에 설정쪽에서 받아올 수 있게 변경 필요
        this.setRandomBlock();
        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;
    }

    public final ArrayList<Element[]> getBoard() {
        return board;
    }

    public double getScore() {
        return score;
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public Block getNextBlock() {
        return nextBlock;
    }

    public int getDeletedRaw() {
        return deletedRowCount;
    }

    public final void initBoard(final int width, final int height) {
        board = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            Element[] row = new Element[width];
            for (int j = 0; j < width; j++) {
                row[j] = Element.EMPTY;
            }
            board.add(row);
        }
    }

    public final void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        BlockType blocktype;
        int rndNum = 0;

        if(nextBlock == null) {
            rndNum = rnd.nextInt(BlockType.getTetrominoSize());
            blocktype = BlockType.values()[rndNum];
            currentBlock = BlockType.getBlockInstance(blocktype);
        } else {
            currentBlock = nextBlock;
        }
       /* switch (설정에서 가져온 난이도 값) {
            case EASY -> {
                rndNum = rnd.nextInt(72) / 10; // I_BLOCK 60 ~ 71, weight 12
                if(rndNum > 6) rndNum = 6;
            }
            case NORMAL -> rndNum = rnd.nextInt(70) / 10; // I_BLOCK 60 ~ 69, weight 10
            case HARD -> rndNum = rnd.nextInt(68) / 10; // I_BLOCK 60 ~ 67, weight 8
        }*/
        rndNum = rnd.nextInt(70) / 10;  //일단 노말로 설정

        blocktype = BlockType.values()[rndNum];
        nextBlock = BlockType.getBlockInstance(blocktype);

        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;
        //생성이 불가한 상황이면 게임오버되는 기능 구현 필요
    }

    private enum Result {       //확인용
        SUCC, ERR;
    }

    abstract class Act {
        public Result run() {
            eraseCurr();
            move();
            if (canPlaceBlock()) {
                return placeBlock();
            } else {
                fallBack();
                return Result.ERR;
            }
        }

        public void fallBack() {
            moveBack();
            Result ret = placeBlock();
            if (ret == Result.ERR) {
                throw new IllegalStateException();
            }
            hook();
        }

        public boolean canPlaceBlock() {
            if (ifBoundaryGoOver()) {
                return false;
            }
            for (int i = 0; i < currentBlock.width(); i++) {
                for (int j = 0; j < currentBlock.height(); j++) {
                    if (board.get(posY + j)[posX + i] != Element.EMPTY
                            && currentBlock.getShape(i, j) != Element.EMPTY) {
                        return false;
                    }
                }
            }
            return true;
        }

        public abstract boolean ifBoundaryGoOver();

        public abstract void move();

        public abstract void moveBack();

        public abstract void hook();
    }

    class Down extends Act {
        public boolean ifBoundaryGoOver() {
            return posY + currentBlock.height() > 20;
        }       //20자리에 설정에서 바뀌는 보드높이 받아와야함

        public void move() {
            posY++;
            score += 1;     //난이도따라 가중치 바꿔서 해야함
        }

        public void moveBack() {
            posY--;
        }

        public void hook() {
                checkRaw();
                setRandomBlock();
        }
    }

    public final Result placeBlock() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (currentBlock.getShape(i, j) != Element.EMPTY) {
                    board.get(posY + j)[posX + i] = currentBlock.getShape(i, j);
                }
            }
        }
        return Result.SUCC;
    }

    public final void eraseCurr() {
        for (int i = 0; i < currentBlock.width(); i++) {
            for (int j = 0; j < currentBlock.height(); j++) {
                if (currentBlock.getShape(i, j) != Element.EMPTY) {
                    board.get(posY + j)[posX + i] = Element.EMPTY;
                }
            }
        }
    }

    public final void moveDownAndCheck() {
        Down down = new Down();
        down.run();
    }

    public final void checkRaw() {
        for (int i = 0; i < 20/*설정에서 크기에 따른 보드 높이 받아와야함*/; i++) {
            boolean isRaw = true;
            for (int j = 0; j < 10/*설정에서 크기에 따른 보드 넓이 받아와야함*/; j++) {
                if (board.get(i)[j] == Element.EMPTY) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw) {
                for (int j = 0; j < 10/*설정에서 크기에 따른 보드 넓이 받아와야함*/; j++) {
                    board.get(i)[j] = Element.DELETE;
                }
                score += 100 * 1;       //추후 난이도 따른 가중치 추가
                deletedRowCount++;
            }
        }
        gamecontroller.drawView();
    }
}