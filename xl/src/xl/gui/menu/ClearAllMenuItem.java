package xl.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

import xl.gui.UpdaterXL;

class ClearAllMenuItem extends JMenuItem implements ActionListener {

    public ClearAllMenuItem(UpdaterXL updater) {
        super("Clear all");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        // TODO

        // när vald töm alla rutor
        // -- göras genom att köra main kommando igen?
    }
}
