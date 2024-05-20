package xl.util;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;

import xl.gui.SlotLabel;
import xl.gui.XL;

// TODO move to another package
public class XLPrintStream extends PrintStream {

    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    // TODO Change Object to something appropriate
    public void save(Set<Entry<String, SlotLabel>> set) {
        for (Entry<String, SlotLabel> entry : set) {
            print(entry.getKey());
            print('=');
            println(entry.getValue());
        }
        flush();
        close();
    }
}
