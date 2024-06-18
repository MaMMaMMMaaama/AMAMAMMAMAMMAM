package org.example.recources.comporators;

import org.example.recources.Person;

import java.util.Comparator;

public class PersonComporator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {
        return (o1.getPassportID().length() - o2.getPassportID().length());
    }
    public PersonComporator(){

    }
}
