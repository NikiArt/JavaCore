package ru.boiko.se.lessonfour;

/**
 * Домашнее задание к уроку 04
 * @autor Никита Бойко
 * @version 1.1
 * @see MyWindow  - Объект создания окна чата
 */

public class App {
    public static void main(String[] args) {
        MyWindow chatWindow = new MyWindow();
        chatWindow.setLocationRelativeTo(null);
        chatWindow.setVisible(true);
    }
}
