package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class RemoveAtIndex implements BaseCommand{
    @Override
    public String execute(Request request) throws Exception {
        if (request.getLine().split(" ").length == 2){
            Receiver.removeAtIndex(request.getLine().split(" ")[1]);
        } else throw new WrongArgumentException();
        return null;
    }

    @Override
    public String getName() {
        return "remove_at_index";
    }

    @Override
    public String getDescription() {
        return "remove_at_index_worker";
    }
}
