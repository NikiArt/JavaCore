package ru.boiko.se.lessonfive;

import lombok.SneakyThrows;

public class ArrayTask implements Runnable {
    private float[] array;
    private int startValue;

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
        final long a = System.currentTimeMillis();
        arrayCalc();
        System.out.println("Время расчета массива: " + (System.currentTimeMillis() - a) + " мс.");
    }

    public float[] getArray(){
        return this.array;
    }

    private void arrayCalc(){
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (startValue + i) / 5) * Math.cos(0.2f + (startValue + i) / 5) * Math.cos(0.4f + (startValue + i) / 2));
        }
    }
}