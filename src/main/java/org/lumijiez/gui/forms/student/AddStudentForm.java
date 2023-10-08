package org.lumijiez.gui.forms.student;

import org.lumijiez.base.Faculty;
import org.lumijiez.base.Group;
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

public class AddStudentForm extends JFrame {
    private final JLabel titleLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel surnameLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel groupLabel = new JLabel();
    private final JLabel facultyLabel = new JLabel();
    private final JLabel birthYearLabel = new JLabel();
    private final JLabel birthDayLabel = new JLabel();
    private final JLabel birthMonthLabel = new JLabel();
    private final JLabel enrolDayLabel = new JLabel();
    private final JLabel enrolMonthLabel = new JLabel();
    private final JLabel enrolYearLabel = new JLabel();
    private final JButton submitButton = new JButton();
    private final JButton cancelButton = new JButton();
    private final JTextField nameField = new JTextField();
    private final JTextField surnameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JComboBox<Group> groupCombo = new JComboBox<>();
    private final JComboBox<Faculty> facultyCombo = new JComboBox<>();
    private final JComboBox<Integer> birthYearField = new JComboBox<>();
    private final JComboBox<Integer> birthDayField = new JComboBox<>();
    private final JComboBox<Integer> birthMonthField = new JComboBox<>();
    private final JComboBox<Integer> enrolDayField = new JComboBox<>();
    private final JComboBox<Integer> enrolMonthField = new JComboBox<>();
    private final JComboBox<Integer> enrolYearField = new JComboBox<>();
    private final Supervisor sv;

    public AddStudentForm(Supervisor sv) {
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

        birthDayField.setModel(new DefaultComboBoxModel<>(days));
        birthMonthField.setModel(new DefaultComboBoxModel<>(months));
        birthYearField.setModel(new DefaultComboBoxModel<>(years));

        enrolDayField.setModel(new DefaultComboBoxModel<>(days));
        enrolMonthField.setModel(new DefaultComboBoxModel<>(months));
        enrolYearField.setModel(new DefaultComboBoxModel<>(years));

        initComponents();
    }


    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Add a Student");

        titleLabel.setText("Add a new student");
        submitButton.setText("Submit");
        cancelButton.setText("Cancel");
        nameLabel.setText("Name:");
        surnameLabel.setText("Surname:");
        emailLabel.setText("Email:");
        groupLabel.setText("Group:");
        facultyLabel.setText("Faculty:");
        birthYearLabel.setText("Year of Birth:");
        birthDayLabel.setText("Day of Birth:");
        birthMonthLabel.setText("Month of Birth:");
        enrolDayLabel.setText("Day of Enrollment:");
        enrolMonthLabel.setText("Month of Enrollment:");
        enrolYearLabel.setText("Year of Enrollment:");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        ComboRenderer.setRenderer(facultyCombo, sv.facultyManager().getFaculties().toArray(new Faculty[0]));
        ComboRenderer.setRenderer(groupCombo, sv.groupManager().getGroups().toArray(new Group[0]));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(titleLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(surnameLabel)
                                                .addGap(142, 142, 142)
                                                .addComponent(emailLabel)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                        .addComponent(nameField)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(nameLabel))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(birthDayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(birthDayLabel))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(birthMonthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(birthMonthField))))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(groupLabel)
                                                                .addGap(67, 67, 67)
                                                                .addComponent(facultyLabel)
                                                                .addGap(51, 51, 51))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(34, 34, 34)
                                                                                                .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(cancelButton)
                                                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(submitButton))))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(32, 32, 32)
                                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(birthYearLabel)
                                                                                        .addComponent(birthYearField, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(enrolDayLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(enrolDayField))
                                                .addGap(29, 29, 29)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(enrolMonthLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(enrolMonthField, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                                .addGap(55, 55, 55)))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(enrolYearField, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                                                .addGap(43, 43, 43))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(enrolYearLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addContainerGap()))))));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addGap(13, 13, 13)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(groupLabel)
                                        .addComponent(facultyLabel))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addComponent(surnameLabel)
                                                .addGap(5, 5, 5))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(emailLabel)
                                                .addGap(4, 4, 4)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(birthDayLabel)
                                        .addComponent(birthMonthLabel)
                                        .addComponent(birthYearLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(birthDayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthMonthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(birthYearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(enrolDayLabel)
                                        .addComponent(enrolMonthLabel)
                                        .addComponent(enrolYearLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(enrolDayField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enrolMonthField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enrolYearField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addGap(21, 21, 21)));
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
        Group group = getSelectedGroup();
        Faculty faculty = getSelectedFaculty();
        Date birthDate = getDateFromFields(birthYearField, birthMonthField, birthDayField);
        Date enrolDate = getDateFromFields(enrolYearField, enrolMonthField, enrolDayField);

        if (!name.isEmpty() && !surname.isEmpty() && !email.isEmpty()) {
            sv.addStudent(name, surname, email, group, faculty, birthDate, enrolDate);
            DisplayHandler.displayStudents();
            this.dispose();
        } else JOptionPane.showMessageDialog(null, "Fill in all the fields!", "Warning!", JOptionPane.INFORMATION_MESSAGE, null);
    }

    private Group getSelectedGroup() {
        return (Group) Objects.requireNonNull(groupCombo.getSelectedItem());
    }

    private Faculty getSelectedFaculty() {
        return (Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem());
    }

    private Date getDateFromFields(JComboBox<Integer> yearField, JComboBox<Integer> monthField, JComboBox<Integer> dayField) {
        int year = (Integer) Objects.requireNonNull(yearField.getSelectedItem());
        int month = (Integer) Objects.requireNonNull(monthField.getSelectedItem()) - 1;
        int day = (Integer) Objects.requireNonNull(dayField.getSelectedItem());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return calendar.getTime();
    }


}
