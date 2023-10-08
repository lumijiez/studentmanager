package org.lumijiez.gui.util;

import org.lumijiez.base.Faculty;
import org.lumijiez.base.Group;
import org.lumijiez.base.Student;
import org.lumijiez.gui.StudentManagementGUI;

public class DisplayHandler {
    public static void displayStudents() {
        StringBuilder text = new StringBuilder();
        text.append("<font size='").append(Settings.FontSize).append("'><strong>======================== Students ==========================</strong><br>");
        for (Student student : StudentManagementGUI.getSv().studentManager().getStudents())
            text.append("<b>Name: </b>").append(student.getFullname())
                    .append("<br><b>Group: </b>").append(student.getGroup().getName())
                    .append("<br><b>Email: </b>").append(student.getEmail())
                    .append("<br><b>Graduated: </b>").append((student.isGraduated() ? "<b color='green'>Yes</b>" : "<b color='red'>No</b>"))
                    .append("<br><strong>===============================================</strong><br>");
        StudentManagementGUI.getMainPane().setText(text.toString());
    }

    public static void displayGroups() {
        StringBuilder text = new StringBuilder();
        text.append("<font size='").append(Settings.FontSize).append("'><strong>========================= Groups ===========================</strong><br>");
        for (Group group : StudentManagementGUI.getSv().groupManager().getGroups())
            text.append("<b>Name: </b>").append(group.getName())
                    .append("<br><b>Faculty: </b>").append(group.getFaculty().getName())
                    .append("<br><b>Number of students: </b>").append(group.getStudents().size())
                    .append("<br><strong>===============================================</strong><br>");
        StudentManagementGUI.getMainPane().setText(text.toString());
    }

    public static void displayFaculties() {
        StringBuilder text = new StringBuilder();
        text.append("<font size='").append(Settings.FontSize).append("'><strong>======================= Faculties =========================</strong><br>");
        for (Faculty fac : StudentManagementGUI.getSv().facultyManager().getFaculties())
            text.append("<b>Name: </b>").append(fac.getName()).append("<br> <b>Specialty: </b>").append(fac.getField().getName())
                    .append("<br><b>Abbreviation:</b> ").append(fac.getAbbreviation())
                    .append("<br><b>Number of groups: </b>").append(fac.getGroups().size())
                    .append("<br><strong>===============================================</strong><br>");
        StudentManagementGUI.getMainPane().setText(text.toString());
    }

    public static void displayGraduates() {
        StringBuilder text = new StringBuilder();
        text.append("<font size='").append(Settings.FontSize).append("'><strong>======================== Students ==========================</strong<br>");
        for (Student st : StudentManagementGUI.getSv().studentManager().getStudents())
            if (st.isGraduated())
                text.append("<b>Name: </b>").append(st.getFullname())
                        .append("<br><b>Group: </b>").append(st.getGroup().getName())
                        .append("<br><b>Graduated: </b>").append("<b color='green'>Yes</b>")
                        .append("<br>===============================================<br>");
        StudentManagementGUI.getMainPane().setText(text.toString());
    }

    public static void displayEnrolled() {
        StringBuilder text = new StringBuilder();
        text.append("<font size='").append(Settings.FontSize).append("'><strong>======================== Students ==========================</strong><br>");
        for (Student st : StudentManagementGUI.getSv().studentManager().getStudents())
            if (!st.isGraduated())
                text.append("<b>Name: </b>").append(st.getFullname())
                        .append("<br><b>Group: </b>").append(st.getGroup().getName())
                        .append("<br><b>Graduated: </b>").append("<b color='red'>No</b>")
                        .append("<br><strong>===============================================</strong><br>");
        StudentManagementGUI.getMainPane().setText(text.toString());
    }
}
