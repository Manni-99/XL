package xl.gui;

import java.awt.Color;
import java.awt.event.*;


public class SlotLabel extends ColoredLabel   {
    public SlotLabel() {
        super("                   ", Color.WHITE, RIGHT);
       this.addMouseListener(new MouseAdapter() {
       @Override
       public void mouseClicked(MouseEvent e){
        setBackground(Color.YELLOW);
        setText("lalal");
       } 
        
       });
    }



}
