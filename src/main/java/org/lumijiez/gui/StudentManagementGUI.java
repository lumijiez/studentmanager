package org.lumijiez.gui;

import org.lumijiez.data.DataDeserializer;
import org.lumijiez.data.DataSerializer;
import org.lumijiez.gui.forms.faculty.*;
import org.lumijiez.gui.forms.group.AddGroupForm;
import org.lumijiez.gui.forms.group.DeleteGroupForm;
import org.lumijiez.gui.forms.group.EditGroupForm;
import org.lumijiez.gui.forms.group.ShowGroupForm;
import org.lumijiez.gui.forms.student.*;
import org.lumijiez.gui.loader.BatchGraduater;
import org.lumijiez.gui.loader.BatchLoader;
import org.lumijiez.gui.util.DisplayHandler;
import org.lumijiez.managers.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentManagementGUI extends JFrame {
    private final JMenu fileMenu = new JMenu();
    private final JMenu studentMenu = new JMenu();
    private final JMenu groupMenu = new JMenu();
    private final JMenu facultyMenu = new JMenu();
    private final JMenuBar menuBar = new JMenuBar();
    private static final JTextPane mainTextPane = new JTextPane();
    private static Supervisor sv;
    private final JScrollPane mainScrollPane = new JScrollPane();

    public StudentManagementGUI() {
        sv = (DataDeserializer.deserialize());
        this.setSize(650, 720);
        this.setTitle("Student Management System");
        initComponents();
    }

    private void initComponents() {

        JMenuItem loadBatchOption = new JMenuItem("Load as Batch", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem graduateBatchOption = new JMenuItem("Graduate as Batch", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem saveAsOption = new JMenuItem("Save As (WIP)", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem saveAndExitOption = new JMenuItem("Save and Exit", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem settingsOption = new JMenuItem("Settings (WIP)", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showAllStudentsOption = new JMenuItem("Show all Students", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showParticularStudentOption = new JMenuItem("Show a Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showStudentGrade = new JMenuItem("Show Student Grades", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem graduateStudent = new JMenuItem("Graduate Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showGraduates = new JMenuItem("Show Graduates", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showEnrolled = new JMenuItem("Show Enrolled", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem gradeStudentOption = new JMenuItem("Grade a Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem addStudentOption = new JMenuItem("Add a Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem editStudentOption = new JMenuItem("Edit a Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem deleteStudentOption = new JMenuItem("Delete a Student", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showAllGroupsOption = new JMenuItem("Show all Groups", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showParticularGroupOption = new JMenuItem("Show a Group", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem addGroupOption = new JMenuItem("Add a Group", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem editGroupOption = new JMenuItem("Edit a Group", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem deleteGroupOption = new JMenuItem("Delete a Group", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showAllFacultiesOption = new JMenuItem("Show all Faculties", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showFacultyBySpecialtyOption = new JMenuItem("Show Faculty by Specialty", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem showParticularFacultyOption = new JMenuItem("Show a Faculty", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem addFacultyOption = new JMenuItem("Add a Faculty", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem editFacultyOption = new JMenuItem("Edit a Faculty", UIManager.getIcon("FileView.directoryIcon"));
        JMenuItem removeFacultyOption = new JMenuItem("Remove Faculty", UIManager.getIcon("FileView.directoryIcon"));

        JPopupMenu.Separator studentSeparator = new JPopupMenu.Separator();
        JPopupMenu.Separator groupSeparator = new JPopupMenu.Separator();
        JPopupMenu.Separator facultySeparator = new JPopupMenu.Separator();

        fileMenu.setText("File");
        studentMenu.setText("Student Options");
        groupMenu.setText("Group Options");
        facultyMenu.setText("Faculty Options");

        mainTextPane.setEditable(false);
        mainTextPane.setContentType("text/html");
        mainTextPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mainScrollPane.setViewportView(mainTextPane);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        StudentManagementGUI.this,
                        "Are you sure you want to exit?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    DataSerializer.serialize(sv);
                    dispose();
                }
            }
        });

        loadBatchOption.addActionListener(this::loadBatchEvent);
        graduateBatchOption.addActionListener(this::graduateBatchEvent);
        saveAsOption.addActionListener(this::saveAsEvent);
        saveAndExitOption.addActionListener(this::saveExitEvent);

        fileMenu.add(loadBatchOption);
        fileMenu.add(graduateBatchOption);
        fileMenu.add(saveAsOption);
        fileMenu.add(saveAndExitOption);
        fileMenu.add(settingsOption);

        menuBar.add(fileMenu);

        showAllStudentsOption.addActionListener(this::showAllStudentsEvent);
        showParticularStudentOption.addActionListener(this::showStudentEvent);
        showStudentGrade.addActionListener(this::showGradeEvent);
        gradeStudentOption.addActionListener(this::gradeStudentEvent);
        addStudentOption.addActionListener(this::addStudentEvent);
        editStudentOption.addActionListener(this::editStudentEvent);
        deleteStudentOption.addActionListener(this::deleteStudentEvent);
        graduateStudent.addActionListener(this::graduateStudentEvent);
        showEnrolled.addActionListener(this::showEnrolledEvent);
        showGraduates.addActionListener(this::showGraduatesEvent);

        studentMenu.add(showAllStudentsOption);
        studentMenu.add(showParticularStudentOption);
        studentMenu.add(showStudentGrade);
        studentMenu.add(showEnrolled);
        studentMenu.add(showGraduates);
        studentMenu.add(studentSeparator);
        studentMenu.add(graduateStudent);
        studentMenu.add(gradeStudentOption);
        studentMenu.add(addStudentOption);
        studentMenu.add(editStudentOption);
        studentMenu.add(deleteStudentOption);

        menuBar.add(studentMenu);

        showAllGroupsOption.addActionListener(this::showAllGroupsEvent);
        showParticularGroupOption.addActionListener(this::showGroupEvent);
        addGroupOption.addActionListener(this::addGroupEvent);
        editGroupOption.addActionListener(this::editGroupEvent);
        deleteGroupOption.addActionListener(this::deleteGroupEvent);

        groupMenu.add(showAllGroupsOption);
        groupMenu.add(showParticularGroupOption);
        groupMenu.add(groupSeparator);
        groupMenu.add(addGroupOption);
        groupMenu.add(editGroupOption);
        groupMenu.add(deleteGroupOption);

        menuBar.add(groupMenu);

        showAllFacultiesOption.addActionListener(this::showAllFacultiesEvent);
        showParticularFacultyOption.addActionListener(this::showFacultyEvent);
        showFacultyBySpecialtyOption.addActionListener(this::showFacultySpecEvent);
        addFacultyOption.addActionListener(this::addFacultyEvent);
        editFacultyOption.addActionListener(this::editFacultyEvent);
        removeFacultyOption.addActionListener(this::deleteFacultyEvent);

        facultyMenu.add(showAllFacultiesOption);
        facultyMenu.add(showParticularFacultyOption);
        facultyMenu.add(showFacultyBySpecialtyOption);
        facultyMenu.add(facultySeparator);
        facultyMenu.add(addFacultyOption);
        facultyMenu.add(editFacultyOption);
        facultyMenu.add(removeFacultyOption);

        menuBar.add(facultyMenu);

        setJMenuBar(menuBar);

        DisplayHandler.displayStudents();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainScrollPane, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(mainScrollPane, GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE));
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }

    private void showFacultySpecEvent(ActionEvent actionEvent) {
        ShowSpecialtyFacultyForm form = new ShowSpecialtyFacultyForm(sv, mainTextPane);
        form.setVisible(true);
    }

    private void graduateBatchEvent(ActionEvent actionEvent) {
        BatchGraduater picker = new BatchGraduater(sv);
        picker.setVisible(true);
    }

    private void showGraduatesEvent(ActionEvent actionEvent) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            DisplayHandler.displayGraduates();
        }
    }

    private void showEnrolledEvent(ActionEvent actionEvent) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            DisplayHandler.displayEnrolled();
        }
    }

    private void graduateStudentEvent(ActionEvent actionEvent) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            GraduateStudentForm form = new GraduateStudentForm(sv);
            form.setVisible(true);
        }
    }

    private void showAllStudentsEvent(ActionEvent actionEvent) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            DisplayHandler.displayStudents();
        }
    }

    private void showAllGroupsEvent(ActionEvent actionEvent) {
        if (checkGroup() && checkFaculty()) {
            DisplayHandler.displayGroups();
        }
    }

    private void deleteGroupEvent(ActionEvent actionEvent) {
        if (checkGroup() && checkFaculty()) {
            DeleteGroupForm form = new DeleteGroupForm(sv);
            form.setVisible(true);
        }
    }

    private void loadBatchEvent(ActionEvent evt) {
        BatchLoader picker = new BatchLoader(sv);
        picker.setVisible(true);
    }

    private void saveExitEvent(ActionEvent evt) {
        int result = JOptionPane.showConfirmDialog(
                StudentManagementGUI.this,
                "Are you sure you want to exit?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            DataSerializer.serialize(sv);
            sv.getLogger().logOperation("Died!");
            sv.getLogger().close();
            dispose();
        }
    }

    private void showAllFacultiesEvent(ActionEvent evt) {
        if (checkFaculty()) {
            DisplayHandler.displayFaculties();
        }
    }

    private void saveAsEvent(ActionEvent evt) {

    }

    private void showGroupEvent(ActionEvent evt) {
        if (checkGroup() && checkFaculty()) {
            ShowGroupForm form = new ShowGroupForm(sv, mainTextPane);
            form.setVisible(true);
        }
    }

    private void addFacultyEvent(ActionEvent evt) {
        AddFacultyForm form = new AddFacultyForm(sv);
        form.setVisible(true);
    }

    private void addStudentEvent(ActionEvent evt) {
        if (checkGroup() && checkFaculty()) {
            AddStudentForm form = new AddStudentForm(sv);
            form.setVisible(true);
        }
    }

    private void gradeStudentEvent(ActionEvent evt) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            GradeStudentForm form = new GradeStudentForm(sv, mainTextPane);
            form.setVisible(true);
        }
    }

    private void editStudentEvent(ActionEvent evt) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            EditStudentForm form = new EditStudentForm(sv);
            form.setVisible(true);
        }
    }

    private void deleteStudentEvent(ActionEvent evt) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            DeleteStudentForm form = new DeleteStudentForm(sv);
            form.setVisible(true);
        }
    }

    private void showGradeEvent(ActionEvent evt) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            ShowStudentGradesForm form = new ShowStudentGradesForm(sv, mainTextPane);
            form.setVisible(true);
        }
    }

    private void showStudentEvent(ActionEvent evt) {
        if (checkStudent() && checkGroup() && checkFaculty()) {
            ShowStudentForm form = new ShowStudentForm(sv, mainTextPane);
            form.setVisible(true);
        }
    }

    private void addGroupEvent(ActionEvent evt) {
        if (checkFaculty()) {
            AddGroupForm form = new AddGroupForm(sv);
            form.setVisible(true);
        }
    }

    private void editGroupEvent(ActionEvent evt) {
        if (checkGroup() && checkFaculty()) {
            EditGroupForm form = new EditGroupForm(sv);
            form.setVisible(true);
        }
    }

    private void showFacultyEvent(ActionEvent evt) {
        if (checkFaculty()) {
            ShowFacultyForm form = new ShowFacultyForm(sv, mainTextPane);
            form.setVisible(true);
        }
    }

    private void editFacultyEvent(ActionEvent evt) {
        if (checkFaculty()) {
            EditFacultyForm form = new EditFacultyForm(sv);
            form.setVisible(true);
        }
    }

    private void deleteFacultyEvent(ActionEvent evt) {
        if (checkFaculty()) {
            RemoveFacultyForm form = new RemoveFacultyForm(sv);
            form.setVisible(true);
        }
    }

    private boolean checkFaculty() {
        if (sv.facultyManager().getFaculties().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Configure a faculty!", "Warning!", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }
        return true;
    }

    private boolean checkGroup() {
        if (sv.groupManager().getGroups().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Configure a group!", "Warning!", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }
        return true;
    }

    private boolean checkStudent() {
        if (sv.studentManager().getStudents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students in database!", "Warning!", JOptionPane.INFORMATION_MESSAGE, null);
            return false;
        }
        return true;
    }

    public static JTextPane getMainPane() {
        return mainTextPane;
    }

    public static Supervisor getSv() {
        return sv;
    }
}
