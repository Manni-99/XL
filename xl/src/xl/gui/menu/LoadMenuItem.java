package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

import xl.gui.SlotLabel;
import xl.gui.SlotLabels;
import xl.gui.StatusLabel;
import xl.gui.UpdaterXL;
import xl.gui.XL;
import xl.model.Sheet;

import java.io.*;
import java.util.*;
class LoadMenuItem extends OpenMenuItem {
    UpdaterXL ms;
    Sheet sheet;
    public LoadMenuItem(XL xl, StatusLabel statusLabel, UpdaterXL ms) {
        super(xl, statusLabel, "Load");
        this.ms = ms;
        this.sheet = ms.getSheet();
    }

    protected void action(String path) throws FileNotFoundException {
        File file = new File(path);
        sheet.clearAll();
        
       
        StringBuilder ss = new StringBuilder();
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                //data.append(scanner.nextLine()).append("\n");
                String currentLine = scanner.nextLine();
                putTheLabel(currentLine);
                
                
                
            }
          
        }
        statusLabel.setText(path + " loaded" );
        
    }
    private void putTheLabel(String currentLine){
        //String adress = currentLine.substring(0,2); // Adress
        String[] content = currentLine.split("=");
        sheet.add(content[0], content[1]);
        
        //System.out.println(content[0] + " " + content[1]);
        for(SlotLabel t : xl.getSheetPanel().getSlotLabels().getLabels()){
           t.setText(sheet.display(t.getName()));
        }
       
    }
    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
