package org.lumijiez.gui.forms.faculty;

import org.lumijiez.base.Faculty;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RemoveFacultyForm extends JFrame {
    private final JLabel facultyLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JComboBox<Faculty> facultyCombo = new JComboBox<>();
    private final Supervisor sv;

    public RemoveFacultyForm(Supervisor sv) {
        this.sv = sv;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Remove a Faculty");

        titleLabel.setText("Remove a faculty");
        submitButton.setText("Submit");
        cancelButton.setText("Cancel");
        facultyLabel.setText("Faculty:");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        ComboRenderer.setRenderer(facultyCombo, sv.facultyManager().getFaculties().toArray(new Faculty[0]));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cancelButton)
                                                .addGap(38, 38, 38)
                                                .addComponent(submitButton))
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(facultyLabel)
                                                .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(32, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(titleLabel)
                                .addGap(48, 48, 48)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addGap(18, 18, 18)
                                .addComponent(facultyLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addContainerGap(21, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void submitEvent(ActionEvent evt) {
        sv.deleteFaculty(((Faculty) facultyCombo.getSelectedItem()));
        DisplayHandler.displayFaculties();
        this.dispose();
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

}
