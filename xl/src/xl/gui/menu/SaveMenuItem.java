package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.XL;
import xl.gui.SlotLabel;
import java.util.*;
import java.io.*;
class SaveMenuItem extends OpenMenuItem {

    public SaveMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Save");
    }

    protected void action(String path) throws FileNotFoundException {
        List<SlotLabel> qr = xl.getSheetPanel().getSlotLabels().getLabels();
        String fileString = "";
        for(SlotLabel q : qr){
            if(!q.toString().trim().isEmpty())
            fileString += q.getName() +  "="+ q.toString() + " =\n";
        }
        File file = new File(path + ".xl");
        
        try (PrintWriter out = new PrintWriter(file)) {
            out.print(fileString);
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}
