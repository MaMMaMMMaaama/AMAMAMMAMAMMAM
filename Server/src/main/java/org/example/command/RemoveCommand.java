package org.example.command;

import org.example.exceeption.NoElementException;
import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class RemoveCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws WrongArgumentException, NoElementException {
        if (request.getLine().split(" ").length == 2){
            return Receiver.removeById(request.getLine().split(" ")[1]);
        } else throw new WrongArgumentException();
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "remove collection";
    }
}
