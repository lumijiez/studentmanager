package org.lumijiez.gui.forms.group;

import org.lumijiez.base.Group;
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


public class ShowGroupForm extends JFrame {
    private final JLabel titleLabel = new JLabel();
    private final JLabel groupLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JComboBox<Group> groupCombo = new JComboBox<>();
    private final Supervisor sv;
    private final JTextPane mainTextLabel;

    public ShowGroupForm(Supervisor sv, JTextPane mainTextLabel) {
        this.sv = sv;
        this.mainTextLabel = mainTextLabel;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Show a Group");

        titleLabel.setText("Show a group");
        groupLabel.setText("Group:");
        cancelButton.setText("Cancel");
        submitButton.setText("Submit");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        ComboRenderer.setRenderer(groupCombo, sv.groupManager().getGroups().toArray(new Group[0]));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(groupLabel)
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(cancelButton)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(submitButton))
                                                                .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(titleLabel)))
                                .addContainerGap(24, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addGap(15, 15, 15)
                                .addComponent(groupLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(groupCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addContainerGap(25, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void submitEvent(ActionEvent evt) {
        StringBuilder text = new StringBuilder();
        Group gr = (Group) Objects.requireNonNull(groupCombo.getSelectedItem());
        text.append("<font size='").append(Settings.FontSize).append("'><strong>=================== Group:</strong> ").append(gr.getName()).append("<strong>=====================</strong><br>");
        for (Student student : gr.getStudents())
            text.append("<b>Name:</b> ").append(student.getName())
                    .append("<br><b>Email: </b>").append(student.getEmail())
                    .append("<br><b>Enrol date: </b>").append(student.getEnrollmentDate())
                    .append("<br><strong>===============================================</strong><br>");
        mainTextLabel.setText(text.toString());
        this.dispose();
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }
}
