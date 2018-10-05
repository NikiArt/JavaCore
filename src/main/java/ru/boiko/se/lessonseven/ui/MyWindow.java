package ru.boiko.se.lessonseven.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Класс создания окна чата
 * @author Никита Бойко
 * @version 1.1
 * @see MyWindow - Конструктор создания окна чата
 * @see MyWindow#chatMess - Общее окно всех сообщений
 * @see MyWindow#enterMessage - Поле ввода сообщения пользователя
 */

public class MyWindow extends JFrame {
    private JTextArea chatMess;
    private JTextField enterMessage;

    public MyWindow() {
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));

        /**
         * @param chatPanel - основная панель JFrame
         * @param footer - панель расположения поля ввода и кнопки
         */

        final JPanel chatPanel = new JPanel(new BorderLayout());
        setBounds(500, 500, 600, 400);
        final JButton answerButton = new JButton("Отправить");
        answerButton.addActionListener(event -> send());
        chatMess = new JTextArea();
        chatMess.setEditable(false);
        chatMess.setLineWrap(true);
        chatMess.setWrapStyleWord(true);
        JScrollPane chatScroll = new JScrollPane(chatMess);
        enterMessage = new JTextField(33);
        enterMessage.addActionListener(event -> send());
        enterMessage.setSize(50, 20);
        final JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(new JLabel("Ваше сообщение:"));
        footer.add(enterMessage);
        footer.add(answerButton);
        chatPanel.add(chatScroll,BorderLayout.CENTER);
        chatPanel.add(footer, BorderLayout.SOUTH);
        add(chatPanel);

    }

    private void send(){
        if(!enterMessage.getText().isEmpty()) {
            chatMess.append(enterMessage.getText() + "\n");
            enterMessage.setText("");
        }
    }
}
