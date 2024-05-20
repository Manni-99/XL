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
        // TODO metod eller lösning som kopplar med sheet.add() att ett null objekt kan
        // läggas in kanske ska gå att ta bort en ruta ändå, men meddelande till status
        // om referenser eller division med noll skapas
        ClearOneMenuItem();

    }

    public void addSlotToClear(SlotLabel slot) {
        empty = slot;
    }

    private void ClearOneMenuItem() {
        System.out.println("clear menu item");
        if (empty != null) {
            // if (ms.ClearOneCell()) {
            empty.ClearColor();
            ms.setSlotInputFromEditor(null);
            empty = null;
            // }

        }
    }

}
