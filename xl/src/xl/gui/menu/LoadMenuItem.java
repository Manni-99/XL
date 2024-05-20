package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;

import xl.gui.SlotLabel;
import xl.gui.StatusLabel;
import xl.gui.XL;
import java.io.*;
import java.util.*;
class LoadMenuItem extends OpenMenuItem {

    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        File file = new File(path);

       
        StringBuilder ss = new StringBuilder();
        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                //data.append(scanner.nextLine()).append("\n");
                String currentLine = scanner.nextLine();
                putTheLabel(currentLine);
                
                
                
            }
          
        }
        
    }
    private void putTheLabel(String currentLine){
        String adress = currentLine.substring(0,2); // Adress
        String[] content = currentLine.split("=");
    
        for(SlotLabel t : xl.getSheetPanel().getSlotLabels().getLabels()){
            if(adress.equals(t.getName())){
                t.setText(content[1]);
            }
        }
       
    }
    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
