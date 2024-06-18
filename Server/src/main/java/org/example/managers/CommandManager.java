package org.example.managers;

import org.example.command.*;
import org.example.network.Request;
import org.example.system.Receiver;

import java.util.ArrayDeque;
import java.util.HashMap;

public class CommandManager {
    private static HashMap<String, BaseCommand> commandList = new HashMap<>();
    public static ArrayDeque<BaseCommand> lastSixCommand = new ArrayDeque<>();
    public CommandManager(){
        commandList.put("help", new HelpCommand());
        commandList.put("info", new InfoCommand());
        commandList.put("show", new ShowCommand());
        commandList.put("add", new AddCommand());
        commandList.put("update", new UpdateCommand());
        commandList.put("remove", new RemoveCommand());
        // commandList.put("execute_script", new ExecuteScriptCommand());
        commandList.put("save", new SaveCommand());
        commandList.put("clear", new ClearCommand());
        commandList.put("insert", new InsertCommand());
        commandList.put("remove_at_index", new RemoveAtIndex());
        commandList.put("group_counting_by_coordinates", new GroupCommand());
        commandList.put("shuffle", new ShuffleCommand());
        commandList.put("filter_contains_name", new FilterCommand());
        commandList.put("max_by_person", new MaxCommand());
    }

    public static String executeCommand(Request request){
        String[] sp = request.getLine().split(" ");
        BaseCommand command = commandList.get(sp[0]);
        try {
            return command.execute(request);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "ERROR";
    }

    public static HashMap<String, BaseCommand> getCommandList() {
        return commandList;
    }
}
