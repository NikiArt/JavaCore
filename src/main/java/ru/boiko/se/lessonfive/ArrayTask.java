package ru.boiko.se.lessonfive;

/**
 * Домашнее задание к уроку 05
 * @author Никита Бойко
 * @version 1.0
 * @see ru.boiko.se.lessonfive.ArrayTask#ArrayTask() - перегруженный конструктор класса с различным набором параметров
 * @param array - обрабатываемый массив
 * @param startValue - сдвиг для корректого пересчета значений массива
 * @see ru.boiko.se.lessonfive.ArrayTask#getArray() - геттер для возврата массива (для дальнейшей склейки)
 * @see ru.boiko.se.lessonfive.ArrayTask#run() - переопределение метода из интерфейса Runnable. Также используется при обработке однопоточного метода
 */

import lombok.SneakyThrows;

public class ArrayTask implements Runnable {
    private final float[] array;
    private final int startValue;

    public ArrayTask(float[] array, int startValue){
        this.array = array;
        this.startValue = startValue;
    }

    public ArrayTask(float[] array){
        this.array = array;
        this.startValue = 0;
    }

    @Override
    @SneakyThrows
    public void run() {
        final long startTime = System.currentTimeMillis();
        arrayCalc();
        System.out.println("Время расчета массива: " + (System.currentTimeMillis() - startTime) + " мс.");
    }

    public float[] getArray(){
        return this.array;
    }

    private void arrayCalc(){
        for(int i = 0; i < array.length; i++) {
            array[i] = (float)(array[i] * Math.sin(0.2f + (startValue + i) / 5) * Math.cos(0.2f + (startValue + i) / 5) * Math.cos(0.4f + (startValue + i) / 2));
        }
    }
}