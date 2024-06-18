package org.example.filelogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.exceeption.RootException;
import org.example.managers.CollectionManager;
import org.example.recources.Worker;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterJSON {
    public static String write(String path) throws IOException, RootException {
        File file = new File(path);

        if (!file.canWrite()){
            throw new RootException("You do not have enough rights to write to the file");
        }
        StringBuilder jsonFile = new StringBuilder();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        for (Worker worker : CollectionManager.getWorkers()) {
            jsonFile.append(mapper.writeValueAsString(worker)).append("\n");
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
        bufferedOutputStream.write(jsonFile.toString().getBytes());
        bufferedOutputStream.close();
        return path;
    }

}
