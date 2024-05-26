package org.Stech.SE5.View;

import org.junit.jupiter.api.Test;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import static org.junit.jupiter.api.Assertions.*;

class GameEndViewTest {

    @Test
    void testRecordButtonAction() {
        // Given
        int score = 1000;
        int gameMode = 1;
        int gameDifficulty = 2;
        int deletedLine = 5;
        GameEndView gameEndView = new GameEndView(score, gameMode, gameDifficulty, deletedLine);

        // When
        Component[] components = gameEndView.getContentPane().getComponents();
        JButton recordButton = null;
        for (Component component : components) {
            if (component instanceof JButton && ((JButton) component).getText().equals("Record Score")) {
                recordButton = (JButton) component;
                break;
            }
        }

        // Then
        assertNotNull(recordButton);
        assertTrue(recordButton.isVisible()); // 버튼이 보이는지 확인

        // 버튼 클릭 시 이벤트 처리 테스트
        recordButton.doClick();
        // 여기서는 이벤트가 예외를 발생시키지 않고 정상적으로 처리되었는지 확인하는 코드를 작성해야 합니다.
    }

    @Test
    void testUserNameFieldVisibility() {
        // Given
        int score = 1000;
        int gameMode = 1;
        int gameDifficulty = 2;
        int deletedLine = 5;
        GameEndView gameEndView = new GameEndView(score, gameMode, gameDifficulty, deletedLine);

        // When
        Component[] components = gameEndView.getContentPane().getComponents();
        JTextField userNameField = null;
        for (Component component : components) {
            if (component instanceof JTextField) {
                userNameField = (JTextField) component;
                break;
            }
        }

        // Then
        assertNotNull(userNameField);
        assertTrue(userNameField.isVisible()); // 사용자 이름 입력 필드가 보이는지 확인
    }
}
