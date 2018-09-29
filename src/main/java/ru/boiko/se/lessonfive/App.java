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

    static class ArrayTask implements Runnable {
        private float[] array;
        @Override
        @SneakyThrows
        public void run() {
            long a = System.currentTimeMillis();
            for (int i = 0; i < array.length; i++) {
                array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.out.println("Время расчета массива: " + (System.currentTimeMillis() - a) + " мс.");
        }
    }

    public static void main(String[] args) {

        /**
         * @see fill() - метод заполнения массива единицами
         * @see fillOneThread - метод обработки массива в однопоточном режиме
         * @see fillTwoThreads - метод обработки массива в многопоточном режиме
         */
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
        System.out.println("Время выполнения расчета полного массива без деления: " + (System.currentTimeMillis() - a) + " мс.");
        return array;
    }

    @SneakyThrows
    private static float[] fillTwoThreads(float[] array) throws InterruptedException {
        long a = System.currentTimeMillis();
        float[] arrayFirst = new float[h];
        float[] arraySecond = new float[size - h];
        System.arraycopy(array, 0, arrayFirst, 0, h);
        System.arraycopy(array, h, arraySecond, 0, h);
        System.out.println("Время деления массива на два новых: " + (System.currentTimeMillis() - a) + " мс.");

        ArrayTask arrayFirstTask = new ArrayTask();
        arrayFirstTask.array = arrayFirst;
        Thread arrayFirstThread = new Thread(arrayFirstTask);

        ArrayTask arraySecondTask = new ArrayTask();
        arraySecondTask.array = arraySecond;
        Thread arraySecondThread = new Thread(arrayFirstTask);

        arrayFirstThread.start();
        arraySecondThread.start();

        arrayFirstThread.join();
        arraySecondThread.join();

        System.out.println("Общее время выполнения: " + (System.currentTimeMillis() - a) + " мс.");
        return array;
    }
}
