package org.Stech.SE5.View;

import org.Stech.SE5.Controller.BattleController;
import org.Stech.SE5.Controller.GameController;
import org.Stech.SE5.Controller.HomeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SelectView extends JFrame {
    private HomeController homeController;
    private final ArrayList<JButton> buttonList = new ArrayList<>();  // 만든 버튼을 저장할 ArrayList
    private PlayerKeyListener playerKeyListener;
    private int buttonPtrIndex;
    private boolean itemModeflag;
    private GameController gameController;

    private BattleController battleController;

    private boolean bBattle;

    public SelectView(final HomeController controller, boolean itemMode, double Size) {    // HomeController형 controller 객체를 매개로 하는 생성자
        homeController = controller;
        playerKeyListener = new PlayerKeyListener();
        itemModeflag = itemMode;
        bBattle = false;

        // 전체 프로그램 or HomeView의 제목을 설정
        setTitle("SE5_TEXTRIS");

        // FlowLayout 적용
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        JPanel bgPanel = new JPanel(); // HomeView 내 모든 요소를 올릴 배경 패널

        bgPanel.setBackground(new Color(20, 20, 20));
        bgPanel.setLayout(null);
        //bgPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));

        // 제목 레이블
        JLabel title = new JLabel();
        String titleText = null;

        //updateLabelTitle(title, titleText);

        if (itemMode)
            titleText = "ITEM";
        else
            titleText = "BASIC";
        title = new JLabel(titleText);

        // 게임 난이도 버튼
        JButton easyLvBtn = new JButton("Easy");
        JButton normalLvBtn = new JButton("Normal");
        JButton hardLvBtn = new JButton("Hard");
        JButton toHomeBtn = new JButton("Back to Menu");
        // 폰트 설정
        Font titleFont = new Font("Arial", Font.BOLD, (int)(60 * Size));
        Font buttonFont = new Font("Arial", Font.BOLD, (int)(24 * Size));

        title.setFont(titleFont);
        title.setBounds((int)(30 * Size), (int)(50 * Size), (int)(320 * Size), (int)(90 * Size));

        // 난이도 버튼
        setButton(easyLvBtn, buttonFont, (int)(30 * Size), (int)(200 * Size), (int)(320 * Size), (int)(60 * Size));  // 쉬움 난이도 버튼
        setButton(normalLvBtn, buttonFont, (int)(30 * Size), (int)(270 * Size), (int)(320 * Size), (int)(60 * Size));   // 보통 난이도 버튼
        setButton(hardLvBtn, buttonFont, (int)(30 * Size), (int)(340 * Size), (int)(320 * Size), (int)(60 * Size)); // 어려움 난이도 버튼
        setButton(toHomeBtn, buttonFont, (int)(30 * Size), (int)(410 * Size), (int)(320 * Size), (int)(60 * Size)); // 돌아가기 버튼

        this.setContentPane(bgPanel);
        setScreen(bgPanel, title);

        buttonPtrIndex = 0;
        highlightSelectedButton();

        addKeyListener(playerKeyListener); // Register key listener for this JFrame
        setFocusable(true);
        requestFocusInWindow();
    }


    public SelectView(final HomeController controller, double Size) {    // HomeController형 controller 객체를 매개로 하는 생성자
        homeController = controller;
        playerKeyListener = new PlayerKeyListener();
        bBattle = true;

        // 전체 프로그램 or HomeView의 제목을 설정
        setTitle("SE5_TEXTRIS");

        // FlowLayout 적용
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        JPanel bgPanel = new JPanel(); // HomeView 내 모든 요소를 올릴 배경 패널

        bgPanel.setBackground(new Color(20, 20, 20));
        bgPanel.setLayout(null);
        //bgPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));

        // 제목 레이블
        JLabel title = new JLabel();
        String titleText = null;

        //updateLabelTitle(title, titleText);
        titleText = "Battle";
        title = new JLabel(titleText);

        // 게임 난이도 버튼
        JButton easyLvBtn = new JButton("Basic");
        JButton normalLvBtn = new JButton("Item");
        JButton hardLvBtn = new JButton("Timer");
        JButton toHomeBtn = new JButton("Back to Menu");
        // 폰트 설정
        Font titleFont = new Font("Arial", Font.BOLD, (int)(60 * Size));
        Font buttonFont = new Font("Arial", Font.BOLD, (int)(24 * Size));

        title.setFont(titleFont);
        title.setBounds((int)(30 * Size), (int)(50 * Size), (int)(320 * Size), (int)(90 * Size));

        // 난이도 버튼
        setButton(easyLvBtn, buttonFont, (int)(30 * Size), (int)(200 * Size), (int)(320 * Size), (int)(60 * Size));  // 쉬움 난이도 버튼
        setButton(normalLvBtn, buttonFont, (int)(30 * Size), (int)(270 * Size), (int)(320 * Size), (int)(60 * Size));   // 보통 난이도 버튼
        setButton(hardLvBtn, buttonFont, (int)(30 * Size), (int)(340 * Size), (int)(320 * Size), (int)(60 * Size)); // 어려움 난이도 버튼
        setButton(toHomeBtn, buttonFont, (int)(30 * Size), (int)(410 * Size), (int)(320 * Size), (int)(60 * Size)); // 돌아가기 버튼

        this.setContentPane(bgPanel);
        setScreen(bgPanel, title);

        buttonPtrIndex = 0;
        highlightSelectedButton();

        addKeyListener(playerKeyListener); // Register key listener for this JFrame
        setFocusable(true);
        requestFocusInWindow();
    }

    public void setButton(JButton button, Font buttonFont, int x, int y, int width, int height) {
        button.setBorderPainted(false);  // 버튼의 외곽선을 투명으로 설정
        button.setContentAreaFilled(true);   // 버튼에 해당하는 공간을 색칠
        button.setBounds(x, y, width, height);   // 버튼의 좌표, 너비, 높이 설정
        button.setFont(buttonFont);    // 버튼 내 글씨체 설정
        button.setBackground(Color.BLACK);   // 버튼의 배경색 설정
        buttonList.add(button); // 설정을 끝마친 버튼을 ArrayList에 삽입
    }

    public void setScreen(JPanel buttonPanel, JLabel title) {
        buttonPanel.add(title); // title을 btnPanel에 삽입
        for (JButton i : buttonList) buttonPanel.add(i); // ArrayList 내 모든 버튼을 btnPanel에 삽입
    }

   /* public void setGameMode(int newGameMode) {
        gameMode = newGameMode;    // HomeView에서 호출할 메소드; SelectView의 GameMode 변수를 매개변수의 값으로 설정
    }

    public void updateLabelTitle(JLabel title, String titleText) {
        if (gameMode == 1)
            titleText = "ITEM";
        else
            titleText = "BASIC";
        title.setText(titleText);
    }*/

    private void highlightSelectedButton() {
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == buttonPtrIndex)
                buttonList.get(i).setBackground(Color.WHITE); // Enable the selected button
            else
                buttonList.get(i).setBackground(Color.BLACK); // Disable other buttons
        }
    }
    class PlayerKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent keyEvent) {   // 사용하지 않음
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP, KeyEvent.VK_LEFT:
                    if (buttonPtrIndex == 0) {
                        buttonPtrIndex = buttonList.size() - 1;
                        highlightSelectedButton();
                    }
                    else {
                        buttonPtrIndex = (buttonPtrIndex - 1 + buttonList.size()) % buttonList.size();
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT:
                    if (buttonPtrIndex == buttonList.size())
                        buttonPtrIndex = 0;
                    else {
                        buttonPtrIndex = (buttonPtrIndex + 1) % buttonList.size(); // Loop around to the first button if needed
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    switch(buttonPtrIndex){
                        case 0: // easyMode
                            if (bBattle){
                                battleController = new BattleController(0);
                                battleController.setVisible(true);
                                setVisible(false);
                                break;
                            }else {
                                gameController = new GameController(itemModeflag, 0);
                                gameController.setVisible(true);
                                setVisible(false);
                                break;
                            }
                        case 1: // normalMode
                            if (bBattle){
                                battleController = new BattleController(1);
                                battleController.setVisible(true);
                                setVisible(false);
                                break;
                            }else {
                                gameController = new GameController(itemModeflag, 1);
                                gameController.setVisible(true);
                                setVisible(false);
                                break;
                            }
                        case 2: // hardMode
                            if (bBattle){
                                battleController = new BattleController(2);
                                battleController.setVisible(true);
                                setVisible(false);
                                break;
                            }else {
                                gameController = new GameController(itemModeflag, 2);
                                gameController.setVisible(true);
                                setVisible(false);
                                break;
                            }
                        case 3: // backToMainMenu
                            homeController.setVisible(true);
                            setVisible(false);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + buttonPtrIndex);
                    }
                    break;
                default:    // 설정에서 정의되지 않은 키를 눌렀을 때, 메시지를 출력
                    JOptionPane.showMessageDialog(null, "다음 키만 사용하실 수 있습니다:\n↑, ↓, ←, →, Enter"/*나중에 이 부분 변경 필요, 설정에서 변경한 키에 맞게끔 출력해야 함*/, "ERROR!", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {    // 사용하지 않음
        }
    }
}