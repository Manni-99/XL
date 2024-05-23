package xl.gui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.Action;

public class SlotLabel extends ColoredLabel implements MouseListener {
    UpdaterXL a;

    public SlotLabel(char col, int row, UpdaterXL a) {
        super("                   ", Color.WHITE, RIGHT);
        setName(String.valueOf(col) + row);
        this.addMouseListener(this);
        this.a = a;
    }

    public String toString() {
        return this.getText();
    }

    public void update() {
        a.getCR().setText(getName());
        a.setLastClicked(this);
    }

    public void ClearColor() {
        this.setBackground(Color.WHITE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        update();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
