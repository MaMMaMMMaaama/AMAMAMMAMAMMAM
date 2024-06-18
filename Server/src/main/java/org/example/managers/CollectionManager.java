package org.example.managers;

import org.example.recources.Worker;
import org.example.recources.generator.IdGenerator;

import java.util.ArrayList;

public class CollectionManager {
    private static ArrayList<Worker> workers = new ArrayList<>();

    public static void add(Worker worker){
        workers.add(worker);
        IdGenerator.add(worker.getId());
    }
    public static void remove(Worker worker){
        workers.remove(worker);
    }

    public static ArrayList<Worker> getWorkers() {
        return workers;
    }

    public static void setWorkers(ArrayList<Worker> workers) {
        CollectionManager.workers = workers;
    }


}
