package org.example.command;

import org.example.exceeption.NoElementException;
import org.example.exceeption.RootException;
import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

import java.io.IOException;

public class InfoCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws WrongArgumentException, NoElementException, IOException, RootException {

        return Receiver.showInfo();
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "show info about collection";
    }
}
