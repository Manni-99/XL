package xl.model;

import xl.gui.XL;
import xl.gui.XLCounter;
import xl.gui.XLList;

public class tester {
     public static void main(String[] args) {
        Sheet xl = new Sheet();
        xl.add("A1", "3");
        xl.add("A2", "2");
        xl.add("A1", "3");
       /* xl.add("A1", "4");
        xl.add("A3", "A1");
        xl.add("A1", "A1");*/
        System.out.println(xl.toString());
    }
}