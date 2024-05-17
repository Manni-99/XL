package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusPanel extends BorderPanel{

    protected StatusPanel(StatusLabel statusLabel, UpdaterXL ms) {
        CurrentLabel c = new CurrentLabel();
        ms.addCurrentLabel(c);
        add(WEST, c);
        add(CENTER, statusLabel);
    }

}
