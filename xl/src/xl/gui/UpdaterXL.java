package xl.gui;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
public class UpdaterXL  {

    private CurrentLabel cr;
    private SlotLabel lastClicked;
    private Editor editor;
    public UpdaterXL(){
       
    }
    public void addCurrentLabel(CurrentLabel label){
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

    public void addEditor(Editor editor){
        this.editor = editor;
    }

    public void setSlotInputFromEditor(String s){
        lastClicked.setText(editor.getText());
    }




    
}
