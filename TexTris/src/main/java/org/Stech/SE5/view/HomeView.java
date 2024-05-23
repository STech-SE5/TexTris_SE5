package org.Stech.SE5.View;

import org.Stech.SE5.Controller.ConfigController;
import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Controller.RecordController;
import org.Stech.SE5.Model.ConfigModel;
import org.Stech.SE5.Model.RecordModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/*
    1) UI를 설정하는 부분을 하나의 메소드로 분리하기
    2) Model-View-Controller에 맞게 메소드 분리하고, 관리하기
    3) Enter를 누르고 다른 화면을 호출할 때, 오류 메시지를 출력하는 문제 해결 요망
 */

public class HomeView extends JFrame {
    private double Size;    //설정에서 받아와야함
    private int VIEW_WIDTH;
    private int VIEW_HEIGHT;
    private final ArrayList<JButton> buttonList = new ArrayList<>();  // 만든 버튼을 저장할 ArrayList
    private JPanel bgPanel;    // 전체 버튼을 담을 패널
    private JLabel title;   // 패널에 들어갈 타이틀(TEXTRIS)
    private JButton basicBtn, itemBtn, battleBtn;  // 게임 모드 버튼
    private JButton configBtn, exitBtn, scoreBrdBtn;    // 기타 버튼
    private int buttonPtrIndex; // buttonList의 인덱스를 가리킬 변수

    private ConfigController configController;
    private HomeController homeController;
    private PlayerKeyListener playerKeyListener;
    private SelectView selectView;
    private ConfigModel configModel = new ConfigModel();


    public HomeView(final HomeController controller) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeController = controller;
        playerKeyListener = new PlayerKeyListener();
        setSize();
        setSize(VIEW_WIDTH, VIEW_HEIGHT);
        setTitle("SE5_TEXTRIS");

        // FlowLayout 적용
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.RIGHT);
        bgPanel = new JPanel(); // HomeView 내 모든 요소를 올릴 배경 패널

        bgPanel.setBackground(new Color(20, 20, 20));
        bgPanel.setLayout(null);
        //bgPanel.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 25));

        // 제목 레이블 객체 생성
        title = new JLabel("TEXTRIS");
        // 게임 모드 버튼 객체 생성
        basicBtn = new JButton("Basic Mode");   // 일반 모드 버튼
        itemBtn = new JButton("Item Mode"); // 아이템 모드 버튼
        battleBtn = new JButton("Battle Mode"); // 대전 모드 버튼
        // 기타 버튼 객체 생성
        configBtn = new JButton("Setting"); // 설정 화면으로 이동하는 버튼
        scoreBrdBtn = new JButton("RECORD");    // 스코어 보드로 이동하는 버튼
        exitBtn = new JButton("X");  // 게임 종료 버튼

        // 폰트 설정
        Font titleFont = new Font("Arial", Font.BOLD, (int)(60 * Size));
        Font buttonFont = new Font("Arial", Font.BOLD, (int)(24 * Size));

        title.setFont(titleFont);
        title.setBounds((int)(30 * Size), (int)(50 * Size), (int)(320 * Size), (int)(90 * Size));

        // 모드 버튼
        setButton(basicBtn, buttonFont, (int)(30 * Size), (int)(200 * Size), (int)(320 * Size), (int)(60 * Size));    // 일반 모드 버튼
        setButton(itemBtn, buttonFont, (int)(30 * Size), (int)(270 * Size), (int)(320 * Size), (int)(60 * Size));    // 아이템 모드 버튼
        setButton(battleBtn, buttonFont, (int) (30 * Size), (int) (340 * Size), (int) (320 * Size), (int) (60 * Size));

        // 기타 버튼
        setButton(configBtn, buttonFont, (int) (30 * Size), (int) (410 * Size), (int) (320 * Size), (int) (60 * Size));    // 설정 버튼
        setButton(scoreBrdBtn, new Font("Arial", Font.BOLD, (int)(11 * Size)), (int) (30 * Size), (int) (485 * Size), (int) (80 * Size), (int) (46 * Size));   // 스코어보드 버튼
        setButton(exitBtn, new Font("Arial", Font.BOLD, (int)(11 * Size)), (int)(305 * Size), (int)(485 * Size), (int)(46 * Size), (int)(46 * Size));  // 종료버튼

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
                case KeyEvent.VK_UP, KeyEvent.VK_LEFT:
                    if (buttonPtrIndex == 0)    // 현재 선택된 버튼이 첫번째 버튼일 경우
                        buttonPtrIndex = buttonList.size(); // 위 화살표를 누르면, 맨 마지막 버튼을 선택 -> 문제는 이 과정에서 버튼 입력이 2번 요구됨
                    else {
                        buttonPtrIndex = (buttonPtrIndex + buttonList.size() - 1) % buttonList.size();
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT:
                    if (buttonPtrIndex == buttonList.size())    // 현재 선택된 버튼이 마지막 버튼일 경우
                        buttonPtrIndex = 0; // 아래 화살표를 누르면, 맨 처음 버튼을 선택 -> 이 과정은 잘 작동됨
                    else {
                        buttonPtrIndex = (buttonPtrIndex + 1) % buttonList.size();
                        highlightSelectedButton();
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    switch (buttonPtrIndex) {
                        case 0: // 일반 모드
                            setVisible(false);
                            selectView = new SelectView(homeController, false, Size);
                            selectView.setVisible(true);
                            selectView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
                            selectView.setLocationRelativeTo(null);
                            break;
                        case 1: // 아이템 모드
                            setVisible(false);
                            selectView = new SelectView(homeController, true, Size);
                            selectView.setVisible(true);
                            selectView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
                            selectView.setLocationRelativeTo(null);
                            break;
                        case 2: // 대전 모드
                            /*대전 모드 생성자 호출*/
                            setVisible(false);
                            selectView = new SelectView(homeController, Size);
                            selectView.setVisible(true);
                            selectView.setSize(VIEW_WIDTH, VIEW_HEIGHT);
                            selectView.setLocationRelativeTo(null);
                            break;
                        case 3: // 설정 화면
                            configController = new ConfigController();
                            configController.setVisible(true);
                            setVisible(false);
                            break;
                        case 4: // 스코어 보드
                            RecordModel.loadRecord();
                            RecordController record = new RecordController();
                            record.setVisible(true);
                            setVisible(false);
                            break;
                        case 5: // 게임 종료
                            System.exit(0);
                            break;
                        default:
                            break;
                    }
                default:    // 설정에서 정의되지 않은 키를 눌렀을 때, 메시지를 출력
                    JOptionPane.showMessageDialog(null, "다음 키만 사용하실 수 있습니다:\n↑, ↓, ←, →, Enter"/*나중에 이 부분 변경 필요, 설정에서 변경한 키에 맞게끔 출력해야 함*/, "ERROR!", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {    // 사용하지 않음
        }
    }

    public void getsize(ConfigModel.BoardSize boardSize) {
        switch (boardSize){
            case LARGE -> {
                Size = 1.5;
                break;
            }
            case MEDIUM -> {
                Size = 1.25;
                break;
            }
            case SMALL -> {
                Size = 1;
                break;
            }
        }
    }
    public void setSize() {
        getsize(ConfigModel.boardSize);   //설정에서 받아와야함
        if (Size == 1) {
            VIEW_WIDTH = 400;
            VIEW_HEIGHT = 600;

        } else if (Size == 1.25){
            VIEW_WIDTH = 500;
            VIEW_HEIGHT = 750;

        } else if (Size == 1.5) {
            VIEW_WIDTH = 600;
            VIEW_HEIGHT = 900;

        }
    }
}