package org.lumijiez.managers;

import org.lumijiez.base.Faculty;
import org.lumijiez.base.Grade;
import org.lumijiez.base.Group;
import org.lumijiez.base.Student;

import java.io.Serializable;
import java.util.Date;

// Supervisor class handles all managers, and all operations regarding any data
public class Supervisor implements Serializable {

    private final FacultyManager fm;

    private final LogManager logger;

    public Supervisor() {
        this.fm = new FacultyManager();
        this.logger = new LogManager();
    }

    // Adds a faculty using the FacultyManager
    public void addFaculty(Faculty faculty) {
        facultyManager().addFaculty(faculty);
        logger.logOperation("Faculty added: " + faculty.getName());
    }

    // Deletes a faculty, then deletes all students and groups from the faculty
    public void deleteFaculty(Faculty faculty) {
        fm.deleteFaculty(faculty);
        for (Group gr : faculty.getGroups()) {
            groupManager().deleteGroup(gr);
            for (Student st : gr.getStudents()) {
                studentManager().deleteStudent(st);
            }
        }
        logger.logOperation("Faculty deleted : " + faculty.getName());
    }

    // Adds a grade, self-explanatory
    public void addGrade(Student student, Grade grade) {
        student.addGrade(grade);
        logger.logOperation("Student graded: " + student.getName() + " " + grade.getSubject().getName() + " " + grade.getGrade());
    }

    // Edits a group, also normalizes data to keep track of all bi-directional references
    // due to all my base classes containing two-way references to each other.
    // For example every Student class holds a reference to the Group and Faculty he is in.
    // I did that to keep easier track of objects.
    public void editGroup(Group group, String name, Faculty faculty) {
        group.setName(name);
        Faculty oldFac = group.getFaculty();
        group.setFaculty(faculty);
        faculty.addGroup(group);
        oldFac.getGroups().remove(group);
        logger.logOperation("Group edited: " + group.getName());
    }

    // Deletes a group, then deletes the students.
    public void deleteGroup(Group group) {
        groupManager().deleteGroup(group);
        for (Student st : group.getStudents()) {
            studentManager().deleteStudent(st);
        }
        logger.logOperation("Group deleted: " + group.getName());
    }

    // Adds a student, using the StudentManager
    public void addStudent(String name, String surname, String email, Group group, Faculty faculty, Date birth, Date enrol) {
        Student newStudent = new Student(name, surname, email, group, faculty, birth, enrol);
        studentManager().addStudent(newStudent);
        logger.logOperation("Student added: " + newStudent.getFullname());
    }

    // Edits the student, deletes the reference from the old group, and adds everything to the new one
    public void editStudent(Student student, String name, String surname, String email, Group group, Faculty faculty, Date birth, Date enrol) {
        student.getGroup().deleteStudent(student);
        student.setName(name);
        student.setSurname(surname);
        student.setFullname(name + " " + surname);
        student.setEmail(email);
        student.setGroup(group);
        group.addStudent(student);
        student.setFaculty(faculty);
        student.setDateOfBirth(birth);
        student.setEnrollmentDate(enrol);
        logger.logOperation("Student edited: " + student.getFullname());
    }

    // Adds a group, and adds it to the faculty
    public void addGroup(Group group, Faculty faculty) {
        group.setFaculty(faculty);
        faculty.addGroup(group);
        groupManager().addGroup(group);
        logger.logOperation("Group added: " + group.getName());
    }

    // Deletes a student, simple as that
    public void deleteStudent(Student st) {
        st.getGroup().deleteStudent(st);
        studentManager().deleteStudent(st);
        logger.logOperation("Student deleted: " + st.getFullname());
    }

    // Iterates through all faculties to get a faculty that matches the name
    // In the future would be great to replace it using an UUID instead of a String name.
    public Faculty getFacultyByName(String facultyName) {
        for (Faculty faculty : facultyManager().getFaculties()) {
            if (faculty.getName().equals(facultyName))
                return faculty;
        }
        return null;
    }

    // Same thing as getFacultyByName() except for Groups
    public Group getGroupByName(String groupName, Faculty faculty) {
        for (Group group : groupManager().getGroups()) {
            if (group.getName().equals(groupName) && group.getFaculty().equals(faculty))
                return group;
        }
        return null;
    }

    public FacultyManager facultyManager() {
        return fm;
    }

    public GroupManager groupManager() {
        return fm.getGm();
    }

    public StudentManager studentManager() {
        return fm.getGm().getSm();
    }

    public LogManager getLogger() {
        return this.logger;
    }

}
