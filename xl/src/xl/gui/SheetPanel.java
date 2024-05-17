package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class SheetPanel extends BorderPanel {
    SlotLabels ll;
    public SheetPanel(int rows, int columns, UpdaterXL ms) {
        add(WEST, new RowLabels(rows));
        SlotLabels sl = new SlotLabels(rows, columns, ms);
        ll = sl;
        add(CENTER, sl);
    }

    public SlotLabels getSlotLabels(){
        return ll;
    }
}
