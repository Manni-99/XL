package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import xl.gui.*;

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
        // TODO behöver läggas till metod eller lösning som raderar alla referanser
        // från sheet så den och alla listor vi skapar med input är tomma

        reset();
        System.out.println("allt clear");
        ms.getStatusLabel().setText(" ");

    }

    public void reset() {
        for (SlotLabel slot : sheetPanel.getSlotLabels().getLabels()) {
            ms.resetSlots(slot);
            ms.setSlotInputFromEditor(null);
            // slot.setText(null);
            slot.ClearColor();
        }

        ms.resetUpdaterXL();
        ms.setDefaultClicked(sheetPanel.getSlotLabels().getLabels().get(0));

    }

}
