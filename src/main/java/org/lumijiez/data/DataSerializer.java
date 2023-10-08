package org.lumijiez.data;

import org.lumijiez.managers.Supervisor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSerializer {
    public static void serialize(Supervisor manager) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("manager.ser"))) {
            oos.writeObject(manager);
            System.out.println("Supervisor object serialized successfully.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
