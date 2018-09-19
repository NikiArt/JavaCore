package ru.boiko.se.lessontwo;

public class ConvertAndSum {
    private static int arrX = 4;                //настройка ожидаемого размера массива
    private static int arrY= 4;                 //настройка ожидаемого размера массива
    private static int strMaxLength = 2;        //установка максимальной длины значения в ячейках массива
    public String[][] myArray;

    public static void main(String[] args){
        String[][] myArr = createArr( 4, 4, true);   //формируем массив. num - только числа или все символы
        try{
            System.out.println("Сумма всех элементов массива равна: " + convertSum(myArr));
        }catch (MyArraySizeException e){
            System.out.println(e.getMessage() + "\nСоздайте массив " + arrX + " на " + arrY);
        }catch (MyArrayDataException e){
            System.out.println(e.getMessage());
        }
    }

    private static int convertSum(String[][] myArray) throws MyArrayDataException, MyArraySizeException{
        if (!checkLengthArr(myArray)) throw new MyArraySizeException();
        String regex = "\\d+";
        int sum = 0;
        for(int i = 0; i < myArray.length; i++){
            for(int j = 0; j < myArray[i].length; j++){
                if(!myArray[i][j].matches(regex)) throw new MyArrayDataException(i, j, myArray[i][j]);
                sum += Integer.parseInt(myArray[i][j]);
            }
        }
        return sum;
    }

    private static boolean checkLengthArr(String[][] arr){
        boolean result = true;
        if(arr.length != arrX || arr[0].length != arrY){
            result = false;
        }
        return result;
    }

    private static String[][] createArr(int x, int y, boolean num){
        StringBuilder notify = new StringBuilder("Текущий массив:\n");
        String[][] myArray = new String[x][y];                        //создаем массив
        String numbers = "0123456789";                              //строка из цифр для формирования "строковых" чисел
        String symbols =  "0123456789qwer";                         //строка из символов для формирвоания любой строки
        for(int i = 0; i < myArray.length; i++){                      //циклы для заполнения массива
            notify.append("[\t");
            for(int j = 0; j < myArray[i].length; j++){
                StringBuilder newString = new StringBuilder();
                int strLength = 1 + (int)(Math.random()*(strMaxLength));  //случайная длина текущей строки от 1
                for(int l = 0; l < strLength; l++){                       //до максимальной длины, указанной в константе
                    if (num){
                        int symbNumber = (int)(Math.random()*numbers.length());
                        newString.append(numbers.substring(symbNumber, symbNumber + 1));       //подбор символа
                    }else{
                        int symbNumber = (int) (Math.random() * symbols.length());
                        newString.append(symbols.substring(symbNumber, symbNumber + 1));       //подбор символа
                    }
                }
                notify.append(newString).append("\t");
                myArray[i][j] = newString.toString();                                            //сбор строки
            }
            notify.append("]\n");
        }
        System.out.println(notify);
        return myArray;
    }
}
