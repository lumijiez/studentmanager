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

public class AddFacultyForm extends JFrame {
    private final JLabel titleLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel abbreviationLabel = new JLabel();
    private final JLabel specialtyLabel = new JLabel();
    private final JButton submitButton = new JButton();
    private final JButton cancelButton = new JButton();
    private final JTextField nameField = new JTextField();
    private final JTextField abbreviationField = new JTextField();
    private final JComboBox<StudyField> specialtyCombo = new JComboBox<>();
    private final Supervisor sv;

    public AddFacultyForm(Supervisor sv) {
        this.sv = sv;
        initComponents();
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new Font("sansserif", Font.PLAIN, 18));
        setTitle("Add a Faculty");

        titleLabel.setText("Add a new faculty");
        submitButton.setText("Submit");
        cancelButton.setText("Cancel");
        nameLabel.setText("Name:");
        abbreviationLabel.setText("Abbreviation:");
        specialtyLabel.setText("Specialty Field:");

        ComponentDecorator.submitAndCancel(submitButton, cancelButton);

        ComboRenderer.setRenderer(specialtyCombo, StudyField.getAllEnums().toArray(new StudyField[0]));

        abbreviationField.setText(StudyField.getAbbrevFromString(Objects.requireNonNull(specialtyCombo.getSelectedItem()).toString()));

        specialtyCombo.addActionListener(this::specialtyComboEvent);
        submitButton.addActionListener(this::submitEvent);
        cancelButton.addActionListener(this::cancelEvent);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(abbreviationLabel))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(abbreviationField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                                .addComponent(nameField)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGap(5, 5, 5)
                                                        .addComponent(nameLabel)))
                                        .addGap(40, 40, 40)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(specialtyLabel)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(cancelButton)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(submitButton))
                                                .addComponent(specialtyCombo, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(30, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(titleLabel)
                        .addGap(149, 149, 149)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(titleLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameLabel).addComponent(specialtyLabel))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(specialtyCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(abbreviationLabel)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(abbreviationField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancelButton)
                                .addComponent(submitButton))
                        .addContainerGap(24, Short.MAX_VALUE)));
        pack();
        WindowConfig.center(this);
    }

    private void submitEvent(ActionEvent evt) {
        String name = nameField.getText();
        String abbreviation = abbreviationField.getText();
        StudyField specialty = StudyField.getEnum(Objects.requireNonNull(specialtyCombo.getSelectedItem()).toString());
        if (!name.isEmpty()) {
            Faculty newFaculty = new Faculty(name, abbreviation, specialty);
            sv.addFaculty(newFaculty);
            DisplayHandler.displayFaculties();
            this.dispose();
        } else JOptionPane.showMessageDialog(null, "Fill in all the fields!", "Warning!", JOptionPane.INFORMATION_MESSAGE, null);
    }

    private void specialtyComboEvent(ActionEvent evt) {
        abbreviationField.setText(StudyField.getAbbrevFromString(Objects.requireNonNull(specialtyCombo.getSelectedItem()).toString()));
    }

    private void cancelEvent(ActionEvent evt) {
        this.dispose();
    }

}
