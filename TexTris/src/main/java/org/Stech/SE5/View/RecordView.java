package org.Stech.SE5.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import org.Stech.SE5.Controller.HomeController;
import org.Stech.SE5.Controller.RecordController;
import org.Stech.SE5.Model.RecordModel;


public class RecordView extends JFrame {
    private static RecordController recordController;

    private HomeController homeController;
    private JButton goToMainBtn;
    private ArrayList<JTextPane> recordList;
    public PlayerKeyListener playerKeyListener;

    public RecordView(final RecordController controller) {
        super("Record");
        recordController = controller;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.playerKeyListener = new PlayerKeyListener();

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

        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel recordPanel = new JPanel();
        recordPanel.setBackground(Color.BLACK);
        this.add(recordPanel);

        goToMainBtn = new JButton("Go To Main");
        goToMainBtn.setBounds(100, HEIGHT-84, WIDTH-210, 30);
        goToMainBtn.setBorderPainted(false); // 경계선이 보이지 않도록 설정
        goToMainBtn.setContentAreaFilled(true); //배경색이 보이도록 설정
        goToMainBtn.setBackground(Color.GRAY); //배경색은 회색
        goToMainBtn.setForeground(Color.GREEN); // 버튼 텍스트 색상을 흰색으로 설정합니다.
        goToMainBtn.setFont(new Font("Arial", Font.BOLD, 20)); //폰트 및 크기 설정

        recordPanel.add(goToMainBtn);

        JTextPane backgroundPane = new JTextPane();
        backgroundPane.setBounds(10,10,WIDTH-34,HEIGHT-56);
        backgroundPane.setBorder(new LineBorder(Color.WHITE,3));
        backgroundPane.setOpaque(false);
        recordPanel.add(backgroundPane);

        recordPanel.setLayout(new FlowLayout());

        JTextPane titlePane = new JTextPane();
        titlePane.setBounds(30, 20, WIDTH-74, 50);
        titlePane.setText("Score Board");
        titlePane.setEditable(false);
        titlePane.setBackground(Color.DARK_GRAY);
        titlePane.setForeground(Color.GREEN);
        titlePane.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));

        StyledDocument doc = titlePane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        recordPanel.add(titlePane);


        JTextPane columnNamePane = new JTextPane();
        columnNamePane.setBounds(30, 70, WIDTH-74, 30);
        columnNamePane.setText("Rank | ID | Score | Line | Mode | Level | Date");
        columnNamePane.setEditable(false);
        columnNamePane.setBackground(Color.DARK_GRAY);
        columnNamePane.setForeground(Color.GREEN);
        columnNamePane.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

        StyledDocument doc2 = columnNamePane.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);

        recordPanel.add(columnNamePane);

        // 엔터키 입력을 처리하기 위한 KeyListener 추가
        goToMainBtn.addActionListener(e -> gotoMain());
        // 버튼 클릭 이벤트 처리

        recordList = new ArrayList<>();

        System.out.println(RecordModel.rankedRecords.size());
        for(int i=0; i<Math.min(RecordModel.rankedRecords.size(), (10+resolution*4)); i++) {
            recordList.add(new JTextPane());

            // gameDifficulty 값을 문자열로 변환하여 저장
            String difficultyString;
            switch (RecordModel.rankedRecords.get(i).gameDifficulty) {
                case 0:
                    difficultyString = "EASY";
                    break;
                case 1:
                    difficultyString = "NORMAL";
                    break;
                case 2:
                    difficultyString = "HARD";
                    break;
                default:
                    difficultyString = "Unknown";
            }

            JTextPane individualRecord = new JTextPane();
            individualRecord.setText((i+1) + " | " +
                    RecordModel.rankedRecords.get(i).name + " | " +
                    RecordModel.rankedRecords.get(i).score + " | " +
                    RecordModel.rankedRecords.get(i).deletedLine + " | " +
                    (RecordModel.rankedRecords.get(i).gameMode  == 0 ? "BASIC" : "ITEM" )+ " | " +
                    difficultyString + " | " +
                    RecordModel.rankedRecords.get(i).createdAt);
            individualRecord.setBounds(30,(110+(i*40)), WIDTH-74, 40);
            individualRecord.setEditable(false);
            individualRecord.setBackground(Color.DARK_GRAY);
            individualRecord.setForeground(Color.GREEN);
            if(RecordModel.rankedRecords.get(i).id == RecordModel.lastID)
                individualRecord.setForeground(Color.PINK);
            individualRecord.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

            StyledDocument docRecord = individualRecord.getStyledDocument();
            SimpleAttributeSet centerRecord = new SimpleAttributeSet();
            StyleConstants.setAlignment(centerRecord, StyleConstants.ALIGN_CENTER);
            docRecord.setParagraphAttributes(0, docRecord.getLength(), centerRecord, false);

            recordPanel.add(individualRecord);
        }

        recordPanel.setLayout(new BorderLayout());
        recordPanel.setVisible(true);

        this.setContentPane(recordPanel);
        recordPanel.addKeyListener(this.playerKeyListener);
        recordPanel.setFocusable(true);
        recordPanel.requestFocus();
    }
    class PlayerKeyListener implements KeyListener {
        @Override
        public void keyTyped(final KeyEvent e) {}

        @Override
        public void keyPressed(final KeyEvent e) {          //일단은 기본키로 설정, 설정과 연동해서 키 값 받아와야함
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ENTER:
                    gotoMain();
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    }
    private void gotoMain(){
        homeController = new HomeController();
        homeController.setVisible(true);
        setVisible(false);
    }
}
