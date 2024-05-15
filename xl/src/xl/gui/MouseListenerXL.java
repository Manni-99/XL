package xl.gui;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
public class MouseListenerXL implements MouseListener {
    private char a;
    private int b;
    private CurrentLabel cr;
    private List<SlotLabel> q;
    private SlotLabel lastClicked;
    public MouseListenerXL(){
       
    }
    public void addA(CurrentLabel label){
        this.cr = label;
    }

    public CurrentLabel getCR(){
        return cr;
    }

    public void setLastClicked(SlotLabel q){
        if(lastClicked != null){
            lastClicked.setBackground(Color.WHITE);
        }
        
        lastClicked = q;
        q.setBackground(Color.YELLOW);
    }
    public char getrow(){
        return this.a;
    }

    public int getcol(){
        return this.b;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
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
