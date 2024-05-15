package xl.gui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.Action;


public class SlotLabel extends ColoredLabel implements MouseListener{
    MouseListenerXL a;
    public SlotLabel(char col, int row, MouseListenerXL a) {
        super("                   ", Color.WHITE, RIGHT);
        setName(String.valueOf(col) + row);
        this.addMouseListener(this);
        this.a = a;
    }

    public void update(){
       a.getCR().setText(getName());
       a.setLastClicked(this);
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

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


}
