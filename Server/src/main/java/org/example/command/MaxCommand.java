package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class MaxCommand implements BaseCommand {

    @Override
    public String execute(Request request) throws Exception {
        if (request.getLine().split(" ").length == 1){
            return Receiver.maxByPerson();
        }else throw new WrongArgumentException();
    }

    @Override
    public String getName() {
        return "max_by_person";
    }

    @Override
    public String getDescription() {
        return "max_by_person_edition";
    }
}
