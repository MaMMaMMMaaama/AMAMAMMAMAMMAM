package org.example.recources.generator;

import org.example.exceeption.WrongArgumentException;
import org.example.recources.*;

import java.util.Scanner;

public class WorkerGenerator {
    public static Worker createWorker(){
        Scanner sc = new Scanner(System.in);
        Worker worker = new Worker();

        while (true){
            System.out.println("Введите имя");
            String name = sc.nextLine();
            try {
                Validator.checkName(name);
                worker.setName(name);
                break;
            } catch (WrongArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        Coordinates coordinates = new Coordinates();
        while (true){
            System.out.println("Введите X");
            String x = sc.nextLine();
            try {
                Validator.checkXCoordinates(x);
                if (x.isEmpty()){
                    break;
                }
                coordinates.setX(Float.parseFloat(x));
                break;
            } catch (WrongArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        while (true){
            System.out.println("Введите Y");
            String y = sc.nextLine();
            try {
                Validator.checkYCoordinates(y);
                if (y.isEmpty()){
                    break;
                }
                coordinates.setY(Double.parseDouble(y));
                break;
            } catch (WrongArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        worker.setCoordinates(coordinates);

        while (true){
            System.out.println("Введите зарплату работника");
            String salary = sc.nextLine();
            try {
                Validator.checkSalary(salary);
                worker.setSalary(Integer.parseInt(salary));
                break;
            } catch (WrongArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        while (true){
            System.out.println("Введите позицию (MANAGER,\n" +
                    "    HEAD_OF_DIVISION,\n" +
                    "    DEVELOPER,\n" +
                    "    LEAD_DEVELOPER;\n)");
            String position = sc.nextLine();
            try {
                worker.setPosition(Position.valueOf(position));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Выберете позицию из предложенных");
            }
        }

        while (true){
            System.out.println("Введите статус (FIRED,\n" +
                    "    RECOMMENDED_FOR_PROMOTION,\n" +
                    "    REGULAR;\n)");
            String status = sc.nextLine();
            try {
                if (status.isEmpty()){
                    break;
                }
                worker.setStatus(Status.valueOf(status));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Выберете статус из предложенных");
            }
        }

        Person person = new Person();
        while (true){
            System.out.println("Введите id паспорта");
            String passport = sc.nextLine();
            try {
                Validator.checkPassportId(passport);
                person.setPassportID(passport);
                break;
            } catch (WrongArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        while (true){
            System.out.println("Введите цвет глаз (GREEN,\n" +
                    "    RED,\n" +
                    "    WHITE,\n" +
                    "    BROWN;\n)");
            String color = sc.nextLine();
            try {
                if (color.isEmpty()){
                    break;
                }
                person.setEyeColor(Color.valueOf(color));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Выберете цвет из предложенных");
            }
        }

        while (true){
            System.out.println("Введите цвет волос (GREEN,\n" +
                    "    RED,\n" +
                    "    WHITE,\n" +
                    "    BROWN;\n)");
            String color = sc.nextLine();
            try {
                person.setHairColor(Color.valueOf(color));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Выберете цвет из предложенных");
            }
        }

        while (true){
            System.out.println("Введите страну из списка (RUSSIA,\n" +
                    "    SPAIN,\n" +
                    "    CHINA,\n" +
                    "    ITALY;\n)");
            String country = sc.nextLine();
            try {
                if (country.isEmpty()){
                    break;
                }
                person.setNationality(Country.valueOf(country));
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Выберете страну из предложенных");
            }
        }
        worker.setPerson(person);

        return worker;
    }
}
