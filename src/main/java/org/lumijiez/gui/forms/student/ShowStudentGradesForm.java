package org.lumijiez.gui.forms.student;

import org.lumijiez.base.Grade;
import org.lumijiez.base.Student;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.Settings;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class ShowStudentGradesForm extends JFrame {
    private final JLabel studentLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JComboBox<Student> studentCombo = new JComboBox<>();
    private final Supervisor sv;
    private final JTextPane mainTextLabel;

    public ShowStudentGradesForm(Supervisor sv, JTextPane mainTextLabel) {
        this.sv = sv;
        this.mainTextLabel = mainTextLabel;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Show Student Grades");

        titleLabel.setText("Show Grades");
        studentLabel.setText("Student:");
        submitButton.setText("Submit");
        cancelButton.setText("Cancel");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        ComboRenderer.setRenderer(studentCombo, sv.studentManager().getStudents().toArray(new Student[0]));

        cancelButton.addActionListener(this::cancelEvent);
        submitButton.addActionListener(this::submitEvent);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(5, 5, 5)
                                                                .addComponent(studentLabel))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(cancelButton)
                                                                        .addGap(34, 34, 34)
                                                                        .addComponent(submitButton))
                                                                .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(titleLabel)))
                                .addContainerGap(30, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(titleLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(studentLabel)
                                .addGap(3, 3, 3)
                                .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addContainerGap(22, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void submitEvent(ActionEvent evt) {
        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        StringBuilder builder = new StringBuilder();
        builder.append("<font size='").append(Settings.FontSize).append("'><strong>============================================================</strong><br>");
        builder.append("<b>Grades for </b>").append(student.getFullname()).append(" <b>from</b> ").append(student.getGroup().getName()).append(":<br>");
        for (Grade grade : student.getGrades())
            builder.append(grade.getSubject()).append(": ").append(grade.getGrade()).append("<br>");
        builder.append("<strong>============================================================<strong><br>");
        mainTextLabel.setText(builder.toString());
        this.dispose();
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

}
