package org.example.recources.generator;

import java.util.ArrayList;

public class IdGenerator {
    private static IdGenerator instance;
    private static final Integer min = 1000000;
    private static final Integer max = 10000000;
    private static ArrayList<Integer> IdList = new ArrayList<>();

    private IdGenerator() {
        IdList = new ArrayList<>();
    }
    public static synchronized IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public static Integer generateId() {
        Integer id = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
        while (IdList.contains(id)) {
            id = (int) Math.floor(Math.random() * (max - min + 1)) + min;
        }
        IdList.add(id);
        return id;
    }

    public static boolean idIsUnique(Integer id) {
        if (IdList.contains(id)) {
            return false;
        }
        return true;
    }

    public static void add(Integer id) {
        IdList.add(id);
    }

    public static void remove(Integer id) {
        IdList.remove(id);
    }
    public static boolean idInList(Integer id){
        return IdList.contains(id);
    }
    public static void clear(){
        IdList.clear();
    }
}
