package org.example.command;

import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.system.Receiver;

public class GroupCommand implements BaseCommand{
    @Override
    public String execute(Request request) throws Exception {
        if (request.getLine().split(" ").length == 1){
            return Receiver.groupCountingByCoordinates();
        } else throw new WrongArgumentException();
    }



    @Override
    public String getName() {
        return "group_counting_by_coordinates";
    }

    @Override
    public String getDescription() {
        return "group_counting_by_coordinates";
    }
}
