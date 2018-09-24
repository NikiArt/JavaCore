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


    @Test
    public void app() {
        AppTest window = new AppTest();
        window.setVisible(true);
    }

    public AppTest() {
        setTitle("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(0, 0, 400, 400);
        JButton[] jbs = new JButton[5];
        for (int i = 0; i < 5; i++) {
            jbs[i] = new JButton("#" + i);
        }
        setLayout(new BorderLayout());   // выбор компоновщика элементов
        add(jbs[0], BorderLayout.EAST);  // добавление кнопки на форму
        add(jbs[1], BorderLayout.WEST);
        add(jbs[2], BorderLayout.SOUTH);
        add(jbs[3], BorderLayout.NORTH);
        add(jbs[4], BorderLayout.CENTER);
        //setVisible(true);
    }

}
