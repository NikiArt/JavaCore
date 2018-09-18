package ru.boiko.se.lessonTwo;

public class ConvertAndSum {
    private static int arrX = 4;
    private static int arrY= 4;

    public static void main(String[] args) throws MyArraySizeException {
        char[][] myArr = new char[3][5];
        //char[][] myArr = new char[4][4];
        if (!checkLengthArr(myArr)) throw new MyArraySizeException();
        System.out.println("+");
    }

    private static boolean checkLengthArr(char[][] arr){
        boolean result = true;
        if(arr.length != arrX || arr[0].length != arrY) {
            result = false;
        }
        return result;
    }
}
