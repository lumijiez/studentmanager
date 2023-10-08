package org.lumijiez.gui.forms.student;

import org.lumijiez.base.Grade;
import org.lumijiez.base.Student;
import org.lumijiez.enums.Subjects;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.Settings;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class GradeStudentForm extends JFrame {
    Integer[] grades = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final JLabel gradeLabel = new JLabel();
    private final JLabel studentLabel = new JLabel();
    private final JLabel subjectLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JComboBox<Subjects> subjectCombo = new JComboBox<>();
    private final JComboBox<Student> studentCombo = new JComboBox<>();
    private final JComboBox<Integer> gradeCombo = new JComboBox<>(grades);
    private final Supervisor sv;
    private final JTextPane mainTextLabel;

    public GradeStudentForm(Supervisor sv, JTextPane mainTextLabel) {
        this.sv = sv;
        this.mainTextLabel = mainTextLabel;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Grade A Student");

        titleLabel.setText("Grade a student");
        studentLabel.setText("Student:");
        subjectLabel.setText("Subject:");
        submitButton.setText("Submit");
        gradeLabel.setText("Grade:");
        cancelButton.setText("Cancel");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        ComboRenderer.setRenderer(subjectCombo, Subjects.values());
        ComboRenderer.setRenderer(studentCombo, sv.studentManager().getStudents().toArray(new Student[0]));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(gradeLabel)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(studentLabel)
                                                        .addComponent(gradeCombo, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                                                .addComponent(subjectLabel)
                                                                .addGap(148, 148, 148))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(cancelButton)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(submitButton))
                                                                        .addComponent(subjectCombo, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(titleLabel)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(titleLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(subjectLabel)
                                        .addComponent(studentLabel))
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(subjectCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(gradeLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(gradeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addContainerGap(30, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void submitEvent(ActionEvent evt) {
        Student student = ((Student) Objects.requireNonNull(studentCombo.getSelectedItem()));
        Subjects subject = Subjects.getEnum(Objects.requireNonNull(subjectCombo.getSelectedItem()).toString());
        int intGrade = (Integer) Objects.requireNonNull(gradeCombo.getSelectedItem());
        Grade grade = new Grade(subject, intGrade);
        sv.addGrade(student, grade);

        StringBuilder builder = new StringBuilder();
        builder.append("<font size='").append(Settings.FontSize).append("'><strong>============================================================</strong><br>");
        builder.append("<b>Grades for </b>").append(student.getFullname()).append(" <b>from</b> ").append(student.getGroup().getName()).append(":<br>");
        for (Grade gr : student.getGrades()) builder.append(gr.getSubject()).append(": ").append(gr.getGrade()).append("<br>");
        builder.append("<strong>============================================================</strong><br>");
        mainTextLabel.setText(builder.toString());
        this.dispose();
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

}
