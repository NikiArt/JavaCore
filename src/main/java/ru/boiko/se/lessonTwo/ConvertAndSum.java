package ru.boiko.se.lessonTwo;

public class ConvertAndSum {
    private static int arrX = 4;
    private static int arrY= 4;
    private static int strMaxLength = 2;
    public String[][] myArr;

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {
        //String[][] myArr = new String[3][5];
        //String[][] myArr = new String[4][4];
        String[][] myArr = makeArr( 4, 4, true);
        if (!checkLengthArr(myArr)) throw new MyArraySizeException();
        System.out.println("Сумма всех элементов массива равна: " + ConvertSum(myArr));
    }

    private static int ConvertSum(String[][] myArr){
        int sum = 0;
        for(int i = 0; i < myArr.length; i++) {
            for(int j = 0; j < myArr[i].length; j++) {
                try {
                    sum += Integer.parseInt(myArr[i][j]);
                } catch (NumberFormatException e) {
                    System.out.println("Проверьте корректность введенных данных в ячейке [" + i + "][" + j + "]\nСейчас введено значение - '" + myArr[i][j] + "'");
                    sum = 0;
                    return sum;
                }
            //throw new MyArrayDataException(i, j, myArr[i][j]);
            }
        }
        return sum;
    }

    private static boolean checkLengthArr(String[][] arr){
        boolean result = true;
        if(arr.length != arrX || arr[0].length != arrY) {
            result = false;
        }
        return result;
    }

    private static String[][] makeArr(int x, int y, boolean num){
        String[][] myArr = new String[x][y];                        //создаем массив
        String numbers = "0123456789";                              //строка из цифр для формирования "строковых" чисел
        String symbols =  "0123456789qwertyuiopasdfghjklzxcvbnm";   //строка из символов для формирвоания любой строки
        for(int i = 0; i < myArr.length; i++) {                     //циклы для заполнения массива
            System.out.print("[\t");
            for(int j = 0; j < myArr[i].length; j++) {
                String newString = "";
                int strLength = 1 + (int)(Math.random()*(strMaxLength));  //случайная длина текущей строки от 1
                for(int l = 0; l < strLength; l++) {                      //до максимальной длины, указанной в константе
                    if (num) {
                        int symbNumber = (int)(Math.random()*numbers.length());
                        newString += numbers.substring(symbNumber, symbNumber+1);  //подбор символа
                    } else {
                        int symbNumber = (int)(Math.random()*symbols.length());
                        newString += symbols.substring(symbNumber, symbNumber+1);
                    }
                }
                myArr[i][j] = newString;                                           //сбор строки
                System.out.print("" + newString + "\t");
            }
            System.out.print("]\n\n");
        }
        return myArr;
    }
}
