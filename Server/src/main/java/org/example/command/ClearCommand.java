package org.example.command;

import org.example.network.Request;
import org.example.system.Receiver;

public class ClearCommand implements BaseCommand{
    @Override
    public String execute(Request request) {
        return  Receiver.clearCollection();
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear collection";
    }
}
