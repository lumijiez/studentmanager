package org.lumijiez.managers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LogManager implements Serializable {

    // Has to be transient since it is not serializable
    private transient BufferedWriter writer;

    // Initializes the writer object with a file that matches the exact date of application launch time.
    // Doing it to keep easier track of logging files.
    private void init() {
        BufferedWriter writerTemp;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String fileHandle = "log-" + currentDateTime.format(formatter) + ".txt";
        try {
            FileWriter fwriter = new FileWriter(fileHandle);
            writerTemp = new BufferedWriter(fwriter);
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            writerTemp = null;
        }
        this.writer = writerTemp;
    }

    // Logs an operation, also adds the current time to it.
    // Inits the writer if it's not initialized already.
    public void logOperation(String msg) {
        if (writer == null) init();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss | ");
        try {
            writer.write(currentDateTime.format(formatter) + msg);
            writer.flush();
            writer.newLine();
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    // Closes the file.
    public void close() {
        try {
            this.writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
