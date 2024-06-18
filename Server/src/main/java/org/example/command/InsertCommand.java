package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class InsertCommand implements BaseCommand{

    @Override
    public String execute(Request request) throws Exception {
        if (request.getLine().split(" ").length == 2){
            Receiver.insertWorker(request.getLine().split(" ")[1], request.getWorker());
        } else throw new WrongArgumentException();
        return null;
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getDescription() {
        return "insertWorker";
    }
}
