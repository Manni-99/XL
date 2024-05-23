package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.*;

public class ClearMenuItem extends JMenuItem implements ActionListener {

    private SlotLabel empty;
    private UpdaterXL ms;
    private SheetPanel sheet;

    public ClearMenuItem(UpdaterXL updater, SheetPanel sheet) {
        super("Clear");
        addActionListener(this);
        ms = updater;
        this.sheet = sheet;
        ms.addClearMenuItem(this);

    }

    public void actionPerformed(ActionEvent e) {
        // TODO metod eller lösning som kopplar med sheet.add() att ett null objekt kan
        // läggas in kanske ska gå att ta bort en ruta ändå, men meddelande till status
        // om referenser eller division med noll skapas
        ClearOneMenuItem();

    }

    public void addSlotToClear(SlotLabel slot) {
        empty = slot;
    }

    private void ClearOneMenuItem() {

        if (empty != null) {
            if (ms.ClearOneCell()) {
                // ms.ClearOneCell();
                empty.ClearColor();
                ms.setSlotInputFromEditor(null);
                empty = null;
                // ms.getSheet().add("A1", "7");
                // ms.getSheet().add("A2", "A1");
                // ms.getSheet().add("A3", "1/4");

                for (SlotLabel slot : sheet.getSlotLabels().getLabels()) {
                    ms.resetSlots(slot);
                    ms.setSlotInputFromEditor(ms.getSheet().display(slot.getName()));
                    // System.out.println("slot: " + slot + " input to display: " + slot.getName()
                    // + " To setSlotInputFromEditor: " + ms.getSheet().display(slot.toString()));
                }

            }
        }
    }

}
