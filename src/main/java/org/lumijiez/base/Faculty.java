package org.lumijiez.base;

import org.lumijiez.enums.StudyField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Faculty implements Serializable {
    private String name;
    private String abbreviation;
    private StudyField field;
    private final List<Group> groups = new ArrayList<>();
    public Faculty(String name, String abbreviation, StudyField field) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.field = field;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public StudyField getField() {
        return field;
    }

    public void setField(StudyField field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return getName();
    }

}
