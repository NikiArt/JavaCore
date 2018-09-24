package ru.boiko.se.lessonfour;

import javax.swing.*;
import java.awt.*;

public class MyWindow extends JFrame {
    private JTextArea chatMess;
    private JTextField enterMessage;

    public MyWindow() {
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel chatPanel = new JPanel(new BorderLayout());
        setBounds(500, 500, 500, 400);
        JButton answerButton = new JButton("Отправить");
        answerButton.addActionListener(event -> send());
        chatMess = new JTextArea();
        chatMess.setEditable(false);
        chatMess.setLineWrap(true);
        chatMess.setWrapStyleWord(true);
        JScrollPane chatScroll = new JScrollPane(chatMess);
        enterMessage = new JTextField(33);
        enterMessage.addActionListener(event -> send());
        enterMessage.setSize(50, 20);
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(enterMessage);
        footer.add(answerButton);
        chatPanel.add(chatScroll,BorderLayout.CENTER);
        chatPanel.add(footer, BorderLayout.SOUTH);
        add(chatPanel);
    }

    private void send(){
        if(!"".equals(enterMessage.getText())) {
            chatMess.append(enterMessage.getText() + "\n");
            enterMessage.setText("");
        }
    }
}
