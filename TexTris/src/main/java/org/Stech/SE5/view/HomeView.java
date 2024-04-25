package org.Stech.SE5.view;

import org.Stech.SE5.controller.HomeController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class HomeView extends JFrame {
    private final int VIEW_WIDTH = 400;
    private final int VIEW_HEIGHT = 600;
    private final ArrayList<JButton> buttonList = new ArrayList<>();  // 만든 버튼을 저장할 ArrayList
    private JPanel bgPanel;    // 전체 버튼을 담을 패널
    private JLabel title;   // 패널에 들어갈 타이틀(TEXTRIS)
    private JButton basicBtn, itemBtn;  // 게임 모드 버튼
    private JButton configBtn, exitBtn, scoreBrdBtn;    // 기타 버튼
    private int buttonPtrIndex; // buttonList의 인덱스를 가리킬 변수

    private HomeController homeController;
    private PlayerKeyListener playerKeyListener;

    public HomeView(final HomeController controller) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeController = controller;
        playerKeyListener = new PlayerKeyListener();

        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setTitle("SE5_TEXTRIS");

        // FlowLayout 적용
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        bgPanel = new JPanel(); // HomeView 내 모든 요소를 올릴 배경 패널

        bgPanel.setBackground(new Color(20, 20, 20));
        bgPanel.setLayout(null);
        bgPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));

        // 제목 레이블 객체 생성
        title = new JLabel("TEXTRIS");
        // 게임 모드 버튼 객체 생성
        basicBtn = new JButton("Basic Mode");
        itemBtn = new JButton("Item Mode");
        // 기타 버튼 객체 생성
        configBtn = new JButton("Setting"); // 설정 화면으로 이동하는 버튼
        exitBtn = new JButton("X");  // 게임 종료 버튼
        scoreBrdBtn = new JButton("Score Board");    // 스코어 보드로 이동하는 버튼

        // 폰트 설정
        Font titleFont = new Font("Arial", Font.BOLD, 60);
        Font buttonFont = new Font("Arial", Font.BOLD, 24);

        title.setFont(titleFont);
        title.setBounds(30, 50, 320, 90);

        title.setFont(titleFont);
        title.setBounds(30, 50, 320, 90);

        // 모드 버튼
        setButton(basicBtn, buttonFont, 30, 200, 320, 60);    // 일반 모드 버튼
        setButton(itemBtn, buttonFont, 30, 270, 320, 60);    // 아이템 모드 버튼

        // 기타 버튼
        setButton(scoreBrdBtn, buttonFont, 30, 340, 320, 60);   // 스코어보드 버튼
        setButton(configBtn, buttonFont, 30, 410, 320, 60);    // 설정 버튼
        setButton(exitBtn, new Font("Arial", Font.BOLD, 11), 305, 485, 46, 46);  // 종료버튼

        // bgPanel에 모든 요소를 삽입
        this.setContentPane(bgPanel);
        setScreen(bgPanel, title);

        buttonPtrIndex = 0; // 첫 인덱스를 0으로 초기화
        highlightSelectedButton();

        addKeyListener(playerKeyListener); // playerKeyListener를 이 프레임에 할당
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

    private void highlightSelectedButton() {    // 선택된 버튼을 강조하는 메소드
        for (int i = 0; i < buttonList.size(); i++) {
            if (i == buttonPtrIndex)
                buttonList.get(i).setBackground(Color.WHITE); // 선택된 버튼을 하얀색으로 강조
            else
                buttonList.get(i).setBackground(Color.BLACK); // 나머지 버튼은 검정색으로 색칠
        }
    }

    class PlayerKeyListener implements KeyListener {    // KeyListener를 상속하여 특정 기능을 활용하는 PlayerKeyListner 클래스

        @Override
        public void keyTyped(KeyEvent keyEvent) {   // 사용하지 않음
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                    if (buttonPtrIndex == 0)    // 현재 선택된 버튼이 첫번째 버튼일 경우
                        buttonPtrIndex = buttonList.size(); // 위 화살표를 누르면, 맨 마지막 버튼을 선택 -> 문제는 이 과정에서 버튼 입력이 2번 요구됨
                    else {
                        buttonPtrIndex = (buttonPtrIndex + buttonList.size() - 1) % buttonList.size();
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (buttonPtrIndex == buttonList.size())    // 현재 선택된 버튼이 마지막 버튼일 경우
                        buttonPtrIndex = 0; // 아래 화살표를 누르면, 맨 처음 버튼을 선택 -> 이 과정은 잘 작동됨
                    else {
                        buttonPtrIndex = (buttonPtrIndex + 1) % buttonList.size();
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    switch (buttonPtrIndex) {
                        case 0: // basicMode
                            break;
                        case 1: // itemMode
                            break;
                        case 2: // scoreBoard
                            break;
                        case 3: // setting
                            break;
                        case 4: // exit
                            break;
                        default:
                            break;
                    }
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {    // 사용하지 않음
        }
    }
}