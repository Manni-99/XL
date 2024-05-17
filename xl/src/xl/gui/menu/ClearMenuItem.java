package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import xl.gui.*;

public class ClearMenuItem extends JMenuItem implements ActionListener {

    private SlotLabel empty;

    public ClearMenuItem(UpdaterXL updater) {
        super("Clear");
        addActionListener(this);
        updater.addClearMenuItem(this);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO
        // when selected, make choosen slot empty
        // update referenses to this slot
        // --kanske skulle funka genom att använda add och lägga in en tom cell
        System.out.println("clear menu item");
        empty.ClearColor();
        empty = null;
    }

    public void addSlotToClear(SlotLabel slot) {

        empty = slot;
    }
}
