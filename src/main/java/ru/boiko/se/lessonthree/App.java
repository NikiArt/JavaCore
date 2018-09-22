package ru.boiko.se.lessonthree;

import java.util.*;

public class App {
    public static void main(String[] args) {

        String[] wordsArray = {"alfa", "zulu", "bravo", "gamma", "alfa", "charlie", "alfa", "zulu", "alfa", "alfa", "zulu", "zulu", "bravo", "lima"};
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < wordsArray.length; i++) {
            if (map.containsKey(wordsArray[i])) {
                map.put(wordsArray[i], map.get(wordsArray[i]) + 1);
            } else {
                map.put(wordsArray[i], 1);
            }

        }

        for (Map.Entry < String, Integer > o : map.entrySet()) {
            System.out.println(o.getKey() + " встречается " + o.getValue() + " раз" + ((o.getValue() % 10 < 2 || o.getValue() % 10 >= 5) ? "." : "a."));
        }
    }

}
