package xl.gui;

import java.awt.Color;
import javax.swing.JTextField;

import xl.model.Sheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JTextField implements ActionListener{
    UpdaterXL ms;
    Sheet sheet;
    public Editor(UpdaterXL ms, Sheet sheet) {
        setBackground(Color.WHITE);
        addActionListener(this);
        this.ms = ms;
        this.sheet = sheet;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentTextInEditor = getText();
        //if(sheet.add(ms.getLastClicked().getName(), currentTextInEditor)){
            ms.setSlotInputFromEditor(currentTextInEditor);
            
        //}

        if(getText().equals("Error")){
            ms.getStatusLabel().setText("Error");
        }else{
            ms.getStatusLabel().setText(" ");
        }
    }
}
