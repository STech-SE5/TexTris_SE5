package main.java.org.Stech.SE5.Model;

import java.awt.*;

public enum Element {
    EMPTY, BORDER, I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK, DELETE
    //아이템 추가해야함

    public static Color getElementColor(final Element element) {
        if (false/*설정쪽에서 Blindmode에 관한 변수 받아오기*/) {
            return switch (element) {
                case EMPTY -> Color.BLACK;
                case BORDER, DELETE -> Color.WHITE;
                case I_BLOCK -> Color.decode("#0074B6"); //Sky Blue
                case J_BLOCK -> Color.decode("#1BB7ED"); //Blue
                case L_BLOCK -> Color.decode("#F19B00"); //Orange
                case O_BLOCK -> Color.decode("#F3E300"); //Yellow
                case S_BLOCK -> Color.decode("#00A270"); //Bluish Green
                case T_BLOCK -> Color.decode("#D974A9"); //Reddish Purple
                case Z_BLOCK -> Color.decode("#E65400"); //Vermilion
            };
        } else {
            return switch (element) {
                case EMPTY -> Color.BLACK;
                case BORDER, DELETE -> Color.WHITE;
                case I_BLOCK -> Color.CYAN;
                case J_BLOCK -> Color.BLUE;
                case L_BLOCK -> Color.ORANGE;
                case O_BLOCK -> Color.YELLOW;
                case S_BLOCK -> Color.GREEN;
                case T_BLOCK -> Color.MAGENTA;
                case Z_BLOCK -> Color.RED;
            };
        }

    }

    public static String getElementText(final Element element) {
        return switch (element) {
            case EMPTY -> " ";
            case BORDER -> "X";
            case I_BLOCK, J_BLOCK, L_BLOCK, O_BLOCK, S_BLOCK, T_BLOCK, Z_BLOCK, DELETE -> "O";
        };
    }
}
