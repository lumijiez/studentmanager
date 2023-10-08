package org.lumijiez.base;

import org.lumijiez.enums.Subjects;

import java.io.Serializable;

public class Grade implements Serializable {
    private final Subjects subject;
    private final int grade;

    public Grade(Subjects subject, int grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public Subjects getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }

}
