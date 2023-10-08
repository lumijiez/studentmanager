package org.lumijiez.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String surname;
    private String fullname;
    private String email;
    private Date enrollmentDate;
    private Date dateOfBirth;
    private Faculty faculty;
    private Group group;
    private boolean graduated = false;
    private final List<Grade> grades = new ArrayList<>();

    public Student(String name, String surname, String email, Group group, Faculty faculty, Date birth, Date enrol) {
        this.name = name;
        this.surname = surname;
        this.fullname = name + " " + surname;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
        this.dateOfBirth = birth;
        this.enrollmentDate = enrol;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group gr) {
        this.group.deleteStudent(this);
        this.group = gr;
        gr.addStudent(this);
    }

    public boolean isGraduated() {
        return this.graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }

    @Override
    public String toString() {
        return fullname + " | " + group.getName() + " | " + faculty.getName();
    }

}
