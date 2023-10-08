package org.lumijiez.gui.loader;

import org.lumijiez.base.Faculty;
import org.lumijiez.base.Group;
import org.lumijiez.base.Student;
import org.lumijiez.enums.StudyField;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BatchGraduater extends JFrame {
    private final Supervisor sv;
    private final JButton submitButton = new JButton();
    private final JLabel titleLabel = new JLabel();
    private final JButton browseButton = new JButton();
    private final JLabel formatLabel = new JLabel();
    private final JScrollPane exampleLabel = new JScrollPane();
    private final JTextArea exampleText = new JTextArea();
    private final JButton cancelButton = new JButton();
    private final JTextPane filePane = new JTextPane();

    public BatchGraduater(Supervisor sv) {
        this.sv = sv;
        initComponents();
    }

    private void initComponents() {

        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        setTitle("Graduate a Batch of Students");

        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);
        browseButton.addActionListener(this::browseEvent);

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setText("Pick a file to graduate a batch");
        submitButton.setText("Submit");
        browseButton.setText("Browse");
        formatLabel.setText("File format example:");
        cancelButton.setText("Cancel");

        exampleText.setColumns(15);
        exampleText.setRows(5);
        exampleText.setEditable(false);
        exampleText.setText("name: John\nsurname: Doe\nemail: john.doe@example.com\ngroup: FAF-223\nfaculty: FCIM\nspecialty: Mechanical Engineering");
        exampleLabel.setViewportView(exampleText);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(filePane, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(browseButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addComponent(cancelButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(submitButton))
                                        .addComponent(formatLabel)
                                        .addComponent(exampleLabel, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(33, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(titleLabel)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(titleLabel)
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(filePane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(browseButton))
                                .addGap(18, 18, 18)
                                .addComponent(formatLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exampleLabel, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addGap(23, 23, 23)));
        pack();
        WindowConfig.center(this);
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

    private void browseEvent(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            filePane.setText(file.getAbsolutePath());
        }
    }

    private void submitEvent(ActionEvent evt) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePane.getText()))) {
            String line, name = "", surname = "", email = "", groupName = "", facultyName = "";

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                String[] parts = line.split(": ", 2);
                if (parts.length != 2) continue;

                String field = parts[0], value = parts[1];

                switch (field) {
                    case "name" -> name = value;
                    case "surname" -> surname = value;
                    case "email" -> email = value;
                    case "group" -> groupName = value;
                    case "faculty" -> facultyName = value;
                    case "specialty" -> handleStudentGraduation(name, surname, email, groupName, facultyName, value);
                    default -> System.err.println("Error reading file!");
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        DisplayHandler.displayStudents();

        this.dispose();
    }

    private void handleStudentGraduation(String name, String surname, String email, String groupName, String facultyName, String fieldName) {
        StudyField specialty = (StudyField.getEnum(fieldName) == null) ? StudyField.DEFAULT_UNASSIGNED : StudyField.getEnum(fieldName);

        Faculty faculty = sv.getFacultyByName(facultyName);
        if (faculty == null) {
            assert specialty != null;
            faculty = new Faculty(facultyName, specialty.getAbbreviation(), specialty);
            sv.addFaculty(faculty);
        }

        Group group = sv.getGroupByName(groupName, faculty);
        if (group == null) {
            group = new Group(groupName);
            sv.addGroup(group, faculty);
        }

        for (Student st : group.getStudents()) {
            if (st.getName().equals(name) && st.getSurname().equals(surname) && st.getEmail().equals(email)) {
                st.setGraduated(true);
                sv.getLogger().logOperation("Graduated student: " + st.getFullname() + " " + st.getGroup().getName());
            }
        }
    }
}
