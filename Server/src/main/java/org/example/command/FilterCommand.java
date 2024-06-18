package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class FilterCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws Exception {
        if (request.getLine().split(" ").length == 2){
            return Receiver.filterContainsName(request.getLine().split(" ")[1]);
        }else throw new WrongArgumentException();
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String getDescription() {
        return "filter_contains_name_edition";
    }
}
