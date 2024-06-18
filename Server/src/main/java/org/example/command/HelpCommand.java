package org.example.command;

import org.example.network.Request;
import org.example.system.Receiver;

public class HelpCommand implements BaseCommand{
    @Override
    public String execute(Request request) {
        return Receiver.showHelp();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "show all commands";
    }
}
