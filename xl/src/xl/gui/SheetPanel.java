package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class SheetPanel extends BorderPanel {
    SlotLabels ll;
    int ROWS, COLS;
    UpdaterXL updater;

    public SheetPanel(int rows, int columns, UpdaterXL ms) {
        add(WEST, new RowLabels(rows));
        updater = ms;
        SlotLabels sl = new SlotLabels(rows, columns, ms);
        ll = sl;
        add(CENTER, sl);
    }

    public SlotLabels getSlotLabels() {
        return ll;
    }

    public void reset() {
        ll = new SlotLabels(ROWS, COLS, updater);
    }
}
