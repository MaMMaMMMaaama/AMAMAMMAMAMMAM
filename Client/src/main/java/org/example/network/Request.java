package org.example.network;

import org.example.recources.Worker;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 78L;
    private String line;
    private Worker worker;

    public Request(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
