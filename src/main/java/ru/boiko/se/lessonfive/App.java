package ru.boiko.se.lessonfive;

import lombok.SneakyThrows;

/**
 * Домашнее задание к уроку 05
 * @author Никита Бойко
 * @version 1.0
 */

public class App{
    private static final int size = 10000000;
    private static final int h = size / 2;

    public static void main(String[] args) {
        /**
         * @see fill() - метод заполнения массива единицами
         * @see fillOneThread - метод обработки массива в однопоточном режиме
         * @see fillTwoThreads - метод обработки массива в многопоточном режиме
         */
        float[] array = new float[size];
        fill(array, 1);
        System.out.println("Задача 1. Однопоточный расчет значений элементов массива");
        fillOneThread(array);
        fill(array, 1);
        System.out.println("\nЗадача 2. Многопоточный расчет значений элементов массива");
        fillTwoThreads(array);
    }

    private static float[] fill(float[] array, float value){
        for(int i = 0; i < size; i++){
            array[i] = value;
        }
        return array;
    }
    
    private static void fillOneThread(float[] array) {
        ArrayTask arraySingle = new ArrayTask(array);
        arraySingle.run();
    }

    @SneakyThrows
    private static float[] fillTwoThreads(float[] array) {
        long a = System.currentTimeMillis();
        float[] arrayFirst = new float[h];
        float[] arraySecond = new float[size - h];
        System.arraycopy(array, 0, arrayFirst, 0, h);
        System.arraycopy(array, h, arraySecond, 0, size - h);
        System.out.println("Время деления массива на два новых: " + (System.currentTimeMillis() - a) + " мс.");

        ArrayTask arrayFirstTask = new ArrayTask(arrayFirst, 0);
        Thread arrayFirstThread = new Thread(arrayFirstTask);

        ArrayTask arraySecondTask = new ArrayTask(arraySecond, h);
        Thread arraySecondThread = new Thread(arraySecondTask);

        arrayFirstThread.start();
        arraySecondThread.start();

        arrayFirstThread.join();
        arraySecondThread.join();

        long b = System.currentTimeMillis();
        System.arraycopy(arrayFirstTask.getArray(), 0, array, 0, h);
        System.arraycopy(arraySecondTask.getArray(), 0, array, h, size - h);
        System.out.println("Время склейки массивов: " + (System.currentTimeMillis() - b) + " мс.");

        System.out.println("Общее время выполнения: " + (System.currentTimeMillis() - a) + " мс.");
        return array;
    }
}
