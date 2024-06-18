package org.example.system;

import org.example.exceeption.NoElementException;
import org.example.exceeption.RootException;
import org.example.exceeption.WrongArgumentException;
import org.example.managers.*;
import org.example.recources.*;
import org.example.recources.comporators.PersonComporator;
import org.example.recources.generator.WorkerGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class Receiver {
    public static String addNewWorker(Worker worker){
        CollectionManager.add(worker);
        return "Элемент добавлен в коллекцию";
    }
    public static String showWorkers(){
        StringBuilder st = new StringBuilder();
        ArrayList<Worker> workers = CollectionManager.getWorkers();
        for (Worker worker: workers){
            st.append(worker).append("\n");
        }
        return st.toString();
    }

    public static String clearCollection(){
        CollectionManager.setWorkers(new ArrayList<Worker>());
        return "Коллекция была очищена";
    }

    public static String updateById(String line, Worker newWorker) throws WrongArgumentException, NoElementException {
        Validator.checkId(line);
        Integer id = Integer.parseInt(line);;
        for (Worker worker: CollectionManager.getWorkers()){
            if (Objects.equals(worker.getId(), id)){
                CollectionManager.remove(worker);
                newWorker.setId(id);
                CollectionManager.add(newWorker);
                return "Данные о работнике обновлены";
            }
        }
        return "Не удалось найти работника";
    }

    public static void insertWorker(String position,Worker worker) {
        try {
            int index = Integer.parseInt(position);
            ArrayList<Worker> list = CollectionManager.getWorkers();
            if (index > 0 && index < CollectionManager.getWorkers().size()) {
                ArrayList<Worker> newList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    if (i == index) {
                        if(worker == null) {
                            newList.add(worker);
                        }else newList.add(worker);
                    }
                    newList.add(list.get(i));
                }
                CollectionManager.setWorkers(newList);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String showHelp() {
        String a = "";
        for (String key: CommandManager.getCommandList().keySet()){
            a += CommandManager.getCommandList().get(key).getName() + " - " + CommandManager.getCommandList().get(key).getDescription() + "\n";
        }
        System.out.println(a);
        return a;
    }

    public static String showInfo() {
        String info = "Data type - " + CollectionManager.getWorkers().getClass().getName();
        info += "\nCount of person - " + CollectionManager.getWorkers().size();
        return info;
    }

    public static String removeById(String line) throws WrongArgumentException, NoElementException {
        Validator.checkId(line);
        Integer id = Integer.parseInt(line);
        for (Worker worker: CollectionManager.getWorkers()){
            if (Objects.equals(worker.getId(), id)){
                CollectionManager.remove(worker);
                return "Данные о работнике удалены";
            }
        }
        return "Работник не был найден";
    }

    public static void removeAtIndex(String position) {
        try {
            int index = Integer.parseInt(position);
            ArrayList<Worker> list = CollectionManager.getWorkers();
            list.remove(index);
            CollectionManager.setWorkers(list);
            System.out.println("Объект удален");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String groupCountingByCoordinates() {
        Map<Double, List<Worker>> groupsByCoordinates = CollectionManager.getWorkers().stream()
                .collect(Collectors.groupingBy(w -> w.getCoordinates().getX() + w.getCoordinates().getY()));

        StringBuilder sb = new StringBuilder();
        int k = 0;
        for (Map.Entry<Double, List<Worker>> entry : groupsByCoordinates.entrySet()) {
            k++;
            sb.append("Группа №").append(k).append("\n");
            for (Worker worker : entry.getValue()) {
                sb.append(worker.toString());
            }
        }
        return sb.toString();

    }

    public static String maxByPerson(){
        PersonComporator comporator = new PersonComporator();
        ArrayList<Worker> list = CollectionManager.getWorkers();
        Collections.sort(list, (w1, w2) ->
                comporator.compare(w1.getPerson(), w2.getPerson()));
        return list.get(list.size() - 1).toString();
    }

    public static String filterContainsName(String line){
        StringBuilder sb = new StringBuilder();
        CollectionManager.getWorkers().stream()
                .filter(worker -> worker.getName().contains(line))
                .forEach(worker -> sb.append(worker).append("\n"));

        return sb.toString();
    }
}
