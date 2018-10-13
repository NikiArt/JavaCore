package ru.boiko.se.chatNew.client.gui;

import ru.boiko.se.chatNew.client.MessageSender;

import javax.swing.*;
import java.awt.*;

public class ChatWindow extends JFrame {
    private final JPanel chatPanel;
    private JTextArea chatMess;
    private JTextField enterMessage;
    private MessageSender messageSender;

    public ChatWindow(MessageSender messageSender) {
        this.messageSender = messageSender;
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 400));

        chatPanel = new JPanel(new BorderLayout());
        setBounds(500, 500, 600, 400);
        final JButton answerButton = new JButton("Отправить");
       // answerButton.addActionListener(event -> send(PacketType.MESSAGE));
        chatMess = new JTextArea();
        chatMess.setEditable(false);
        chatMess.setLineWrap(true);
        chatMess.setWrapStyleWord(true);
        JScrollPane chatScroll = new JScrollPane(chatMess);
        enterMessage = new JTextField(33);
        //enterMessage.addActionListener(event -> send(PacketType.MESSAGE));
        enterMessage.setSize(50, 20);
        final JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(new JLabel("Ваше сообщение:"));
        footer.add(enterMessage);
        footer.add(answerButton);
        chatPanel.add(chatScroll,BorderLayout.CENTER);
        chatPanel.add(footer, BorderLayout.SOUTH);

        add(chatPanel);

    }
}
