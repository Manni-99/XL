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
        List <SlotLabel> ss = new ArrayList<SlotLabel>();
       

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                //data.append(scanner.nextLine()).append("\n");
                String temp = scanner.nextLine();
                SlotLabel q = new SlotLabel('A', 0, xl.getUpdater());
                q.setText(temp);
                ss.add(q);
            }
          
        }
        int count = 0;
        for(SlotLabel q :  xl.getSheetPanel().getSlotLabels().getLabels()){
            q.setText(ss.get(count).getText());
            count++;
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}
