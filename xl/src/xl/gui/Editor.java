package xl.gui;

import java.awt.Color;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JTextField implements ActionListener{

    public Editor() {
        setBackground(Color.WHITE);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(getText());
        
        
    }
}
