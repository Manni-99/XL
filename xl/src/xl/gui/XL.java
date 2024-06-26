package xl.gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import javax.swing.JFrame;
import javax.swing.JPanel;
import xl.gui.menu.XLMenuBar;
import xl.model.Sheet;
//den inlämnade versionen
public class XL extends JFrame {

    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    private StatusLabel statusLabel = new StatusLabel();
    private XLList xlList;
    private SheetPanel sp;
    private UpdaterXL ms;
    private Sheet sheet;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        counter.increment();

        // ----- insatt av gruppen -----
        UpdaterXL ms = new UpdaterXL();
        ms.addStatus(statusLabel);

        // -----

        JPanel statusPanel = new StatusPanel(statusLabel, ms);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, ms);

        // ----- insatt av gruppen ------
        sp = (SheetPanel) sheetPanel;

        Editor editor = new Editor(ms, sp);
        ms.addEditor(editor);
        // -----

        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, ms, sp));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public SheetPanel getSheetPanel() {
        return sp;
    }

    public void setSlotLabelsWithLoad() {

    }

    public UpdaterXL getUpdater() {
        return ms;
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
    // lalalala
}
