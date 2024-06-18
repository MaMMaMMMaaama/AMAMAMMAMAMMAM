package org.example.command;

import org.example.network.Request;
import org.example.system.Receiver;

public class AddCommand implements BaseCommand{

    @Override
    public String execute(Request request) {
        return Receiver.addNewWorker(request.getWorker());
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add new";
    }
}
