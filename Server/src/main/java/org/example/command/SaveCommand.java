package org.example.command;

import org.example.exceeption.RootException;
import org.example.filelogic.WriterJSON;
import org.example.network.Request;
import org.example.network.Server;

import java.io.IOException;

public class SaveCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws IOException, RootException {
        return  WriterJSON.write(Server.getPath());
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save data";
    }
}
