package xl.gui;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class StatusLabel extends ColoredLabel implements Observer {

    public StatusLabel() {
        super("error.status label klass", Color.WHITE);
    }

    public void StatusUpdate(String str) {
        setText(str);
    }

    public void update(Observable observable, Object object) {
        setText("");
    }
}
