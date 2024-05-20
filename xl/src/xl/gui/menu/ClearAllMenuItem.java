package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import xl.gui.*;
import xl.model.*;

class ClearAllMenuItem extends JMenuItem implements ActionListener {

    private UpdaterXL ms;
    private SheetPanel sheetPanel;

    public ClearAllMenuItem(UpdaterXL updater, SheetPanel sheet) {
        super("Clear all");
        ms = updater;
        sheetPanel = sheet;
        addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        // TODO

        // när vald töm alla rutor
        // -- göras genom att köra main kommando igen?
        ms.resetUpdaterXL();
        reset();
        System.out.println("allt clear");
    }

    public void reset() {

        for (SlotLabel slot : sheetPanel.getSlotLabels().getLabels()) {
            slot.setText(null);
            slot.ClearColor();
        }
    }

}
