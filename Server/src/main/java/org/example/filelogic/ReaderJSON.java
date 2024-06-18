package org.example.filelogic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exceeption.ReadFileException;
import org.example.exceeption.RootException;
import org.example.managers.CollectionManager;
import org.example.recources.Person;
import org.example.recources.Worker;
import org.example.recources.generator.IdGenerator;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class ReaderJSON {
    public static void read(String path) throws RootException, ReadFileException, JsonProcessingException {
        IdGenerator.clear();
        File file = new File(path);
        if (!file.canRead()) {
            throw new RootException("You do not have enough rights to read the file");
        }
        try {
            var br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
                Worker worker = mapper.readValue(line, Worker.class);
                CollectionManager.add(worker);
            }
        } catch (IOException e) {
            System.out.println("Error with file");
            System.exit(1);
        }
    }

}