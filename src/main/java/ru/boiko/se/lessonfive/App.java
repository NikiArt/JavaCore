package ru.boiko.se.lessonfive;

/**
 * Домашнее задание к уроку 05
 * @author Никита Бойко
 * @version 1.0
 */

public class App {
    private static final int size = 10000000;
    private static final int h = size / 2;


    public static void main(String[] args) {
        float[] array = new float[size];
        fill(array, 1);
        fillOneThread(array);
        fill(array, 1);
        fillTwoThreads(array);
    }

    private static float[] fill(float[] array, float value){
        for(float i : array ){
            i = value;
        }
        return array;
    }
    private static float[] fillOneThread(float[] array) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - a) + " мс.");
        return array;
    }

    private static float[] fillTwoThreads(float[] array) {
        long a = System.currentTimeMillis();
        float[] arrayFirst = new float[h];
        float[] arraySecond = new float[size - h];
        System.arraycopy(array, 0, arrayFirst, 0, h);
        System.arraycopy(array, h, arraySecond, 0, h);
        System.out.println("Время деления массива на два новых: " + (System.currentTimeMillis() - a) + " мс.");
        /*for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - a) + " мс.");*/
        return array;
    }
}
