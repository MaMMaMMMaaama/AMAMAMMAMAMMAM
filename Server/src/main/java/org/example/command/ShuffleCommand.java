package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.recources.Worker;

import java.util.ArrayList;
import java.util.Collections;

public class ShuffleCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws Exception {
        ArrayList<Worker> list = CollectionManager.getWorkers() ;
        if (request.getLine().split(" ").length ==1 ){
            Collections.shuffle(list);
            CollectionManager.setWorkers(list);
        }else throw new WrongArgumentException();
        return "Коллекция была перемешана";
    }

    @Override
    public String getName() {
        return "shuffle";
    }

    @Override
    public String getDescription() {
        return "shuffle_command";
    }
}
