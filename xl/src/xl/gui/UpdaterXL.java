package xl.gui;

import java.awt.Color;
import java.awt.event.*;
import java.util.*;

import xl.model.*;
import xl.gui.menu.ClearMenuItem;

public class UpdaterXL {

    private CurrentLabel cr;
    private SlotLabel lastClicked;
    private Editor editor;
    private StatusLabel status;
    private ClearMenuItem clearMenu;
    private Sheet sheet;

    public UpdaterXL() {
        this.sheet = new Sheet();

    }

    public Sheet getSheet() {
        return sheet;
    }

    // __________________________________________Addderade attribut
    public void addEditor(Editor editor) {
        this.editor = editor;
    }

    public void addStatus(StatusLabel statlabel) {
        this.status = statlabel;
    }

    public void addCurrentLabel(CurrentLabel label) {
        this.cr = label;
    }

    public void addClearMenuItem(ClearMenuItem clearEye) {
        clearMenu = clearEye;
    }

    // ___________________________________________________________________

    public CurrentLabel getCR() {
        return cr;
    }

    public void setDefaultClicked(SlotLabel q) {
        lastClicked = q;
        lastClicked.setBackground(Color.YELLOW);
    }

    public void setLastClicked(SlotLabel q) {
        editor.setText(q.getText().trim());
        lastClicked.setBackground(Color.WHITE);

        clearMenu.addSlotToClear(q);
        lastClicked = q;
        q.setBackground(Color.YELLOW);
    }

    public SlotLabel getLastClicked() {
        return lastClicked;
    }

    public void setSlotInputFromEditor(String s) {
        // editor.getText()
        lastClicked.setText(s);
    }

    public void StatusTextOk() {
        status.StatusUpdate("Insättning ok!");
    }

    public StatusLabel getStatusLabel() {
        return status;
    }

    // ----- Reset/ clear metoder -----
    public void resetUpdaterXL() {
        sheet.resetMapReferences();
    }

    public void resetSlots(SlotLabel s) {
        lastClicked = s;
    }

    public boolean ClearOneCell() {
        boolean done = true;
        if (/* sheet.add(lastClicked.getName(), null) */ sheet.clear(lastClicked.getName())) {

            sheet.clear(lastClicked.getName())/* add(lastClicked.getName(), null) */;
            System.out.println("En ruta tömd");
            return done;
        }
        return !done;
    }

    // ----------

}
