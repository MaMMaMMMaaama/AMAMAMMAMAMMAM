package org.example.exceeption;

public class NoElementException extends Exception{
    public NoElementException(){
        super("Элемента с введенными параметрами не существует");
    }
}
