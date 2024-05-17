package xl.gui.menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import xl.gui.StatusLabel;
import xl.gui.UpdaterXL;
import xl.gui.XL;
import xl.gui.XLList;

public class XLMenuBar extends JMenuBar {

    public XLMenuBar(XL xl, XLList xlList, StatusLabel statusLabel, UpdaterXL updater) {
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        file.add(new SaveMenuItem(xl, statusLabel));
        file.add(new LoadMenuItem(xl, statusLabel));
        file.add(new NewMenuItem(xl));
        file.add(new CloseMenuItem(xl, xlList));
        edit.add(new ClearMenuItem(updater));
        edit.add(new ClearAllMenuItem(updater));
        add(file);
        add(edit);
        add(new WindowMenu(xlList));
    }
}
