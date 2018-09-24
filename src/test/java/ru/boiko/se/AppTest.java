package ru.boiko.se;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
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

}
