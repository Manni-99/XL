package xl.gui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingConstants;

import org.w3c.dom.events.MouseEvent;

public class SlotLabels extends GridPanel {

    private List<SlotLabel> labelList;
    
    public SlotLabels(int rows, int cols, UpdaterXL ms) {
        
        super(rows + 1, cols);
        
        labelList = new ArrayList<SlotLabel>(rows * cols);
        for (char ch = 'A'; ch < 'A' + cols; ch++) {
            add(new ColoredLabel(Character.toString(ch), Color.LIGHT_GRAY, SwingConstants.CENTER));
        }
        for (int row = 1; row <= rows; row++) {
            for (char ch = 'A'; ch < 'A' + cols; ch++) {
                SlotLabel label = new SlotLabel(ch, row, ms);
                add(label);
                labelList.add(label);
            }
        }
        
    }

    public List<SlotLabel> getLabels(){
        return labelList;
    }
   
}
