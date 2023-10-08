package org.lumijiez.gui.forms.faculty;

import org.lumijiez.base.Faculty;
import org.lumijiez.enums.StudyField;
import org.lumijiez.gui.util.ComboRenderer;
import org.lumijiez.gui.util.ComponentDecorator;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.gui.util.WindowConfig;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class EditFacultyForm extends JFrame {
    private final JLabel abbreviationLabel = new JLabel();
    private final JLabel facultyLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel specialtyLabel = new JLabel();
    private final JLabel titleLabel = new JLabel();
    private final JButton cancelButton = new JButton();
    private final JButton submitButton = new JButton();
    private final JTextField abbreviationField = new JTextField();
    private final JTextField nameField = new JTextField();
    private final JComboBox<Faculty> facultyCombo = new JComboBox<>();
    private final JComboBox<StudyField> specialtyCombo = new JComboBox<>();
    private final Supervisor sv;

    public EditFacultyForm(Supervisor sv) {
        this.sv = sv;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Edit a Faculty");

        titleLabel.setText("Edit a faculty");
        submitButton.setText("Submit");
        cancelButton.setText("Cancel");
        nameLabel.setText("New name:");
        facultyLabel.setText("Faculty:");
        specialtyLabel.setText("New specialty:");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        cancelButton.addActionListener(this::cancelEvent);
        submitButton.addActionListener(this::submitEvent);
        specialtyCombo.addActionListener(this::specialtyComboEvent);
        facultyCombo.addActionListener(this::facultyComboEvent);

        ComboRenderer.setRenderer(facultyCombo, sv.facultyManager().getFaculties().toArray(new Faculty[0]));
        ComboRenderer.setRenderer(specialtyCombo, StudyField.getAllEnums().toArray(new StudyField[0]));

        specialtyCombo.setSelectedItem(((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem())).getField());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(120, 120, 120)
                                                .addComponent(titleLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(facultyLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(specialtyLabel)
                                                                        .addComponent(facultyCombo, 0, 115, Short.MAX_VALUE)
                                                                        .addComponent(specialtyCombo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(abbreviationLabel)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(abbreviationField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addComponent(cancelButton)
                                                                                        .addGap(18, 18, 18)
                                                                                        .addComponent(submitButton)))
                                                                        .addComponent(nameLabel))))))
                                .addContainerGap(24, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nameLabel)
                                        .addComponent(facultyLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(facultyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(abbreviationLabel)
                                        .addComponent(specialtyLabel))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(abbreviationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(specialtyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(submitButton))
                                .addGap(14, 14, 14)));
        pack();
        WindowConfig.center(this);
    }

    private void facultyComboEvent(ActionEvent actionEvent) {
        Faculty fac = ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem()));
        specialtyCombo.setSelectedItem(fac.getField());
        nameField.setText(fac.getName());
    }

    private void specialtyComboEvent(ActionEvent actionEvent) {
        abbreviationField.setText(((StudyField) Objects.requireNonNull(specialtyCombo.getSelectedItem())).getAbbreviation());
    }

    private void submitEvent(ActionEvent evt) {
        ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem())).setName(nameField.getText());
        ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem())).setAbbreviation(abbreviationField.getText());
        ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem())).setField(((StudyField) Objects.requireNonNull(specialtyCombo.getSelectedItem())));
        DisplayHandler.displayFaculties();
        sv.getLogger().logOperation("Faculty edited : " + ((Faculty) Objects.requireNonNull(facultyCombo.getSelectedItem())).getName());
        this.dispose();
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

}
