package screens;

import javax.swing.*;

// Frameworks/Drivers layer

public class LabelComboboxPanel extends JPanel {

    public LabelComboboxPanel(JLabel label, JComboBox<String> comboBox) {
        this.add(label);
        this.add(comboBox);
    }

}