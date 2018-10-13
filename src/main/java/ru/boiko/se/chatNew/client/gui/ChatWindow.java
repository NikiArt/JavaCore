package ru.boiko.se.chatNew.client.gui;

import ru.boiko.se.chatNew.client.MessageSender;

import javax.swing.*;

public class ChatWindow extends JFrame {

    private JButton sendButton;
    private JList<String> userList;
    private JScrollPane userScroll;
    private JScrollPane chatScroll;
    private JTextArea chatArea;
    private JTextField inputText;
    
    public ChatWindow(MessageSender messageSender) {
        userScroll = new JScrollPane();
        userList = new JList<>();
        inputText = new JTextField();
        chatScroll = new JScrollPane();
        chatArea = new JTextArea();
        sendButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        userScroll.setViewportView(userList);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatScroll.setViewportView(chatArea);

        sendButton.setText("Отправить");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(inputText, GroupLayout.Alignment.LEADING)
                                        .addComponent(chatScroll, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(userScroll)
                                        .addComponent(sendButton, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chatScroll)
                                        .addComponent(userScroll, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(inputText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sendButton)))
        );

        pack();
        setLocationRelativeTo(null);
    }
}

