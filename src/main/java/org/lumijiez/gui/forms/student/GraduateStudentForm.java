package org.lumijiez.gui.forms.student;

import org.lumijiez.base.Student;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class GraduateStudentForm extends JFrame {
    private final JLabel studentLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JComboBox<Student> studentCombo = new JComboBox<>();
    private final Supervisor sv;

    public GraduateStudentForm(Supervisor sv) {
        this.sv = sv;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Graduate a Student");

        ComboRenderer.setRenderer(studentCombo, sv.studentManager().getEnrolled().toArray(new Student[0]));

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        submitButton.setText("Submit");
        cancelButton.setText("Cancel");
        titleLabel.setText("Graduate a Student");
        studentLabel.setText("Student:");

        submitButton.addActionListener(this::cancelEvent);
        cancelButton.addActionListener(this::submitEvent);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(submitButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(studentLabel))
                                        .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titleLabel)
                                .addGap(51, 51, 51)));
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
                                        .addComponent(submitButton)
                                        .addComponent(cancelButton))
                                .addContainerGap(26, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void cancelEvent(ActionEvent evt) {
        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        student.setGraduated(true);
        DisplayHandler.displayStudents();
        this.dispose();
    }

    private void submitEvent(ActionEvent evt) {
        this.dispose();
    }
}
