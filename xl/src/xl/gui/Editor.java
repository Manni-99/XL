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
        String reference = "";
        String value = currentTextInEditor.substring(1);
        System.out.println(reference);
        System.out.println(value);
        // testa input med sheet.add!
        // reference är vilken adress man är på just nu
        //if(sheet.add(reference, value)){
            ms.setSlotInputFromEditor(currentTextInEditor);
       // }
        

        
        // testa om input är rimlig?
    }
}
