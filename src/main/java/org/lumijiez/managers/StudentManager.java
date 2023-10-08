package org.lumijiez.managers;

import org.lumijiez.base.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentManager implements Serializable {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        student.getGroup().addStudent(student);
        students.add(student);
    }
    public List<Student> getStudents() {
        return students;
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

//    public List<Student> getGraduates() {
//        List<Student> stList = new ArrayList<>();
//        for (Student st : students) {
//            if (st.isGraduated()) {
//                stList.add(st);
//            }
//        }
//        return stList;
//    }

    public List<Student> getEnrolled() {
        List<Student> stList = new ArrayList<>();
        for (Student st : students) {
            if (!st.isGraduated()) {
                stList.add(st);
            }
        }
        return stList;
    }

}
