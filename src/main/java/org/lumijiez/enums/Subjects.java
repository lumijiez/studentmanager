package org.lumijiez.enums;

import java.io.Serializable;

public enum Subjects implements Serializable {
    ENGLISH("English Language"),
    LINEAR_ALGEBRA("Linear Algebra"),
    OBJECT_ORIENTED_PROGRAMMING("Object Oriented Programming"),
    DATABASES("Databases"),
    MATHEMATICAL_ANALYSIS("Mathematical Analysis"),
    DISCRETE_MATH("Discrete Math"),
    PROBABILITY_AND_STATISTICS("Probability and Statistics"),
    PHYSICS("Physics");

    private final String name;

    Subjects(String name) {
        this.name = name;
    }

    public static Subjects getEnum(String str) {
        for (Subjects st : values()) {
            if (st.name.equals(str)) return st;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
