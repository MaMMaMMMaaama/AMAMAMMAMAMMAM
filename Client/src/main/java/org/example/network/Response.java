package org.example.network;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 78L;
    private String line;

    public Response(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
