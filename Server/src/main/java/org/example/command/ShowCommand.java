package org.example.command;

import org.example.network.Request;
import org.example.system.Receiver;

public class ShowCommand implements BaseCommand{
    @Override
    public String execute(Request request) {

        return  Receiver.showWorkers();
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show workers";
    }
}
