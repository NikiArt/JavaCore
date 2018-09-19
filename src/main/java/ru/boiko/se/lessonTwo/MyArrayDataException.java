package ru.boiko.se.lessonTwo;

public class MyArrayDataException extends NumberFormatException{
    public MyArrayDataException() {
        super("Не удается преобразовать значение в число.");
    }
    public MyArrayDataException(int x, int y, String arg) {
        super("Не удается преобразовать значение в число. Проверьте корректность введенных данных в ячейке [" + x + "][" + y + "]\nСейчас введено значение - '" + arg + "'");
    }
}
