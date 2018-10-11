package ru.boiko.se.chat.ui;

import ru.boiko.se.chat.packets.Packet;
import ru.boiko.se.chat.packets.PacketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final CardLayout card;
    private final JPanel mainPanel;
    private final JPanel loginPanel;
    private final JPanel chatPanel;
    private final JPanel registrationPanel;
    private final JTextField loginInput;
    private final JTextField passwordInput;

    public MyWindow() {
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));

        /**
         * @param chatPanel - основная панель JFrame
         * @param footer - панель расположения поля ввода и кнопки
         */
        card = new CardLayout();
        mainPanel = new JPanel(card);

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
        loginInput = new JTextField(33);
        loginInput.setSize(50, 20);
        passwordInput = new JTextField(33);
        passwordInput.setSize(50, 20);
        final JButton loginChat = new JButton("Войти");
        loginChat.addActionListener(event -> send(PacketType.LOGIN));
        final JButton registration = new JButton("Зарегистрироваться");
        registration.addActionListener(event -> changeFrame(PacketType.REGISTRY));
        loginPanel.add(loginInput);
        /*loginPanel.add(passwordInput);
        loginPanel.add(loginChat);
        loginPanel.add(registration);*/

        registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel,BoxLayout.Y_AXIS));
        final JButton registry = new JButton("Зарегистрироваться");
        registry.addActionListener(event -> send(PacketType.REGISTRY));
        final JButton cancel = new JButton("Отмена");
        registry.addActionListener(event -> changeFrame(PacketType.LOGIN));
        registrationPanel.add(loginInput);
        registrationPanel.add(passwordInput);
        registrationPanel.add(registry);
        registrationPanel.add(cancel);

        chatPanel = new JPanel(new BorderLayout());
        setBounds(500, 500, 600, 400);
        final JButton answerButton = new JButton("Отправить");
        answerButton.addActionListener(event -> send(PacketType.MESSAGE));
        chatMess = new JTextArea();
        chatMess.setEditable(false);
        chatMess.setLineWrap(true);
        chatMess.setWrapStyleWord(true);
        JScrollPane chatScroll = new JScrollPane(chatMess);
        enterMessage = new JTextField(33);
        enterMessage.addActionListener(event -> send(PacketType.MESSAGE));
        enterMessage.setSize(50, 20);
        final JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(new JLabel("Ваше сообщение:"));
        footer.add(enterMessage);
        footer.add(answerButton);
        chatPanel.add(chatScroll,BorderLayout.CENTER);
        chatPanel.add(footer, BorderLayout.SOUTH);

        mainPanel.add(loginPanel,"1");
        //mainPanel.add(registrationPanel,"2");
        //mainPanel.add(chatPanel,"3");

        add(mainPanel);
        //card.show(mainPanel,"1");

        changeFrame(PacketType.LOGIN);

    }

    private void changeFrame(PacketType packetType) {
        int xxx = (int)(Math.random()*3);
        card.show(mainPanel,""+xxx);
        /*switch (packetType) {
            case REGISTRY:
                card.show(mainPanel,"2");
                loginInput.setText("");
                passwordInput.setText("");
            case LOGIN:
                card.show(mainPanel,"1");
                loginInput.setText("");
                passwordInput.setText("");
            case MESSAGE:
                *//*chatPanel.setVisible(true);
                registrationPanel.setVisible(false);
                loginPanel.setVisible(false);*//*
                card.show(mainPanel,"3");
                loginInput.setText("");
                passwordInput.setText("");
        }*/
    }

    private void send(PacketType packetType){
        final Packet packet = new Packet();
        switch (packetType) {
            case REGISTRY:
                packet.setMessage("registration");
                packet.setType(PacketType.REGISTRY);
                packet.setLogin(loginInput.getText());
                packet.setPassword(passwordInput.getText());
            case LOGIN:
                packet.setMessage("login");
                packet.setType(PacketType.LOGIN);
                packet.setLogin(loginInput.getText());
                packet.setPassword(passwordInput.getText());
            case MESSAGE:
                packet.setMessage(enterMessage.getText());
                packet.setType(PacketType.MESSAGE);
        }
    }
}
