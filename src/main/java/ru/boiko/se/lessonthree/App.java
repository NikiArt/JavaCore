package ru.boiko.se.lessonthree;

import java.util.*;

public class App {
    public static void main(String[] args) {

        //Задание перовое
        final String[] wordsArray = {"alfa", "zulu", "bravo", "gamma", "alfa", "charlie", "alfa", "zulu", "alfa", "alfa", "zulu", "zulu", "bravo", "lima"};
        final Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < wordsArray.length; i++) {
            if(map.containsKey(wordsArray[i])) {
                map.put(wordsArray[i], map.get(wordsArray[i]) + 1);
            } else {
                map.put(wordsArray[i], 1);
            }

        }

        for(Map.Entry<String, Integer> o : map.entrySet()) {
            System.out.println(o.getKey() + " встречается " + o.getValue() + " раз" + ((o.getValue() % 10 < 2 || o.getValue() % 10 >= 5) ? "." : "a."));
        }

        //Задание второе
        final Phonebook phonebook = new Phonebook();
        phonebook.add("John", "89111234567");
        phonebook.add("Richard", "89119876543");
        phonebook.add("Walter", "89211237767");
        phonebook.add("John", "89052056819");
        phonebook.add("Richard", "89855429678");
        phonebook.add("Freddy", "89855429321");
        phonebook.add("John", "89033031315");

        phonebook.get("John");
        phonebook.get("Walter");
        phonebook.get("Richard");
        phonebook.get("Adam");
    }

}
