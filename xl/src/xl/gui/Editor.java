package xl.gui;

import java.awt.Color;
import javax.swing.JTextField;

import xl.model.Sheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JTextField implements ActionListener{
    UpdaterXL ms;
    public Editor(UpdaterXL ms) {
        setBackground(Color.WHITE);
        addActionListener(this);
        this.ms = ms;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentTextInEditor = getText();
        //if(ms.getSheet().add(ms.getLastClicked().getName(), currentTextInEditor)){
            ms.setSlotInputFromEditor(currentTextInEditor);
            
        //}

        if(getText().equals("Error")){
            ms.getStatusLabel().setText("Error");
        }else{
            ms.getStatusLabel().setText(" ");
        }
    }
}
