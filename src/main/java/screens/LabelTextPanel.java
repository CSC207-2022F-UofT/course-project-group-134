package screens;

import javax.swing.*;
import java.awt.*;

// Frameworks/Drivers layer

public class LabelTextPanel extends JPanel {

    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }

}