package org.example.exceeption;

public class WrongArgumentException extends Exception{
    public WrongArgumentException(){
        super("Что-то не так с введенным аргументом");
    }
}
