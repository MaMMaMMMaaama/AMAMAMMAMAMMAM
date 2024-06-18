package org.example.system;

import org.example.exceeption.RootException;
import org.example.exceeption.WrongArgumentException;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.UDPClient;
import org.example.recources.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class ScriptExecutor {
    private static Stack<File> st = new Stack<>();
    public static void executeScript(String path, UDPClient udpClient) throws RootException, IOException, WrongArgumentException, ClassNotFoundException, InterruptedException {
        File file = new File(path);
        if (!file.canRead()) {
            throw new RootException("You do not have enough rights to read the file");
        }
        /*if (st.isEmpty()) {
            st.add(file);
        }*/
        st.add(file);
        var br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        String line;
        String[] sp = new String[10];
        while ((line = br.readLine()) != null) {
            String id = null;
            String command = line.split(" ")[0];
            if (command.equals("add") || command.equals("update") || command.equals("insert_at")) {
                if (command.equals("update") || command.equals("insert_at")) id = line.split(" ")[1];
                for (int n = 0; n < 10; n++) {
                    if ((line = br.readLine()) != null) {
                        sp[n] = line;
                    }
                }
                Worker worker = new Worker();

                Validator.checkName(sp[0]);
                worker.setName(sp[0]);

                Coordinates coordinates = new Coordinates();
                Validator.checkXCoordinates(sp[1]);
                Validator.checkYCoordinates(sp[2]);
                if (sp[1].isEmpty()) {
                    coordinates.setY(Double.parseDouble(sp[1]));
                } else {
                    coordinates.setX(Float.parseFloat(sp[1]));
                    coordinates.setY(Double.parseDouble(sp[2]));
                }
                worker.setCoordinates(coordinates);

                Validator.checkSalary(sp[3]);
                worker.setSalary(Integer.parseInt(sp[3]));
                worker.setPosition(Position.valueOf(sp[4]));

                if (!sp[5].isEmpty()) {
                    worker.setStatus(Status.valueOf(sp[5]));
                }

                Person person = new Person();
                if (!sp[6].isEmpty()) {
                    Validator.checkPassportId(sp[6]);
                    person.setPassportID(sp[6]);
                }

                if (sp[7].isEmpty()) {
                    break;
                }
                person.setEyeColor(Color.valueOf(sp[7]));

                person.setHairColor(Color.valueOf(sp[8]));

                if (sp[9].isEmpty()) {
                    break;
                }
                person.setNationality(Country.valueOf(sp[9]));
                worker.setPerson(person);

                Request request = new Request(command);
                request.setWorker(worker);
                udpClient.sendRequest(request);
                System.out.println(udpClient.getResponse().getLine());
            } else {
                if (line.contains("execute_script")) {
                    File file_new = new File(line.split(" ")[1]);
                    if (!file_new.canRead()) {
                        throw new RootException("You do not have enough rights to read the file");
                    }
                    if (st.contains(file_new)) {
                        System.out.println("Recursion to file " + file.getName() + " was skipped");
                    } else {
                        udpClient.sendRequest(new Request(line));

                        System.out.println(udpClient.getResponse().getLine());
                    }
                } else {
                    udpClient.sendRequest(new Request(line));
                    System.out.println(udpClient.getResponse().getLine());
                }
            }
        }
        st.pop();
    }
}
