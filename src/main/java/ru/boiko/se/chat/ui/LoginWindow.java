package ru.boiko.se.chat.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.boiko.se.chat.packets.Packet;
import ru.boiko.se.chat.packets.PacketType;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LoginWindow extends JFrame{
    
    private JButton loginButton;
    private JButton registryButton;
    private JPanel mainPanel;
    private JTextField loginInput;
    private JTextField passwordInput;
    private DataOutputStream stream;
    private PrintWriter outMessage;
    private Socket socket;
    private JLabel textLabel;
    private DataInputStream in;

    @SneakyThrows
    public LoginWindow(Socket socket) {
        this.socket = socket;
        stream = new DataOutputStream(socket.getOutputStream());
        outMessage = new PrintWriter(socket.getOutputStream());
        mainPanel = new JPanel();
        loginInput = new JTextField();
        textLabel = new JLabel();
        textLabel.setText("Авторизируйтесь.");
        passwordInput = new JTextField();
        loginButton = new JButton();
        loginButton.addActionListener(event -> send());
        registryButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginButton.setText("Login");

        registryButton.setText("Registry");

        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(114, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addComponent(loginButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(registryButton))
                                        .addComponent(textLabel)
                                        .addComponent(loginInput)
                                        .addComponent(passwordInput))
                                .addContainerGap(140, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap(72, Short.MAX_VALUE)
                                .addComponent(textLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(loginInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginButton)
                                        .addComponent(registryButton))
                                .addContainerGap(140, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    @SneakyThrows
    private void send() {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        @NotNull final Packet packet = new Packet();
        packet.setType(PacketType.LOGIN);
        packet.setLogin(loginInput.getText());
        packet.setPassword(passwordInput.getText());
        packet.setMessage("Logging in user ...");
        /*stream.writeUTF(objectMapper.writeValueAsString(packet));
        stream.flush();*/
        outMessage.println(objectMapper.writeValueAsString(packet));
        outMessage.flush();
        final Scanner scanner = new Scanner(socket.getInputStream());
        in = new DataInputStream(socket.getInputStream());
        while (true) {
            /*String str = in.readUTF();
            textLabel.setText(str);*/
            if (scanner.hasNext()) {
                String mess = scanner.nextLine();
                System.out.println(mess);
                textLabel.setText(mess);
                break;
            }
        }
    }
}

