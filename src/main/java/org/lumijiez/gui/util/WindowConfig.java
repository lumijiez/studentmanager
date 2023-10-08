package org.lumijiez.gui.util;

import javax.swing.*;
import java.awt.*;

public class WindowConfig {
    public static <T extends JFrame> void center(T frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
    }
}
