package xl.gui;

import java.awt.Color;
import javax.swing.JTextField;

import xl.model.Sheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Editor extends JTextField implements ActionListener {
    UpdaterXL ms;
    SheetPanel sheetPanel;

    public Editor(UpdaterXL ms, SheetPanel sheetPanel) {
        setBackground(Color.WHITE);
        addActionListener(this);
        this.ms = ms;
        this.sheetPanel = sheetPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentTextInEditor = getText();

        if (ms.getSheet().add(ms.getLastClicked().getName(), currentTextInEditor)) {
            ms.setSlotInputFromEditor(currentTextInEditor);
            ms.StatusTextOk();
            for (SlotLabel slot : sheetPanel.getSlotLabels().getLabels()) {
                ms.resetSlots(slot);
                ms.setSlotInputFromEditor(ms.getSheet().display(slot.getName()));
                slot.setBackground(Color.WHITE);
            }

        } else {
            ms.updateStatus();
        }

    }
}
