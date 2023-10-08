package org.lumijiez.managers;

import org.lumijiez.base.Faculty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FacultyManager implements Serializable {

    private final StudentManager sm = new StudentManager();

    private final GroupManager gm = new GroupManager(sm);

    private final List<Faculty> faculties = new ArrayList<>();

    public GroupManager getGm() {
        return gm;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void deleteFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }
}
