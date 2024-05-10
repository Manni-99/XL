package xl.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.w3c.dom.events.MouseEvent;

public class CurrentLabel extends ColoredLabel implements ActionListener{

    public CurrentLabel() {
        super("   ", Color.WHITE);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText("kl");
    }

}
