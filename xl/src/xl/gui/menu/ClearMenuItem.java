package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.*;

public class ClearMenuItem extends JMenuItem implements ActionListener {

    private SlotLabel empty;
    private UpdaterXL ms;

    public ClearMenuItem(UpdaterXL updater) {
        super("Clear");
        addActionListener(this);
        ms = updater;
        ms.addClearMenuItem(this);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
        //
        // Kontrolera mot add att det är ok
        // update referenses to this slot
        // --kanske skulle funka genom att använda add och lägga in en tom cell
        System.out.println("clear menu item");
        if (empty != null) {
            empty.ClearColor();
            ms.setSlotInputFromEditor(null);
            empty = null;
        }
    }

    public void addSlotToClear(SlotLabel slot) {

        empty = slot;
    }
}
