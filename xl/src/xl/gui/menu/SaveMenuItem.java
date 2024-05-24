package xl.gui.menu;

import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import xl.gui.StatusLabel;
import xl.gui.UpdaterXL;
import xl.gui.XL;
import xl.model.Sheet;
import xl.gui.SlotLabel;
import java.util.*;
import java.io.*;
class SaveMenuItem extends OpenMenuItem {
    UpdaterXL ms;
    public SaveMenuItem(XL xl, StatusLabel statusLabel, UpdaterXL ms) {
        super(xl, statusLabel, "Save");
        this.ms = ms;
    }

    protected void action(String path) throws FileNotFoundException {
        List<SlotLabel> qr = xl.getSheetPanel().getSlotLabels().getLabels();
        Sheet sheet = ms.getSheet();
        
        String fileString = "";
        for(SlotLabel q : qr){
            if(!q.toString().trim().isEmpty())
            fileString += q.getName() +  "="+ sheet.formula(q.getName()) + " \n";
        }
        File file = new File(path + ".xl");
        
        try (PrintWriter out = new PrintWriter(file)) {
            out.print(fileString);
            statusLabel.setText(path + ".xl saved");
        }
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showSaveDialog(xl);
    }
}
