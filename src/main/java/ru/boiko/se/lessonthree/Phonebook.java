package ru.boiko.se.lessonthree;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private final Map<String, String> phones = new HashMap<>();

    public void add(String name, String phone){
        phones.put(phone, name);
    }

    public void get(String name){
        boolean findPhone = false;
        String answer = "";
        answer += "По записи '" + name + "' найдены следующие номера:\n";
        for(Map.Entry<String, String> o : phones.entrySet()) {
            if (o.getValue().equals(name)) {
                findPhone = true;
                answer += o.getKey() + "\n";
            }
        }
        if(findPhone) {
            System.out.println(answer);
        }else {
            System.out.println("По записи '" + name + "' телефонов не найдено");
        }
    }
}
