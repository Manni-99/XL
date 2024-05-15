package xl.gui;

import java.awt.Color;
import java.awt.event.*;


public class SlotLabel extends ColoredLabel implements MouseListener{
    public SlotLabel(char col, int row) {
        super("                   ", Color.WHITE, RIGHT);
        setName(String.valueOf(col) + row);
        addMouseListener(this);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("aarå");
    }

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
   System.out.println("you've entered" + getName());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }



}
