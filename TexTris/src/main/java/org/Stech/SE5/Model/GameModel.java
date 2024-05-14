package org.Stech.SE5.Model;

import org.Stech.SE5.Controller.BattleController;
import  org.Stech.SE5.Controller.GameController;
import  org.Stech.SE5.Block.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameModel {
    private GameController gamecontroller;
    private BattleController battlecontroller;
    private ArrayList<Element[]> board = null;
    private Block currentBlock = null;
    private Block nextBlock = null;
    private double score = 0;
    private double scorerate = 1;
    private int lineCounts = 0;
    private int itemCount = 0;

    private int boostturn = 0;  //아이템부스트 아이템이 켜져있는지 관리할 변수

    private int ITEM_GENERATE_INTERVAL = 10;

    private boolean itemModeFlag;        //시작화면에서 정보 넘겨받아야 함

    private int diff;

    private final int DEFAULT_POS_X = 3;
    private final int DEFAULT_POS_Y = 0;
    private double gameSpeed = 1;

    private int posX;
    private int posY;

    private boolean bBattle = false;
    private ArrayList<Element[]> attack = new ArrayList<>();    //공격줄

    private GameModel opposite; //상대방
    private boolean isPlayer1 = true;  //true - p1, false = p2

    public void setOpposite(GameModel oppositeModel) {
        this.opposite = oppositeModel;
    }

    public void sendAttack(ArrayList<Element[]> myAttack) {
        opposite.receiveAttack(myAttack);
        System.out.println(myAttack);
    }

    public void receiveAttack(ArrayList<Element[]> receiveAttack) {
        for(int i=0; i<receiveAttack.size(); i++) {
            if(this.attack.size() == 10) return;
            this.attack.add(receiveAttack.get(i));
        }
    }

    public ArrayList<Element[]> getAttack() {
        return attack;
    }

    private void attackToBoard() {
        if(attack.size() == 0) return;
        for(int k=0; k < 20 - attack.size(); k++) {
            for (int i = 0; i < 10; i++) {
                board.get(k)[i] = board.get(k + attack.size())[i];
            }
        }

        for(int k = attack.size(); k > 0; k--) {
            for (int i = 0; i < 10; i++) {
                board.get(20 - k)[i] = attack.get(k - 1)[i];
            }
        }
        attack = new ArrayList<>();
    }

    public GameModel(final BattleController controller, int mode/*0,1,2로 일반, 아이템, 시간제한*/, boolean bPlayer1) {   //2인모드 생성자
        this.battlecontroller = controller;
        if (mode == 1){
            itemModeFlag = true;
        } else {
            itemModeFlag = false;
        }
        diff = 1;  //난이도 고정
        bBattle = true;     //대전모드
        initBoard(10, 20);
        this.setRandomBlock();
        this.isPlayer1 = bPlayer1;
        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;
    }

    public GameModel(final GameController controller, boolean itemFlag, int difficulty) {   //1인모드 생성자
        this.gamecontroller = controller;
        itemModeFlag = itemFlag;
        diff = difficulty;
        bBattle = false;
        initBoard(10, 20);
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

    public Block getNextBlock() {
        return nextBlock;
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

    public int getMode(){
        if (itemModeFlag){
            return 1;
        }
        else {
            return 0;
        }
    }

    public int getDiff(){ return diff; }

    public int getLineCounts(){     //기록용
        return lineCounts;
    }

    public int getItemCount(){
        return itemCount;
    }

    public void increaseItemcount(){
        if (boostturn > 0){
            itemCount = itemCount + 3;
            boostturn--;
        }
        else {
            itemCount++;
        }
    }

    public final void setRandomBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        BlockType blocktype;
        int rndNum = 0;

        if (nextBlock == null) {
            rndNum = rnd.nextInt(BlockType.getTetrominoSize());
            blocktype = BlockType.values()[rndNum];
            currentBlock = BlockType.getBlockInstance(blocktype);
        }
        else {
            currentBlock = nextBlock;
        }
        if (itemModeFlag && itemCount >= ITEM_GENERATE_INTERVAL){
            itemCount = Math.max(0, itemCount - ITEM_GENERATE_INTERVAL);
            rndNum = rnd.nextInt(BlockType.getItemSize()) + BlockType.getTetrominoSize();
        }
        else {
             if (diff == 0){
                rndNum = rnd.nextInt(72) / 10;
                if(rndNum > 6) rndNum = 6;
            }
             else if (diff == 1) {
                 rndNum = rnd.nextInt(70) / 10;
             }
             else if (diff == 2) {
                rndNum = rnd.nextInt(68) / 10;
             }
        }
        blocktype = BlockType.values()[rndNum];
        nextBlock = BlockType.getBlockInstance(blocktype);

        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;

        GameOver gameOver = new GameOver();
        if (gameOver.canPlaceBlock()) {
            placeBlock();
        }
        else {
            if (bBattle){
                battlecontroller.gameOver(isPlayer1);
            }else {
                gamecontroller.gameOver();
            }
        }
        gameSpeedUp();
    }

    public void gameSpeedUp() {
        if(gameSpeed > 6) return;
        gameSpeed *= 1.01;
        scorerate *= 1.05;
        if (bBattle && isPlayer1){
            battlecontroller.setTimeIntervalP1((int) (1000 / gameSpeed));
        } else if (bBattle && !isPlayer1) {
            battlecontroller.setTimeIntervalP2((int) (1000 / gameSpeed));
        } else {
            gamecontroller.setTimeInterval((int) (1000 / gameSpeed));
        }
    }

    private void getBonusScore(){
    if ((lineCounts % 2) == 0){
        score += 600 * scorerate;
        }
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

    class Rotate extends Act {
        public boolean ifBoundaryGoOver() {
            return (posY + currentBlock.height() > 20)
                    || (posX + currentBlock.width() > 10);
        }

        public void move() {
            currentBlock.rotate();
        }

        public void moveBack() {
            currentBlock.rotate();
            currentBlock.rotate();
            currentBlock.rotate();
        }

        public void hook() {
        }
    }

    class Down extends Act {
        public boolean ifBoundaryGoOver() {
            return posY + currentBlock.height() > 20;
        }

        public void move() {
            posY++;
            score += 1 * scorerate;     //난이도따라 가중치 바꿔서 해야함
        }

        public void moveBack() {
            posY--;
        }

        public void hook() {
            if(currentBlock.isItemBlock()) {
                triggerItem();
            }
            else {
                checkRaw();
                attackToBoard();
                setRandomBlock();
            }
        }
    }

    class Right extends Act {
        public boolean ifBoundaryGoOver() {
            return posX + currentBlock.width() > 10;
        }

        public void move() {
            posX++;
        }

        public void moveBack() {
            posX--;
        }

        public void hook() {
        }
    }

    class Left extends Act {
        public boolean ifBoundaryGoOver() {
            return posX < 0;
        }

        public void move() {
            posX--;
        }

        public void moveBack() {
            posX++;
        }

        public void hook() {
        }
    }

    public final void moveStraightDown() {
        Down down = new Down();
        int cnt = 0;
        while (true) {
            cnt++;
            if (down.run() == Result.ERR) {
                break;
            }
        }
        score += cnt * scorerate;
    }

    class GameOver extends Act {
        public boolean ifBoundaryGoOver() {
            return false;
        }

        public void move() {

        }

        public void moveBack() {

        }

        public void hook() {

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

    public final void actRotate() {
        Rotate rotate = new Rotate();
        rotate.run();
    }

    public final void moveRight() {
        Right right = new Right();
        right.run();
    }

    public final void moveLeft() {
        Left left = new Left();
        left.run();
    }

    public final void moveDownAndCheck() {
        Down down = new Down();
        down.run();
    }

    public final void checkRaw() {
            int temp = lineCounts;
            ArrayList<Element[]> attack = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            boolean isRaw = true;
            for (int j = 0; j < 10; j++) {
                if (board.get(i)[j] == Element.EMPTY) {
                    isRaw = false;
                    break;
                }
            }
            if (isRaw) {
                for (int j = 0; j < 10; j++) {
                    board.get(i)[j] = Element.DELETE;
                }
                score += 100 * scorerate;       //추후 난이도 따른 가중치 추가
                increaseItemcount();
                lineCounts++;
                getBonusScore();
                if (bBattle){
                    Element[] tempRaw = new Element[10];

                    for(int k=0; k<10; k++) {
                        if(k - posX < 0 || i - posY < 0 || k - posX >= currentBlock.width() || i - posY >= currentBlock.height()) {
                            tempRaw[k] = Element.ATTACK;
                        } else if(currentBlock.getShape(k - posX, i - posY) != Element.EMPTY) {
                            tempRaw[k] = Element.EMPTY;
                        } else {
                            tempRaw[k] = Element.ATTACK;
                        }
                    }
                    attack.add(tempRaw);
                }
            }
        }
        if (bBattle && lineCounts - temp >= 2){
            Collections.reverse(attack);
            sendAttack(attack);
        }
        if (bBattle) {
            battlecontroller.drawView();
        } else {
            gamecontroller.drawView();
        }
    }

    public final void runDelete() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.get(i)[j] == Element.DELETE) {
                    shiftDown(i-1, j);
                }
            }
        }
    }

    public final void shiftDown(final int startHeight, final int posX) {
        eraseCurr();
        for (int i = startHeight; i >= 0; i--) {
            board.get(i + 1)[posX] = board.get(i)[posX];
        }
        placeBlock();
    }

    public final void moveWeightBlockDown() {
        eraseCurr();
        posY++;
        if (posY + currentBlock.height() > 20) {
            posY--;
            placeBlock();
            if (bBattle){
                battlecontroller.weightBlockStop(isPlayer1);
            } else {
                gamecontroller.weightBlockStop();
            }
            checkRaw();
            setRandomBlock();
            return;
        }
        placeBlock();
    }

    public final void triggerItem() {
        switch (currentBlock.getType()) {
            case WEIGHT_BLOCK -> {
                if (bBattle){
                    battlecontroller.weightBlockStart(isPlayer1);
                } else {
                    gamecontroller.weightBlockStart();
                }
            }
            case LINE_CLEANER -> {
                for (int j = 0; j < 10/*설정에서 받아오기*/; j++) {
                    board.get(posY + currentBlock.getItemPosY())[j] = Element.DELETE;
                }
                checkRaw();
                score += 100 * scorerate;
                increaseItemcount();
                lineCounts++;
                getBonusScore();
                gameSpeedUp();
                setRandomBlock();
            }
            case BOMB -> {
                int cnt = 0;
                for (int i = -2; i <= 2; i++) { // 중심점에서 위로 2칸, 아래로 2칸
                    int currentY = posY + i;
                    if (currentY < 0 || currentY >= 20/*높이 받아와서 수정*/) continue; // 게임보드 범위를 벗어나면 무시
                    // 각 줄마다 영향을 받는 X 범위의 너비를 계산
                    int width = 5 - Math.abs(i) * 2; // 1, 3, 5, 3, 1 패턴에 맞게 너비를 계산
                    int startX = posX - (width / 2); // 시작 X 좌표
                    int endX = startX + width - 1; // 종료 X 좌표

                    for (int j = startX; j <= endX; j++) {
                        if (j < 0 || j >= 10/*너비 받아와서 수정*/) continue; // 게임보드 범위를 벗어나면 무시
                        if (board.get(currentY)[j] != Element.EMPTY) {
                            board.get(currentY)[j] = Element.DELETE;
                            cnt++;
                        }
                    }
                }
                score += 10 * cnt * scorerate;
                setRandomBlock();
            }
            case ITEM_BOOST -> {
                boostturn = boostturn + 3;
                checkRaw();
                setRandomBlock();
            }
            case CROSS_DELETE -> {
                int cnt = 0;
                for (int i = 0; i < 20/*높이 받아오기*/; i++) {
                    for (int j = 0; j < 10/*너비 받아오기*/; j++) {
                        if(Math.abs(posX - j) == Math.abs(posY - i) && board.get(i)[j] != Element.EMPTY) {
                            board.get(i)[j] = Element.DELETE;
                            cnt++;
                        }
                    }
                }
                score += 10 * cnt * scorerate;
                setRandomBlock();
            }
        }
    }
    //test용
    public double getGameSpeed(){ return gameSpeed;}
    public double getScorerate(){return scorerate;}
    public void setITEM_GENERATE_INTERVAL0(){ITEM_GENERATE_INTERVAL = 0;}
    public final void setRandomBlock_test() {
        Random rnd = new Random(System.currentTimeMillis());
        BlockType blocktype;
        int rndNum = 0;

        if (nextBlock == null) {
            rndNum = rnd.nextInt(BlockType.getTetrominoSize());
            blocktype = BlockType.values()[rndNum];
            currentBlock = BlockType.getBlockInstance(blocktype);
        }
        else {
            currentBlock = nextBlock;
        }
        if (itemModeFlag && itemCount >= ITEM_GENERATE_INTERVAL){
            itemCount = Math.max(0, itemCount - ITEM_GENERATE_INTERVAL);
            rndNum = rnd.nextInt(BlockType.getItemSize()) + BlockType.getTetrominoSize();
        }
        else {
            if (diff == 0){
                rndNum = rnd.nextInt(72) / 10;
                if(rndNum > 6) rndNum = 6;
            }
            else if (diff == 1) {
                rndNum = rnd.nextInt(70) / 10;
            }
            else if (diff == 2) {
                rndNum = rnd.nextInt(68) / 10;
            }
        }
        blocktype = BlockType.values()[rndNum];
        nextBlock = BlockType.getBlockInstance(blocktype);

        posX = DEFAULT_POS_X;
        posY = DEFAULT_POS_Y;

        GameOver gameOver = new GameOver();
        if (gameOver.canPlaceBlock()) {
            placeBlock();
        }
        else {
            if (bBattle){
                battlecontroller.gameOver(isPlayer1);
            }else {
                gamecontroller.gameOver();
            }
        }
        gameSpeedUp();
    }
}