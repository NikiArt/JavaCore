package ru.boiko.se.chat.ui;

/**
 * Домашнее задание к уроку 04
 * @author Никита Бойко
 * @version 1.1
 * @see MyWindow  - Объект создания окна чата
 */

public class App {
    public static void main(String[] args) {
        final MyWindow chatWindow = new MyWindow();
        chatWindow.setLocationRelativeTo(null);
        chatWindow.setVisible(true);
    }
}
