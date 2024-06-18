package org.example.recources;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 78L;
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 5, Длина строки не должна быть больше 35, Поле может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null

    public Person(){

    }

    public Person(String passportID, Color eyeColor, Color hairColor, Country nationality) {
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }
}

