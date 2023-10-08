package org.lumijiez.enums;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public enum StudyField implements Serializable {
    DEFAULT_UNASSIGNED("Unassigned", "None"),
    MECHANICAL_ENGINEERING("Mechanical Engineering", "ME"),
    SOFTWARE_ENGINEERING("Software Engineering", "FAF"),
    FOOD_TECHNOLOGY("Food Technology", "FT"),
    URBANISM_ARCHITECTURE("Urbanism and Architecture", "UA"),
    VETERINARY_MEDICINE("Veterinary Medicine", "VE");

    private final String name;
    private final String abbreviation;

    StudyField(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public static String getAbbrevFromString(String str) {
        for (StudyField st : values()) {
            if (st.name.equals(str)) return st.abbreviation;
        }
        return str;
    }

    public static StudyField getEnum(String str) {
        for (StudyField st : values()) {
            if (st.name.equals(str)) return st;
        }
        return null;
    }

    public static List<StudyField> getAllEnums() {
        return Arrays.asList(values());
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return getName();
    }
}
