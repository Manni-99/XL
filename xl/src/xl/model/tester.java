package xl.model;

import xl.gui.XL;
import xl.gui.XLCounter;
import xl.gui.XLList;

public class tester {
    public static void main(String[] args) {

        BombCell bomber = new BombCell(2);
        System.out.println(bomber.getContent());

        Sheet xl = new Sheet();

        xl.add("A2", "3");
        xl.add("A2", "24/2");
        xl.add("A4", "100");
        xl.add("A5", "A2");
        xl.add("A6", "12/1");
        xl.add("A7", "A4");
        xl.add("A7", "B4");

        System.out.println(xl.toString());
        // System.out.println(xl.display("A7") + " Ska skriva ut vad A1 innehåller");

        // när add är klar, ta bort kommentarer i clear
    }
}