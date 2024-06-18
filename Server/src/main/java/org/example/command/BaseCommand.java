package org.example.command;

import org.example.exceeption.NoElementException;
import org.example.exceeption.RootException;
import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;

import java.io.IOException;

public interface BaseCommand {
    String execute(Request request) throws Exception;
    String getName();
    String getDescription();
}
