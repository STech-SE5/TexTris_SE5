package org.Stech.SE5.View;

import org.Stech.SE5.Controller.RecordController;
import org.Stech.SE5.Model.RecordModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameEndView extends JFrame {
    private JTextField userNameField;

    public GameEndView(int score, int gameMode, int gameDifficulty) {
        LocalDate date = LocalDate.now();
        String formattedDate = String.format("%02d%02d", date.getMonthValue(), date.getDayOfMonth());

        int currentScore = score;
        int currentGameMode = gameMode;
        int currentGameDifficulty = gameDifficulty;

        int resolution = 1; // 해상도 설정시 값을 불러와서 대입.

        int WIDTH;
        int HEIGHT;
        switch (resolution) {
            case 0:
                WIDTH = 400;
                HEIGHT = 600;
                break;
            case 1:
                WIDTH = 500;
                HEIGHT = 750;
                break;
            case 2:
                WIDTH = 600;
                HEIGHT = 900;
                break;
            default:
                WIDTH = 400;
                HEIGHT = 600;
        }

        setTitle("Game End");
        setSize(WIDTH, HEIGHT);
        setLayout(null);
        setLocationRelativeTo(null); // 창을 화면 가운데에 위치시킵니다.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);
        backgroundPanel.setBackground(Color.BLACK);
        backgroundPanel.setLayout(null);
        backgroundPanel.setLocation(0, 0); // 창을 화면 가운데에 위치시킵니다.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(backgroundPanel);

        // 게임 종료 메시지
        JLabel gameEndTitle = new JLabel("Game End");
        gameEndTitle.setBounds(20, 20, WIDTH-46, 60);
        gameEndTitle.setFont(new Font("Bold", Font.BOLD, 30));
        gameEndTitle.setBackground(Color.DARK_GRAY);
        gameEndTitle.setForeground(Color.GREEN);
        gameEndTitle.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(gameEndTitle);

        // 점수 표시
        JLabel scoreLabel = new JLabel("Your score: " + currentScore);
        scoreLabel.setBounds(98, 100, WIDTH-200, 30);
        scoreLabel.setFont(new Font("Bold", Font.BOLD, 20));
        scoreLabel.setBackground(Color.DARK_GRAY);
        scoreLabel.setForeground(Color.GREEN);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(scoreLabel);

        // 사용자 이름 입력 필드
        userNameField = new JTextField();
        userNameField.setBounds(98, 150, WIDTH-200, 50);
        userNameField.setBackground(Color.GRAY);
        backgroundPanel.add(userNameField);

        // 점수 기록 버튼
        JButton recordButton = new JButton("Record Score");
        recordButton.setBounds(100, HEIGHT-200, WIDTH-200, 50);
        recordButton.setBackground(Color.GRAY);
        recordButton.setForeground(Color.GREEN);
        backgroundPanel.add(recordButton);



        // 엔터키 입력을 처리하기 위한 KeyListener 추가
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String userName = userNameField.getText();

                    // Score Board 화면으로 이동하는 로직
                    RecordModel.loadRecord();
                    RecordModel.addRecord(currentScore, 10, currentGameMode,currentGameDifficulty,formattedDate, userName);
                    RecordController record = new RecordController();
                    record.setVisible(true);

                    // 현재 창을 숨김
                    setVisible(false);
                }
            }
        });

        // 버튼 클릭 이벤트 처리
        recordButton.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               String userName = userNameField.getText();

                                               // Score Board 화면으로 이동하는 로직
                                               RecordModel.loadRecord();
                                               RecordModel.addRecord(currentScore, 10,0,1, formattedDate, userName);
                                               RecordController record = new RecordController();
                                               record.setVisible(true);

                                               // 현재 창을 숨김
                                               setVisible(false);
                                           }
                                       });

        setVisible(true);
    }
}
