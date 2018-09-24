package ru.boiko.se;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest extends JFrame
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() { assertTrue( true );
    }

    @Test
    public void newArr()
    {
        char[][] arr = new char[5][3];
        System.out.println(arr.length + " " + arr[0].length);
    }

    @Test
    public void main() {

        final String[] wordsArray = {"alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa","alfa", "zulu", "bravo", "gamma", "alfa", "charlie", "alfa", "zulu"};
        final Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < wordsArray.length; i++){
            if (map.containsKey(wordsArray[i])) {
                map.put(wordsArray[i], map.get(wordsArray[i]) + 1);
            } else {
                map.put(wordsArray[i], 1);
            }
        }
        System.out.println(map);

        for (Map.Entry < String, Integer > o : map.entrySet()) {
            System.out.println(o.getKey() + " встречается " + o.getValue() + " раз" + ((o.getValue() % 10 < 2 || o.getValue() % 10 >= 5) ? "." : "a."));
        }
    }


    private JTextArea chatMess;
    private JTextField enterMessage;

    @Test
    public void appFrameTest() {
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JPanel chatPanel = new JPanel(new BorderLayout());
        setBounds(500, 500, 600, 400);
        final JButton answerButton = new JButton("Отправить");
       // answerButton.addActionListener(event -> send());
        chatMess = new JTextArea();
        chatMess.setEditable(false);
        chatMess.setLineWrap(true);
        chatMess.setWrapStyleWord(true);
        JScrollPane chatScroll = new JScrollPane(chatMess);
        enterMessage = new JTextField(33);
       // enterMessage.addActionListener(event -> send());
        enterMessage.setSize(50, 20);
        final JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(new JLabel("Ваше сообщение:"));
        footer.add(enterMessage);
        footer.add(answerButton);
        chatPanel.add(chatScroll,BorderLayout.CENTER);
        chatPanel.add(footer, BorderLayout.SOUTH);
        add(chatPanel);
        setVisible(true);
    }

}
