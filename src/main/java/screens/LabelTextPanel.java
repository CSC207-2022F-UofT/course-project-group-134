package screens;

import javax.swing.*;

// Frameworks/Drivers layer

/**
 * LabelTextPanel is a subclass of JPanel. A LabelTextPanel is a JPanel that
 * has two elements. First, a JLabel which contains text information. Second,
 * a JTextField which the user at the keyboard can input information in.
 */
public class LabelTextPanel extends JPanel {

    /**
     *
     * @param label The JLabel which describes the information required for the JTextField.
     * @param textField The JTextField which the user inputs the required information into.
     */
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }

}