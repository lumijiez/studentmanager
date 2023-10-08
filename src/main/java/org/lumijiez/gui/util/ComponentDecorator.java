package org.lumijiez.gui.util;

import javax.swing.*;

public class ComponentDecorator {
    public static void submitAndCancel(JButton buttonSubmit, JButton buttonCancel) {
        buttonSubmit.setBackground(new java.awt.Color(204, 255, 204));
        buttonCancel.setBackground(new java.awt.Color(255, 204, 204));
    }
}
