package org.lumijiez;

import org.lumijiez.gui.StudentManagementGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Sets a Windows "Look and Feel", or at least tries to
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Windows LAF not found!");
        }

        // Application entry point
        EventQueue.invokeLater(() -> new StudentManagementGUI().setVisible(true));
    }
}

