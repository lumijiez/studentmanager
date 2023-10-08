package org.lumijiez.gui.forms.student;

import org.lumijiez.base.Faculty;
import org.lumijiez.base.Group;
import org.lumijiez.base.Student;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class EditStudentForm extends JFrame {
    private final JLabel bdayLabel = new JLabel();
    private final JLabel bmonthLabel = new JLabel();
    private final JLabel byearLabel = new JLabel();
    private final JLabel edayLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel emonthLabel = new JLabel();
    private final JLabel eyearLabel = new JLabel();
    private final JLabel facultyLabel = new JLabel();
    private final JLabel groupLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel studentLabel = new JLabel();
    private final JLabel surnameLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JTextField nameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField surnameField = new JTextField();
    private final JComboBox<Faculty> facultyCombo = new JComboBox<>();
    private final JComboBox<Group> groupCombo = new JComboBox<>();
    private final JComboBox<Student> studentCombo = new JComboBox<>();
    private final JComboBox<Integer> birthdayCombo;
    private final JComboBox<Integer> birthmonthCombo;
    private final JComboBox<Integer> birthyearCombo;
    private final JComboBox<Integer> enroldayCombo;
    private final JComboBox<Integer> enrolmonthCombo;
    private final JComboBox<Integer> enrolyearCombo;
    private final Supervisor sv;

    public EditStudentForm(Supervisor sv) {
        this.sv = sv;

        Integer[] days = new Integer[31];
        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }

        Integer[] months = new Integer[12];
        for (int i = 0; i < 12; i++) {
            months[i] = i + 1;
        }

        Integer[] years = new Integer[100];
        for (int i = 0; i < 100; i++) {
            years[i] = 1970 + i;
        }

        birthdayCombo = new JComboBox<>(days);
        birthmonthCombo = new JComboBox<>(months);
        birthyearCombo = new JComboBox<>(years);
        enroldayCombo = new JComboBox<>(days);
        enrolmonthCombo = new JComboBox<>(months);
        enrolyearCombo = new JComboBox<>(years);
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Edit a Student");

        titleLabel.setText("Edit a student");
        submitButton.setText("Submit");
        surnameLabel.setText("New Surname:");
        studentLabel.setText("Student:");
        facultyLabel.setText("New faculty:");
        groupLabel.setText("New Group:");
        nameLabel.setText("New name:");
        emailLabel.setText("New email:");
        bdayLabel.setText("New birthday:");
        bmonthLabel.setText("New birthmonth:");
        byearLabel.setText("New birthyear:");
        emonthLabel.setText("New enrol month:");
        eyearLabel.setText("New enrol year:");
        edayLabel.setText("New enrol day:");
        cancelButton.setText("Cancel");

        studentCombo.addActionListener(this::studentComboEvent);
        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        ComboRenderer.setRenderer(facultyCombo, sv.facultyManager().getFaculties().toArray(new Faculty[0]));
        ComboRenderer.setRenderer(groupCombo, sv.groupManager().getGroups().toArray(new Group[0]));
        ComboRenderer.setRenderer(studentCombo, sv.studentManager().getStudents().toArray(new Student[0]));

        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        facultyCombo.setSelectedItem(student.getFaculty());
        groupCombo.setSelectedItem(student.getGroup());
        emailField.setText(student.getEmail());
        nameField.setText(student.getName());
        surnameField.setText(student.getSurname());

        Calendar birthCalendar = Calendar.getInstance();
        Calendar enrolCalendar = Calendar.getInstance();

        birthCalendar.setTime(student.getDateOfBirth());
        enrolCalendar.setTime(student.getEnrollmentDate());

        birthdayCombo.setSelectedItem(birthCalendar.get(Calendar.DAY_OF_MONTH));
        birthmonthCombo.setSelectedItem(birthCalendar.get(Calendar.MONTH) + 1);
        birthyearCombo.setSelectedItem(birthCalendar.get(Calendar.YEAR));

        enroldayCombo.setSelectedItem(enrolCalendar.get(Calendar.DAY_OF_MONTH));
        enrolmonthCombo.setSelectedItem(enrolCalendar.get(Calendar.MONTH) + 1);
        enrolyearCombo.setSelectedItem(enrolCalendar.get(Calendar.YEAR));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(enroldayCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(edayLabel))
                                                .addGap(33, 33, 33)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(emonthLabel)
                                                        .addComponent(enrolmonthCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                                .addGap(40, 40, 40)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(enrolyearCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(eyearLabel)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(nameLabel)
                                                                        .addComponent(groupLabel)
                                                                        .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(birthdayCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(bdayLabel)
                                                                        .addComponent(studentLabel))
                                                                .addGap(33, 33, 33)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(titleLabel)
                                                                        .addComponent(surnameLabel)
                                                                        .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(facultyLabel)
                                                                        .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(bmonthLabel)
                                                                        .addComponent(birthmonthCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(40, 40, 40))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(16, 16, 16)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(birthyearCombo, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(byearLabel)
                                                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(emailLabel)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(cancelButton)
                                                .addGap(34, 34, 34)
                                                .addComponent(submitButton)))
                                .addContainerGap(27, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(studentLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(titleLabel)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(surnameLabel)
                                        .addComponent(nameLabel)
                                        .addComponent(emailLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(groupLabel)
                                        .addComponent(facultyLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(bdayLabel)
                                                        .addComponent(bmonthLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(birthdayCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(birthmonthCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(byearLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(birthyearCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(edayLabel)
                                                        .addComponent(emonthLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(enroldayCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(enrolmonthCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(eyearLabel)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(enrolyearCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(25, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void cancelEvent(ActionEvent actionEvent) {
        this.dispose();
    }

    private void submitEvent(ActionEvent evt) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String email = emailField.getText();
        Group group = (Group) Objects.requireNonNull(groupCombo.getSelectedItem());
        Faculty faculty = ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem()));
        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        int birthYear = (Integer) Objects.requireNonNull(birthyearCombo.getSelectedItem());
        int birthMonth = (Integer) Objects.requireNonNull(birthmonthCombo.getSelectedItem());
        int birthDay = (Integer) Objects.requireNonNull(birthdayCombo.getSelectedItem());
        int enrolYear = (Integer) Objects.requireNonNull(enrolyearCombo.getSelectedItem());
        int enrolMonth = (Integer) Objects.requireNonNull(enrolmonthCombo.getSelectedItem());
        int enrolDay = (Integer) Objects.requireNonNull(enroldayCombo.getSelectedItem());

        Calendar birthCalendar = Calendar.getInstance();
        Calendar enrolCalendar = Calendar.getInstance();

        birthCalendar.set(Calendar.YEAR, birthYear);
        birthCalendar.set(Calendar.MONTH, birthMonth - 1);
        birthCalendar.set(Calendar.DAY_OF_MONTH, birthDay);

        enrolCalendar.set(Calendar.YEAR, enrolYear);
        enrolCalendar.set(Calendar.MONTH, enrolMonth - 1);
        enrolCalendar.set(Calendar.DAY_OF_MONTH, enrolDay);

        Date birthDate = birthCalendar.getTime();
        Date enrolDate = enrolCalendar.getTime();

        if (!name.isEmpty() && !surname.isEmpty() && !email.isEmpty())
            sv.editStudent(student, name, surname, email, group, faculty, birthDate, enrolDate);

        DisplayHandler.displayStudents();
        this.dispose();
    }

    private void studentComboEvent(ActionEvent evt) {
        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        facultyCombo.setSelectedItem(student.getFaculty());
        groupCombo.setSelectedItem(student.getGroup());
        emailField.setText(student.getEmail());
        nameField.setText(student.getName());
        surnameField.setText(student.getSurname());

        Calendar birthCalendar = Calendar.getInstance();
        Calendar enrolCalendar = Calendar.getInstance();

        birthCalendar.setTime(student.getDateOfBirth());
        enrolCalendar.setTime(student.getEnrollmentDate());

        birthdayCombo.setSelectedItem(birthCalendar.get(Calendar.DAY_OF_MONTH));
        birthmonthCombo.setSelectedItem(birthCalendar.get(Calendar.MONTH) + 1);
        birthyearCombo.setSelectedItem(birthCalendar.get(Calendar.YEAR));

        enroldayCombo.setSelectedItem(enrolCalendar.get(Calendar.DAY_OF_MONTH));
        enrolmonthCombo.setSelectedItem(enrolCalendar.get(Calendar.MONTH) + 1);
        enrolyearCombo.setSelectedItem(enrolCalendar.get(Calendar.YEAR));
    }

}
